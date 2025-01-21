package com.example.smt1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI elements by their IDs
        TextView textView5 = findViewById(R.id.textView5);  // Reference the TextView
        ImageView imageView3 = findViewById(R.id.imageView3);  // Reference the ImageView
        Button welcomeBtn = findViewById(R.id.welcome_btn);  // Reference the Button

        // Set an OnClickListener for the "Let's Go" button
        welcomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent to start the next activity, you can change NextActivity to your desired activity
                Intent intent = new Intent(MainActivity.this, Login_page.class);
                startActivity(intent);
            }
        });

        // Optionally set actions on textView5 or imageView3 if needed
        // Example: textView5.setText("Hello, EV Charge!");
        // Example: imageView3.setImageResource(R.drawable.some_other_image);
    }
}
