package edu.uga.cs.rideshareshawnhannah;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ManageRidesActivity extends AppCompatActivity {

    private RecyclerView pendingRecyclerView, oldRecyclerView;
    private RideManageAdapter pendingAdapter, oldAdapter;
    private List<String> pendingRides, oldRides;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_rides);

        pendingRecyclerView = findViewById(R.id.recyclerViewPending);
        oldRecyclerView = findViewById(R.id.recyclerViewOld);

        pendingRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        oldRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Dummy Data
        pendingRides = new ArrayList<>();
        pendingRides.add("Pending: Ride to University");
        pendingRides.add("Pending: Ride to Downtown");
        pendingRides.add("Pending: Ride to Mall");

        oldRides = new ArrayList<>();
        oldRides.add("Completed: Ride to Airport");
        oldRides.add("Completed: Ride to Stadium");
        oldRides.add("Cancelled: Ride to Library");

        pendingAdapter = new RideManageAdapter(pendingRides);
        oldAdapter = new RideManageAdapter(oldRides);

        pendingRecyclerView.setAdapter(pendingAdapter);
        oldRecyclerView.setAdapter(oldAdapter);
    }
}
