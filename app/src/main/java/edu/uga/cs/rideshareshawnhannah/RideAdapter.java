package edu.uga.cs.rideshareshawnhannah;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.*;

import java.util.List;

public class RideAdapter extends RecyclerView.Adapter<RideAdapter.RideViewHolder> {

    private List<Ride> rideList;

    public RideAdapter(List<Ride> rideList) {
        this.rideList = rideList;
    }

    @NonNull
    @Override
    public RideViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_ride, parent, false);
        return new RideViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RideViewHolder holder, int position) {
        Ride ride = rideList.get(position);
        holder.textOrigin.setText(ride.getOrigin());
        holder.textDestination.setText(ride.getDestination());
        holder.textDate.setText(ride.getDate());

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser == null) return;

        holder.acceptButton.setOnClickListener(v -> {
            String riderUid = currentUser.getUid();
            String rideId = FirebaseDatabase.getInstance().getReference("acceptedRides").push().getKey();

            if (rideId != null) {
                ride.setRiderUid(riderUid);
                ride.setId(rideId);

                DatabaseReference acceptedRef = FirebaseDatabase.getInstance()
                        .getReference("acceptedRides")
                        .child(rideId);

                acceptedRef.setValue(ride);
                Toast.makeText(v.getContext(), "Ride accepted!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(v.getContext(), "Failed to accept ride", Toast.LENGTH_SHORT).show();
            }
        });

        holder.confirmButton.setOnClickListener(v -> {
            String rideId = ride.getId();
            String driverUid = ride.getDriverUid();
            String riderUid = ride.getRiderUid();

            if (rideId == null || driverUid == null || riderUid == null) {
                Toast.makeText(v.getContext(), "Incomplete ride data", Toast.LENGTH_SHORT).show();
                return;
            }

            FirebaseDatabase.getInstance().getReference("acceptedRides")
                    .child(rideId).removeValue();

            updatePoints(driverUid, riderUid);
            Toast.makeText(v.getContext(), "Ride confirmed!", Toast.LENGTH_SHORT).show();
        });

        holder.cancelButton.setOnClickListener(v -> {
            String rideId = ride.getId();
            if (rideId == null) {
                Toast.makeText(v.getContext(), "Ride ID missing", Toast.LENGTH_SHORT).show();
                return;
            }

            FirebaseDatabase.getInstance().getReference("acceptedRides")
                    .child(rideId).removeValue()
                    .addOnSuccessListener(unused ->
                            Toast.makeText(v.getContext(), "Ride cancelled", Toast.LENGTH_SHORT).show());
        });
    }

    @Override
    public int getItemCount() {
        return rideList.size();
    }

    public static class RideViewHolder extends RecyclerView.ViewHolder {
        TextView textOrigin, textDestination, textDate;
        Button acceptButton, confirmButton, cancelButton;

        public RideViewHolder(@NonNull View itemView) {
            super(itemView);
            textOrigin = itemView.findViewById(R.id.textOrigin);
            textDestination = itemView.findViewById(R.id.textDestination);
            textDate = itemView.findViewById(R.id.textDate);
            acceptButton = itemView.findViewById(R.id.buttonAcceptRide);
            confirmButton = itemView.findViewById(R.id.buttonConfirmRide);
            cancelButton = itemView.findViewById(R.id.buttonCancelRide);
        }
    }

    private void updatePoints(String driverUid, String riderUid) {
        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference("users");

        usersRef.child(driverUid).child("points").get().addOnSuccessListener(snapshot -> {
            long current = snapshot.exists() ? snapshot.getValue(Long.class) : 0;
            usersRef.child(driverUid).child("points").setValue(current + 50);
        });

        usersRef.child(riderUid).child("points").get().addOnSuccessListener(snapshot -> {
            long current = snapshot.exists() ? snapshot.getValue(Long.class) : 0;
            usersRef.child(riderUid).child("points").setValue(Math.max(0, current - 50));
        });
    }
}
