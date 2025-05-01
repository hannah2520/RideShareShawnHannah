package edu.uga.cs.rideshareshawnhannah;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.*;

public class InitialActivity extends AppCompatActivity {

    private TextView textViewPoints;
    private Button buttonViewOffers, buttonViewRequests, buttonPostRide, buttonPostRequest,
            buttonManageRides, buttonLogout;

    private FirebaseAuth mAuth;
    private DatabaseReference userRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);

        // Firebase Auth setup
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        // UI references
        textViewPoints = findViewById(R.id.textViewPoints);
        buttonViewOffers = findViewById(R.id.buttonViewRideOffers);
        buttonViewRequests = findViewById(R.id.buttonViewRideRequests);
        buttonPostRide = findViewById(R.id.buttonPostRide);
        buttonPostRequest = findViewById(R.id.buttonPostRequest);
        buttonManageRides = findViewById(R.id.buttonManageRides);
        buttonLogout = findViewById(R.id.buttonLogout);

        // Load current user's points
        if (currentUser != null) {
            String uid = currentUser.getUid();
            userRef = FirebaseDatabase.getInstance().getReference("users").child(uid).child("points");

            userRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Integer points = snapshot.getValue(Integer.class);
                    if (points != null) {
                        textViewPoints.setText("Points: " + points);
                    } else {
                        textViewPoints.setText("Points: 0");
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    textViewPoints.setText("Points: N/A");
                }
            });
        }

        // Navigation buttons
        buttonViewOffers.setOnClickListener(v ->
                startActivity(new Intent(InitialActivity.this, RideOffersActivity.class)));

        buttonViewRequests.setOnClickListener(v ->
                startActivity(new Intent(InitialActivity.this, RideRequestsActivity.class)));

        buttonPostRide.setOnClickListener(v ->
                startActivity(new Intent(InitialActivity.this, PostRideActivity.class)));

        buttonPostRequest.setOnClickListener(v ->
                startActivity(new Intent(InitialActivity.this, PostRequestActivity.class)));

        buttonManageRides.setOnClickListener(v ->
                startActivity(new Intent(InitialActivity.this, ManageRidesActivity.class)));

        buttonLogout.setOnClickListener(v -> {
            mAuth.signOut();
            Intent intent = new Intent(InitialActivity.this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });

    }
}
