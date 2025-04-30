package edu.uga.cs.rideshareshawnhannah;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RideRequestsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RideRequestAdapter adapter;
    private List<String> rideRequestsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ride_requests);

        recyclerView = findViewById(R.id.recyclerViewRequests);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        rideRequestsList = new ArrayList<>();
        // Dummy hardcoded destinations
        rideRequestsList.add("North Campus");
        rideRequestsList.add("South Campus");
        rideRequestsList.add("City Center");
        rideRequestsList.add("Train Station");
        rideRequestsList.add("Hospital");
        rideRequestsList.add("Concert Hall");
        rideRequestsList.add("Amusement Park");
        rideRequestsList.add("Old Town");
        rideRequestsList.add("Cinema");

        adapter = new RideRequestAdapter(rideRequestsList);
        recyclerView.setAdapter(adapter);
    }
}
