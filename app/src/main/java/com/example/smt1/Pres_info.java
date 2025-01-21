package com.example.smt1;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Pres_info extends AppCompatActivity {

    private TextView tUserId, tEmailId, tPhNo, tPass;
    private EditText eUserId, eEmailId, ePhNo, ePass;
    private Button saveButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pres_info);

        // Bind TextViews
        tUserId = findViewById(R.id.tuserid);
        tEmailId = findViewById(R.id.temailid);
        tPhNo = findViewById(R.id.tphno);
        tPass = findViewById(R.id.tpass);

        // Bind EditTexts
        eUserId = findViewById(R.id.userid);
        eEmailId = findViewById(R.id.emailid);
        ePhNo = findViewById(R.id.phno);
        ePass = findViewById(R.id.pass);

        // Bind Button
        saveButton = findViewById(R.id.savechanges);

        // Initially hide EditTexts and show TextViews
        toggleVisibility(true);

        // Set onClickListeners for the "Edit" TextViews to switch to EditTexts
        setEditClickListener(R.id.textView20, tUserId, eUserId);
        setEditClickListener(R.id.textView21, tEmailId, eEmailId);
        setEditClickListener(R.id.textView22, tPhNo, ePhNo);
        setEditClickListener(R.id.textView26, tPass, ePass);

        // Save button functionality
        saveButton.setOnClickListener(v -> {
            // Set the text from EditTexts back to TextViews
            tUserId.setText(eUserId.getText().toString());
            tEmailId.setText(eEmailId.getText().toString());
            tPhNo.setText(ePhNo.getText().toString());
            tPass.setText(ePass.getText().toString());

            // Switch back to TextViews
            toggleVisibility(true);
        });

        // Handle window insets for edge-to-edge display
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void toggleVisibility(boolean showTextViews) {
        int textViewVisibility = showTextViews ? View.VISIBLE : View.GONE;
        int editTextVisibility = showTextViews ? View.GONE : View.VISIBLE;

        // Toggle visibility of TextViews and EditTexts
        tUserId.setVisibility(textViewVisibility);
        tEmailId.setVisibility(textViewVisibility);
        tPhNo.setVisibility(textViewVisibility);
        tPass.setVisibility(textViewVisibility);

        eUserId.setVisibility(editTextVisibility);
        eEmailId.setVisibility(editTextVisibility);
        ePhNo.setVisibility(editTextVisibility);
        ePass.setVisibility(editTextVisibility);
    }

    private void setEditClickListener(int editTextViewId, TextView textView, EditText editText) {
        findViewById(editTextViewId).setOnClickListener(v -> {
            // Copy the text from TextView to EditText
            editText.setText(textView.getText().toString());

            // Show the EditText and hide the TextView
            textView.setVisibility(View.GONE);
            editText.setVisibility(View.VISIBLE);
            editText.requestFocus();
        });
    }
}
