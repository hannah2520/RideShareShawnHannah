package edu.uga.cs.rideshareshawnhannah;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PostRideActivity extends AppCompatActivity {

    private EditText editOrigin, editDestination, editDate;
    private Button buttonPost;

    private DatabaseReference ridesRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_ride);

        editOrigin = findViewById(R.id.editOrigin);
        editDestination = findViewById(R.id.editDestination);
        editDate = findViewById(R.id.editDate);
        buttonPost = findViewById(R.id.buttonPost);

        ridesRef = FirebaseDatabase.getInstance().getReference("rides");

        buttonPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String origin = editOrigin.getText().toString().trim();
                String destination = editDestination.getText().toString().trim();
                String date = editDate.getText().toString().trim();

                if (origin.isEmpty() || destination.isEmpty() || date.isEmpty()) {
                    Toast.makeText(PostRideActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                Ride ride = new Ride(origin, destination, date);
                ridesRef.push().setValue(ride);

                Toast.makeText(PostRideActivity.this, "Ride Posted!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
