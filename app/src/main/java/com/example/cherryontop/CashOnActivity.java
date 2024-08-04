package com.example.cherryontop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

public class CashOnActivity extends AppCompatActivity {

    EditText userName;
    EditText userAddress;
    EditText userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_on);

        //        Transparent action bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                (WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS));
        getSupportActionBar().hide();

        userName = findViewById(R.id.userName);
        userAddress = findViewById(R.id.userAddress);
        userEmail = findViewById(R.id.userEmail);
    }

    public void placeOrder(View view) {
        String name = userName.getText().toString();
        String address = userAddress.getText().toString();
        String mail = userEmail.getText().toString();

        if (name.isEmpty() || address.isEmpty() || mail.isEmpty()){
            Toast.makeText(CashOnActivity.this, "Please enter valid information.", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(CashOnActivity.this, "Your Order has been placed!", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(CashOnActivity.this, HomeActivity.class);
            startActivity(intent);
        }

    }
}