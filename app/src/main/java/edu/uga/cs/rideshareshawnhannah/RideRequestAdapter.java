package edu.uga.cs.rideshareshawnhannah;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RideRequestAdapter extends RecyclerView.Adapter<RideRequestAdapter.RideRequestViewHolder> {

    private List<String> rideRequestsList;

    public RideRequestAdapter(List<String> rideRequestsList) {
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
        String destination = rideRequestsList.get(position);
        holder.destinationTextView.setText(destination);

        holder.acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Accepted request to " + destination, Toast.LENGTH_SHORT).show();
            }
        });

        holder.denyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Denied request to " + destination, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return rideRequestsList.size();
    }

    public static class RideRequestViewHolder extends RecyclerView.ViewHolder {

        TextView destinationTextView;
        Button acceptButton, denyButton;

        public RideRequestViewHolder(@NonNull View itemView) {
            super(itemView);
            destinationTextView = itemView.findViewById(R.id.textViewRequestDestination);
            acceptButton = itemView.findViewById(R.id.buttonRequestAccept);
            denyButton = itemView.findViewById(R.id.buttonRequestDeny);
        }
    }
}
