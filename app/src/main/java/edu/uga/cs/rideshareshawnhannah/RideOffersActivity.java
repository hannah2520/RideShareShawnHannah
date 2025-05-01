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

public class RideOffersActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RideAdapter adapter;
    private List<Ride> rideOffersList;
    private DatabaseReference ridesRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ride_offers);

        recyclerView = findViewById(R.id.recyclerViewRides);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        rideOffersList = new ArrayList<>();
        adapter = new RideAdapter(rideOffersList);
        recyclerView.setAdapter(adapter);

        ridesRef = FirebaseDatabase.getInstance().getReference("rides");

        ridesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                rideOffersList.clear();
                for (DataSnapshot rideSnapshot : snapshot.getChildren()) {
                    Ride ride = rideSnapshot.getValue(Ride.class);
                    if (ride != null) {
                        rideOffersList.add(ride);
                    }
                }
                adapter.notifyDataSetChanged();

                if (rideOffersList.isEmpty()) {
                    Toast.makeText(RideOffersActivity.this, "No ride offers available", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(RideOffersActivity.this, "Failed to load ride offers", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
