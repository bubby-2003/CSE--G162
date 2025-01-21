package com.example.smt1;

import static android.content.Intent.getIntent;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class Fin_charge extends AppCompatActivity {

    private TextView chargingStatusText;
    private TextView progressPercentageText;
    private Button stopChargingButton;
    private ProgressBar progressBar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fin_charge);

        // Initialize views
        chargingStatusText = findViewById(R.id.textView8);
        progressPercentageText = findViewById(R.id.textView9);
        stopChargingButton = findViewById(R.id.button4);
        progressBar = findViewById(R.id.progressBar2);

        // Get the data passed from the Dashboard activity (Booking Date & Time)
        String date = getIntent().getStringExtra("date");
        String time = getIntent().getStringExtra("time");

        // Set the charging status text with the passed data
     //   chargingStatusText.setText("Booking Date: " + date + "\nBooking Time: " + time);

        // Simulate progress (This could be dynamic in a real scenario)
        int chargingProgress = 99;  // This can be updated based on actual progress
        progressBar.setProgress(chargingProgress);
      //  progressPercentageText.setText(chargingProgress + "%");

        // Set onClickListener for Stop Charging button
        stopChargingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to Payment activity
                Intent intent = new Intent(Fin_charge.this, Payment.class);
                startActivity(intent);
            }
        });
    }
}
