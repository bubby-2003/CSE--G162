package com.example.smt1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;
import org.json.JSONObject;

public class Payment extends AppCompatActivity implements PaymentResultListener {

    private TextView amountTextView, addCardTextView;
    private Button continueButton;
    private RadioGroup radioGroup;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        // Razorpay initialization
        Checkout.preload(getApplicationContext());

        amountTextView = findViewById(R.id.amountTextView);
        continueButton = findViewById(R.id.payButton);
        radioGroup = findViewById(R.id.radioGroup);

        // Add Card TextView
       // addCardTextView = findViewById(R.id.addCardTextView);

        Intent intent = getIntent();
        double stationCost = intent.getDoubleExtra("stationCost", 0.0);
        amountTextView.setText("₹" + stationCost);

    //    addCardTextView.setOnClickListener(v -> showAddCardPopup());  // Add card functionality

        continueButton.setOnClickListener(v -> {
            int selectedOption = radioGroup.getCheckedRadioButtonId();

            if (selectedOption == -1) {
                Toast.makeText(Payment.this, "Please select a payment method!", Toast.LENGTH_SHORT).show();
            } else {
                if (selectedOption == R.id.phonepe_option) {
                    launchUPIApp("com.phonepe.app");
                } else if (selectedOption == R.id.googlepay_option) {
                    launchUPIApp("com.google.android.apps.nbu.paisa.user");
//                } else if (selectedOption == R.id.razorpay_option) {
//                    // Proceed with Razorpay payment option
//                    startRazorpayPayment(stationCost);
                } else if (selectedOption == R.id.other_upi_option) {
                    launchUPIApp(null); // Opens any UPI-enabled app chooser
                } else {
                    Toast.makeText(Payment.this, "Invalid selection!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

//    private void showAddCardPopup() {
//        LayoutInflater inflater = LayoutInflater.from(this);
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setView(inflater.inflate(R.layout.activity_add_card, null));
//        AlertDialog dialog = builder.create();
//        dialog.show();
//    }

    private void launchUPIApp(String packageName) {
        try {
            // Convert stationCost to a formatted string
            Intent intent = getIntent();
            double stationCost = intent.getDoubleExtra("stationCost", 0.0);
            String stationCostStr = String.format("%.2f", stationCost); // Format to two decimal places

            // Construct the UPI URI with the stationCost value
            Uri uri = Uri.parse("upi://pay?pa=8073580509@ybl&pn=8073580509&mc=&tid=txn12345&tr=order1234&tn=Solt+payment&am=" + stationCostStr + "&cu=INR");
            Intent intentUpi = new Intent(Intent.ACTION_VIEW, uri);
            if (packageName != null) {
                intentUpi.setPackage(packageName); // Set the package name for the specific UPI app
            }
            if (intentUpi.resolveActivity(getPackageManager()) != null) {
                startActivity(intentUpi);
            } else {
                Toast.makeText(this, "No UPI app found to handle this action!", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Error opening UPI app: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

//    // Razorpay payment handling
//    private void startRazorpayPayment(double amount) {
//        try {
//            // Creating a payment object in JSON format for Razorpay
//            JSONObject options = new JSONObject();
//            options.put("currency", "INR");
//            options.put("amount", (int) (amount * 100));  // Converting amount to paisa (as Razorpay uses paisa)
//
//            // Setting merchant details (use your Razorpay key)
//            options.put("key", "rzp_test_e664V0FP0zQy7N");  // Your Razorpay key
//            options.put("name", "Merchant Name");
//            options.put("description", "Order Payment");
//            options.put("image", "https://your_merchant_logo_url.com");  // Optional logo
//
//            // Initialize Razorpay Checkout
//            Checkout checkout = new Checkout();
//            checkout.open(Payment.this, options);  // Launch Razorpay UI
//        } catch (Exception e) {
//            Toast.makeText(this, "Error initializing Razorpay: " + e.getMessage(), Toast.LENGTH_SHORT).show();
//        }
//    }

    // This will be called after the Razorpay payment result (success or failure)
    @Override
    public void onPaymentSuccess(String paymentId) {
        Toast.makeText(this, "Payment Successful. Payment ID: " + paymentId, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPaymentError(int code, String response) {
        Toast.makeText(this, "Payment Failed: " + response, Toast.LENGTH_SHORT).show();
    }
}
