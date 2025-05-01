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

public class RideRequestAdapter extends RecyclerView.Adapter<RideRequestAdapter.RideRequestViewHolder> {

    private List<RideRequest> rideRequestsList;

    public RideRequestAdapter(List<RideRequest> rideRequestsList) {
        this.rideRequestsList = rideRequestsList;
    }

    @NonNull
    @Override
    public RideRequestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_ride_request, parent, false);
        return new RideRequestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RideRequestViewHolder holder, int position) {
        RideRequest request = rideRequestsList.get(position);
        String display = request.getOrigin() + " → " + request.getDestination();
        holder.destinationTextView.setText(display);

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser == null) return;

        holder.acceptButton.setOnClickListener(view -> {
            String driverUid = currentUser.getUid();
            String requestId = FirebaseDatabase.getInstance().getReference("acceptedRequests").push().getKey();

            if (requestId != null) {
                request.setDriverUid(driverUid);
                request.setId(requestId);

                FirebaseDatabase.getInstance().getReference("acceptedRequests")
                        .child(requestId).setValue(request);

                Toast.makeText(view.getContext(), "Request accepted!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(view.getContext(), "Failed to accept request", Toast.LENGTH_SHORT).show();
            }
        });

        holder.confirmButton.setOnClickListener(view -> {
            String requestId = request.getId();
            String driverUid = request.getDriverUid();
            String riderUid = request.getRiderUid();

            if (requestId == null || driverUid == null || riderUid == null) {
                Toast.makeText(view.getContext(), "Missing info to confirm", Toast.LENGTH_SHORT).show();
                return;
            }

            FirebaseDatabase.getInstance().getReference("acceptedRequests")
                    .child(requestId).removeValue();

            updatePoints(driverUid, riderUid);
            Toast.makeText(view.getContext(), "Request confirmed!", Toast.LENGTH_SHORT).show();
        });

        holder.cancelButton.setOnClickListener(view -> {
            String requestId = request.getId();
            if (requestId == null) {
                Toast.makeText(view.getContext(), "Request ID missing", Toast.LENGTH_SHORT).show();
                return;
            }

            FirebaseDatabase.getInstance().getReference("acceptedRequests")
                    .child(requestId).removeValue()
                    .addOnSuccessListener(unused ->
                            Toast.makeText(view.getContext(), "Request cancelled", Toast.LENGTH_SHORT).show());
        });
    }

    @Override
    public int getItemCount() {
        return rideRequestsList.size();
    }

    public static class RideRequestViewHolder extends RecyclerView.ViewHolder {

        TextView destinationTextView;
        Button acceptButton, confirmButton, cancelButton;

        public RideRequestViewHolder(@NonNull View itemView) {
            super(itemView);
            destinationTextView = itemView.findViewById(R.id.textViewRequestDestination);
            acceptButton = itemView.findViewById(R.id.buttonRequestAccept);
            confirmButton = itemView.findViewById(R.id.buttonRequestDeny); // repurposed
            confirmButton.setText("Confirm");
            confirmButton.setBackgroundColor(itemView.getResources().getColor(android.R.color.holo_purple));
            cancelButton = itemView.findViewById(R.id.buttonCancelRequest);
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
