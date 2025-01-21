package com.example.smt1;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LogSec extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private EditText emailIdInput, currentPasswordInput, newPasswordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_log_sec);

        // Initialize DatabaseHelper
        databaseHelper = new DatabaseHelper(this);

        // Initialize views
        emailIdInput = findViewById(R.id.emailid);
        currentPasswordInput = findViewById(R.id.cpass);
        newPasswordInput = findViewById(R.id.newpass);
        Button confirmButton = findViewById(R.id.confirm);

        // Handle confirm button click
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailIdInput.getText().toString().trim();
                String currentPassword = currentPasswordInput.getText().toString().trim();
                String newPassword = newPasswordInput.getText().toString().trim();

                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(currentPassword) || TextUtils.isEmpty(newPassword)) {
                    Toast.makeText(LogSec.this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Validate user credentials
                if (databaseHelper.validateUser(email, currentPassword)) {
                    // Update the password
                    boolean isUpdated = databaseHelper.updatePassword(email, newPassword);
                    if (isUpdated) {
                        Toast.makeText(LogSec.this, "Password updated successfully!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(LogSec.this, "Failed to update password", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LogSec.this, "Invalid email or current password", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
