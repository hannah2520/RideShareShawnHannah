package edu.uga.cs.rideshareshawnhannah;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.*;

import java.util.ArrayList;
import java.util.List;

public class ManageRidesActivity extends AppCompatActivity {

    private RecyclerView recyclerViewRides, recyclerViewRequests;
    private RideAdapter rideAdapter;
    private RideRequestAdapter requestAdapter;
    private List<Ride> acceptedRides = new ArrayList<>();
    private List<RideRequest> acceptedRequests = new ArrayList<>();

    private String currentUid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_rides);

        recyclerViewRides = findViewById(R.id.recyclerViewPending);     // You already have these in layout
        recyclerViewRequests = findViewById(R.id.recyclerViewOld);

        recyclerViewRides.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewRequests.setLayoutManager(new LinearLayoutManager(this));

        rideAdapter = new RideAdapter(acceptedRides);
        requestAdapter = new RideRequestAdapter(acceptedRequests);

        recyclerViewRides.setAdapter(rideAdapter);
        recyclerViewRequests.setAdapter(requestAdapter);

        currentUid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        loadAcceptedRides();
        loadAcceptedRequests();
    }

    private void loadAcceptedRides() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("acceptedRides");

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                acceptedRides.clear();
                for (DataSnapshot rideSnap : snapshot.getChildren()) {
                    Ride ride = rideSnap.getValue(Ride.class);
                    if (ride != null && currentUid.equals(ride.getRiderUid())) {
                        ride.setId(rideSnap.getKey());
                        acceptedRides.add(ride);
                    }
                }
                rideAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ManageRidesActivity.this, "Failed to load rides", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadAcceptedRequests() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("acceptedRequests");

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                acceptedRequests.clear();
                for (DataSnapshot reqSnap : snapshot.getChildren()) {
                    RideRequest request = reqSnap.getValue(RideRequest.class);
                    if (request != null && currentUid.equals(request.getDriverUid())) {
                        request.setId(reqSnap.getKey());
                        acceptedRequests.add(request);
                    }
                }
                requestAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ManageRidesActivity.this, "Failed to load requests", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
