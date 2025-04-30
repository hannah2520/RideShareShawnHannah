package edu.uga.cs.rideshareshawnhannah;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RideOffersActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RideOfferAdapter adapter;
    private List<String> rideOffersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ride_offers);

        recyclerView = findViewById(R.id.recyclerViewRides);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        rideOffersList = new ArrayList<>();
        // Dummy hardcoded destinations
        rideOffersList.add("Downtown");
        rideOffersList.add("Airport");
        rideOffersList.add("Mall");
        rideOffersList.add("University");
        rideOffersList.add("Beach");
        rideOffersList.add("Stadium");
        rideOffersList.add("Library");
        rideOffersList.add("Park");
        rideOffersList.add("Museum");

        adapter = new RideOfferAdapter(rideOffersList);
        recyclerView.setAdapter(adapter);
    }
}
