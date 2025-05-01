package edu.uga.cs.rideshareshawnhannah;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.*;

import java.util.ArrayList;
import java.util.List;

public class RideRequestsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RideRequestAdapter adapter;
    private List<RideRequest> rideRequestsList;
    private DatabaseReference requestsRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ride_requests);

        recyclerView = findViewById(R.id.recyclerViewRequests);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        rideRequestsList = new ArrayList<>();
        adapter = new RideRequestAdapter(rideRequestsList);
        recyclerView.setAdapter(adapter);

        requestsRef = FirebaseDatabase.getInstance().getReference("requests");

        requestsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                rideRequestsList.clear();
                for (DataSnapshot requestSnapshot : snapshot.getChildren()) {
                    RideRequest request = requestSnapshot.getValue(RideRequest.class);
                    if (request != null) {
                        rideRequestsList.add(request);
                    }
                }
                adapter.notifyDataSetChanged();

                if (rideRequestsList.isEmpty()) {
                    Toast.makeText(RideRequestsActivity.this, "No ride requests found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(RideRequestsActivity.this, "Failed to load requests", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
