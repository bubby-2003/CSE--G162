package com.example.smt1;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // Book Slot
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) ImageView bookSlotIcon = findViewById(R.id.bookSlotIcon);
        bookSlotIcon.setOnClickListener(v -> {
            Intent intent = new Intent(Dashboard.this, Station_Booking.class);
            startActivity(intent);
        });

        // AI Predict
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) ImageView finChar = findViewById(R.id.fin_char);
        finChar.setOnClickListener(v -> {
            Intent intent = new Intent(Dashboard.this, Fin_charge.class);
            startActivity(intent);
        });

        // Profile Popup
        ImageView profileIcon = findViewById(R.id.profile);
       profileIcon.setOnClickListener(v -> {
            // Redirect to login page
            Intent intent = new Intent(Dashboard.this, Profile.class);
            startActivity(intent);
            finish(); // Close the dashboard activity
        });

        // Logout
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) ImageView logoutIcon = findViewById(R.id.logoutIcon);
        logoutIcon.setOnClickListener(v -> {
            // Redirect to login page
            Intent intent = new Intent(Dashboard.this, Login_page.class);
            startActivity(intent);
            finish(); // Close the dashboard activity
        });
    }

//    private void showProfilePopup() {
//        // Create a dialog instance
//        Dialog profileDialog = new Dialog(this);
//
//        // Remove dialog title bar
//        profileDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//
//        // Inflate the profile.xml layout
//        View profileView = LayoutInflater.from(this).inflate(R.layout.activity_profile, null);
//
//        // Set the content view of the dialog
//        profileDialog.setContentView(profileView);
//
//        // Optional: Customize dialog appearance (e.g., size, transparency)
//        if (profileDialog.getWindow() != null) {
//            profileDialog.getWindow().setLayout(
//                    (int) (getResources().getDisplayMetrics().widthPixels * 0.9),
//                    (int) (getResources().getDisplayMetrics().heightPixels * 0.8)
//            );
//        }

        // Show the dialog
     //   profileDialog.show();
    }

