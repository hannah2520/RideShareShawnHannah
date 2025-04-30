package edu.uga.cs.rideshareshawnhannah;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText emailField, passwordField;
    private Button loginButton, signupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailField = findViewById(R.id.editTextEmail);
        passwordField = findViewById(R.id.editTextPassword);
        loginButton = findViewById(R.id.buttonLogin);
        signupButton = findViewById(R.id.buttonSignup);

        View.OnClickListener moveToInitial = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, InitialActivity.class);
                startActivity(intent);
                finish();
            }
        };

        loginButton.setOnClickListener(moveToInitial);
        signupButton.setOnClickListener(moveToInitial);
    }
}
