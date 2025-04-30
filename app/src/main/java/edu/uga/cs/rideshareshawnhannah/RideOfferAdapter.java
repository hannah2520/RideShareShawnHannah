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

public class RideOfferAdapter extends RecyclerView.Adapter<RideOfferAdapter.RideOfferViewHolder> {

    private List<String> rideOffersList;

    public RideOfferAdapter(List<String> rideOffersList) {
        this.rideOffersList = rideOffersList;
    }

    @NonNull
    @Override
    public RideOfferViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_ride_offer, parent, false);
        return new RideOfferViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RideOfferViewHolder holder, int position) {
        String destination = rideOffersList.get(position);
        holder.destinationTextView.setText(destination);

        holder.acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Accepted ride to " + destination, Toast.LENGTH_SHORT).show();
            }
        });

        holder.denyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Denied ride to " + destination, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return rideOffersList.size();
    }

    public static class RideOfferViewHolder extends RecyclerView.ViewHolder {

        TextView destinationTextView;
        Button acceptButton, denyButton;

        public RideOfferViewHolder(@NonNull View itemView) {
            super(itemView);
            destinationTextView = itemView.findViewById(R.id.textViewDestination);
            acceptButton = itemView.findViewById(R.id.buttonAccept);
            denyButton = itemView.findViewById(R.id.buttonDeny);
        }
    }
}
