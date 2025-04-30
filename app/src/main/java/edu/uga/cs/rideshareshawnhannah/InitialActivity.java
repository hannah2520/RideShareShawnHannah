package edu.uga.cs.rideshareshawnhannah;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;

public class InitialActivity extends AppCompatActivity {

    private Button buttonViewRideOffers, buttonViewRideRequests, buttonLogout, buttonPostRide, buttonPostRequest, buttonManageRides;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);

        mAuth = FirebaseAuth.getInstance();

        buttonViewRideOffers = findViewById(R.id.buttonViewRideOffers);
        buttonViewRideRequests = findViewById(R.id.buttonViewRideRequests);
        buttonLogout = findViewById(R.id.buttonLogout);
        buttonPostRide = findViewById(R.id.buttonPostRide);
        buttonPostRequest = findViewById(R.id.buttonPostRequest);
        buttonManageRides = findViewById(R.id.buttonManageRides); // <--- NEW

        buttonViewRideOffers.setOnClickListener(v ->
                startActivity(new Intent(InitialActivity.this, RideOffersActivity.class)));

        buttonViewRideRequests.setOnClickListener(v ->
                startActivity(new Intent(InitialActivity.this, RideRequestsActivity.class)));

        buttonPostRide.setOnClickListener(v ->
                startActivity(new Intent(InitialActivity.this, PostRideActivity.class)));

        buttonPostRequest.setOnClickListener(v ->
                startActivity(new Intent(InitialActivity.this, PostRequestActivity.class)));

        buttonManageRides.setOnClickListener(v ->
                startActivity(new Intent(InitialActivity.this, ManageRidesActivity.class))); // <--- NEW

        buttonLogout.setOnClickListener(v -> {
            mAuth.signOut();
            finish();
        });
    }
}
