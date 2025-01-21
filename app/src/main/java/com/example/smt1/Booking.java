package com.example.smt1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class Booking extends AppCompatActivity {

    private EditText dateEditText, timeEditText;
    private Button confirmBookingButton;
    private ImageView dateImageView, timeImageView;
    private String selectedStationName;
    private double stationCost;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        // Retrieve station details from the intent
        Intent intent = getIntent();
        selectedStationName = intent.getStringExtra("stationName");
        stationCost = intent.getDoubleExtra("stationCost", 0.0);

        // Initialize views
        dateEditText = findViewById(R.id.dateEditText);
        timeEditText = findViewById(R.id.timeEditText);
        confirmBookingButton = findViewById(R.id.confirmBookingButton);
        dateImageView = findViewById(R.id.date); // For date popup
        timeImageView = findViewById(R.id.time); // For time popup

        // Date picker functionality
        dateImageView.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            android.app.DatePickerDialog datePickerDialog = new android.app.DatePickerDialog(Booking.this,
                    (view, selectedYear, selectedMonth, selectedDay) -> {
                        // Format and set the selected date
                        String formattedDate = String.format("%02d/%02d/%04d", selectedDay, selectedMonth + 1, selectedYear);
                        dateEditText.setText(formattedDate);
                    }, year, month, day);

            datePickerDialog.show();
        });

        // Time picker functionality
        timeImageView.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int minute = calendar.get(Calendar.MINUTE);

            android.app.TimePickerDialog timePickerDialog = new android.app.TimePickerDialog(Booking.this,
                    (view, selectedHour, selectedMinute) -> {
                        // Format and set the selected time
                        String formattedTime = String.format("%02d:%02d", selectedHour, selectedMinute);
                        timeEditText.setText(formattedTime);
                    }, hour, minute, true); // Use 24-hour format

            timePickerDialog.show();
        });

        // Confirm booking button functionality
        confirmBookingButton.setOnClickListener(v -> {
            String date = dateEditText.getText().toString();
            String time = timeEditText.getText().toString();

            if (date.isEmpty() || time.isEmpty()) {
                // Show a warning if date or time is not selected
                Toast.makeText(Booking.this, "Please select both date and time!", Toast.LENGTH_SHORT).show();
            } else {
                // Show success message
                Toast.makeText(Booking.this, "Station booked successfully!", Toast.LENGTH_LONG).show();

                // Redirect to Payment activity
                Intent intentToPayment = new Intent(Booking.this, Payment.class);
                intentToPayment.putExtra("stationCost", stationCost);
                intentToPayment.putExtra("stationName", selectedStationName);
                intentToPayment.putExtra("selectedDate", date);
                intentToPayment.putExtra("selectedTime", time);
                startActivity(intentToPayment);
            }
        });
    }
}
