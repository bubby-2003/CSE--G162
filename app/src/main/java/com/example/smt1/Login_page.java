package com.example.smt1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Login_page extends AppCompatActivity {

    private EditText usernameEditText, passwordEditText;
    private Button loginButton;
    private DatabaseHelper dbHelper;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        // Initialize views
        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);

        // Initialize database helper
        dbHelper = new DatabaseHelper(this);

        // Set login button click listener
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                // Check if both fields are empty
                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(Login_page.this, "Please fill in both fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Validate user login
                if (dbHelper.validateUser(username, password)) {
                    Intent intent = new Intent(Login_page.this, Dashboard.class);

                    startActivity(intent);
                    finish();  // Close login activity to prevent going back
                } else {
                    Toast.makeText(Login_page.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Navigate to the registration page
    public void navigateToRegisterPage(View view) {
        Intent intent = new Intent(Login_page.this, RegisterPage.class);
        startActivity(intent);
    }
}
