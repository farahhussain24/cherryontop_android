package com.example.cherryontop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class CategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        //        Transparent action bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                (WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS));
        getSupportActionBar().hide();
    }

    public void goToMenu(View view) {
        Intent intent = new Intent(CategoryActivity.this, MenuActivity.class);
        startActivity(intent);
    }
}