package edu.uga.cs.rideshareshawnhannah;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.*;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private RecyclerView recyclerView;
    private RideAdapter rideAdapter;
    private List<Ride> rideList;
    private DatabaseReference ridesRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false); // hide app name
        }

        recyclerView = findViewById(R.id.recyclerViewRides);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        rideList = new ArrayList<>();
        rideAdapter = new RideAdapter(rideList);
        recyclerView.setAdapter(rideAdapter);

        ridesRef = FirebaseDatabase.getInstance().getReference("rides");

        ridesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                rideList.clear();
                for (DataSnapshot rideSnapshot : snapshot.getChildren()) {
                    Ride ride = rideSnapshot.getValue(Ride.class);
                    if (ride != null) {
                        rideList.add(ride);
                    }
                }
                rideAdapter.notifyDataSetChanged();

                if (rideList.isEmpty()) {
                    Toast.makeText(MainActivity.this, "No rides available.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w(TAG, "loadRides:onCancelled", error.toException());
                Toast.makeText(MainActivity.this, "Failed to load rides.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);  // we will create this XML now
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_logout) {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
