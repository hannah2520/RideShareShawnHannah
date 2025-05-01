package edu.uga.cs.rideshareshawnhannah;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.Calendar;

public class PostRideActivity extends AppCompatActivity {

    private EditText editOrigin, editDestination, editDate;
    private Button buttonPickDate, buttonPost;
    private DatabaseReference ridesRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_ride);

        editOrigin = findViewById(R.id.editOrigin);
        editDestination = findViewById(R.id.editDestination);
        editDate = findViewById(R.id.editDate);
        buttonPickDate = findViewById(R.id.buttonPickDate);
        buttonPost = findViewById(R.id.buttonPost);

        ridesRef = FirebaseDatabase.getInstance().getReference("rides");

        buttonPickDate.setOnClickListener(v -> showDatePicker());

        buttonPost.setOnClickListener(v -> {
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
        });
    }

    private void showDatePicker() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, year1, month1, dayOfMonth) -> {
                    String formattedDate = (month1 + 1) + "/" + dayOfMonth + "/" + year1;
                    editDate.setText(formattedDate);
                },
                year, month, day
        );
        datePickerDialog.show();
    }
}
