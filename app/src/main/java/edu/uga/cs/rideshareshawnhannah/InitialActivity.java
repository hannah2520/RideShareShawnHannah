package edu.uga.cs.rideshareshawnhannah;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;

public class InitialActivity extends AppCompatActivity {

    private Button buttonViewRides, buttonLogout, buttonPostRide, buttonPostRequest;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);

        mAuth = FirebaseAuth.getInstance();

        buttonViewRides = findViewById(R.id.buttonViewRides);
        buttonLogout = findViewById(R.id.buttonLogout);
        buttonPostRide = findViewById(R.id.buttonPostRide);
        buttonPostRequest = findViewById(R.id.buttonPostRequest);

        buttonViewRides.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(InitialActivity.this, MainActivity.class));
            }
        });

        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                finish();
            }
        });
        buttonPostRide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InitialActivity.this, PostRideActivity.class);
                startActivity(intent);
            }
        });
        buttonPostRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(InitialActivity.this, PostRequestActivity.class));
            }
        });

    }
}
