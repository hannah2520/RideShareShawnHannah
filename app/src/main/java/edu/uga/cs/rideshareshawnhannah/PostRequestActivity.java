package edu.uga.cs.rideshareshawnhannah;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class PostRequestActivity extends AppCompatActivity {

    private EditText editOrigin, editDestination, editDate, editNotes;
    private Button buttonPostRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_request);

        editOrigin = findViewById(R.id.editOriginRequest);
        editDestination = findViewById(R.id.editDestinationRequest);
        editDate = findViewById(R.id.editDateRequest);
        editNotes = findViewById(R.id.editNotesRequest);
        buttonPostRequest = findViewById(R.id.buttonPostRequest);

        buttonPostRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String origin = editOrigin.getText().toString().trim();
                String destination = editDestination.getText().toString().trim();
                String date = editDate.getText().toString().trim();
                String notes = editNotes.getText().toString().trim();

                if (origin.isEmpty() || destination.isEmpty() || date.isEmpty()) {
                    Toast.makeText(PostRequestActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                DatabaseReference requestsRef = FirebaseDatabase.getInstance().getReference("requests");
                String requestId = requestsRef.push().getKey();

                RideRequest request = new RideRequest(origin, destination, date, notes);
                if (requestId != null) {
                    requestsRef.child(requestId).setValue(request);
                    Toast.makeText(PostRequestActivity.this, "Ride request posted successfully", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(PostRequestActivity.this, "Failed to post request", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
