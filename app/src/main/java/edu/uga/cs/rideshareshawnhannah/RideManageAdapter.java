package edu.uga.cs.rideshareshawnhannah;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RideManageAdapter extends RecyclerView.Adapter<RideManageAdapter.RideManageViewHolder> {

    private List<String> ridesList;

    public RideManageAdapter(List<String> ridesList) {
        this.ridesList = ridesList;
    }

    @NonNull
    @Override
    public RideManageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_manage_ride, parent, false);
        return new RideManageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RideManageViewHolder holder, int position) {
        holder.rideTextView.setText(ridesList.get(position));
    }

    @Override
    public int getItemCount() {
        return ridesList.size();
    }

    public static class RideManageViewHolder extends RecyclerView.ViewHolder {

        TextView rideTextView;

        public RideManageViewHolder(@NonNull View itemView) {
            super(itemView);
            rideTextView = itemView.findViewById(R.id.textViewRideManage);
        }
    }
}
