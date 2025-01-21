package com.example.smt1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class More extends AppCompatActivity {
TextView logout,about,setting;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_more);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        logout=findViewById(R.id.logout);
        logout.setOnClickListener(view -> {
            Intent intent = new Intent(More.this, Login_page.class);
            startActivity(intent);
        });
        about=findViewById(R.id.about);
        about.setOnClickListener(view -> {
            Intent intent = new Intent(More.this, Aboutus.class);
            startActivity(intent);
        });
        setting=findViewById(R.id.setting);
        setting.setOnClickListener(view -> {
            Intent intent = new Intent(More.this, Setting.class);
            startActivity(intent);
        });


    }
    }
