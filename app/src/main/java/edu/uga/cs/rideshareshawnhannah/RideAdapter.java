package edu.uga.cs.rideshareshawnhannah;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
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
                .inflate(R.layout.activity_ride, parent, false); // 👈 important, use your ride XML
        return new RideViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RideViewHolder holder, int position) {
        Ride ride = rideList.get(position);
        holder.textOrigin.setText(ride.getOrigin());
        holder.textDestination.setText(ride.getDestination());
        holder.textDate.setText(ride.getDate());
    }

    @Override
    public int getItemCount() {
        return rideList.size();
    }

    public static class RideViewHolder extends RecyclerView.ViewHolder {
        TextView textOrigin, textDestination, textDate;

        public RideViewHolder(@NonNull View itemView) {
            super(itemView);
            textOrigin = itemView.findViewById(R.id.textOrigin);
            textDestination = itemView.findViewById(R.id.textDestination);
            textDate = itemView.findViewById(R.id.textDate);
        }
    }
}
