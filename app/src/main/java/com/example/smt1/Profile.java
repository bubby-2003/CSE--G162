package com.example.smt1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Profile extends AppCompatActivity {
    TextView Profinfo, Logsec;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Find TextViews by their IDs
        Profinfo = findViewById(R.id.presinfo);
        Logsec = findViewById(R.id.logsec);

        // Navigate to Pres_info activity
        Profinfo.setOnClickListener(v -> {
            Intent intent = new Intent(Profile.this, Pres_info.class);
            startActivity(intent);
        });

        // Navigate to LogSec activity
        Logsec.setOnClickListener(v -> {
            Intent intent = new Intent(Profile.this, LogSec.class);
            startActivity(intent);
        });
    }
}
