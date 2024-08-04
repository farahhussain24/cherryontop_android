package com.example.cherryontop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

public class PaymentActivity extends AppCompatActivity {
    TextView billAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        //        Transparent action bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                (WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS));
        getSupportActionBar().hide();

        billAmount = findViewById(R.id.billAmount);
        Intent intent = getIntent();
        String price = intent.getStringExtra(ShowCartActivity.TOTAL_PRICE);
        billAmount.setText("Rs. " + price);
    }

    public void cashFunc(View view) {
        Intent intent = new Intent(this, CashOnActivity.class);
        startActivity(intent);
    }
}