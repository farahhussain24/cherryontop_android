package com.example.cherryontop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;


import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MenuActivity extends AppCompatActivity {

    RecyclerView rV;
    LinearLayoutManager LayoutManager;
    MenuAdapter adapter;
    List<MenuItems> data;

    // for loading bubble symbol
    ProgressDialog progressDialog;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.top_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.cartIconMenuOnTop:
                Intent intentCart = new Intent(MenuActivity.this, ShowCartActivity.class);
                startActivity(intentCart);
                return true;
            case R.id.logoutBtn:
                //for progress dialog
                progressDialog = new ProgressDialog(MenuActivity.this);
                progressDialog.setTitle("Log Out");
                progressDialog.setMessage("Logging Out.");
                //starting the progress dialog
                progressDialog.show();
                Intent intentMain = new Intent(MenuActivity.this, MainActivity.class);
                startActivity(intentMain);
                // closing progress dialog
                progressDialog.dismiss();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        this.setTitle("Menu");

        initData();
        initRecyclerView();
    }

    private void initData() {
        data = new ArrayList<>();
        data.add(new MenuItems(R.drawable.item1, "Choco Cake", "1000"));
        data.add(new MenuItems(R.drawable.item3, "Choco Shake", "250"));
        data.add(new MenuItems(R.drawable.item4, "Cherry Cake", "900"));
        data.add(new MenuItems(R.drawable.item5, "Oreo Shake", "300"));
        data.add(new MenuItems(R.drawable.item7, "Cheese cake", "1400"));
        data.add(new MenuItems(R.drawable.item8, "Red velvet cake", "1200"));
        data.add(new MenuItems(R.drawable.item9, "Berry Brownie", "500"));
        data.add(new MenuItems(R.drawable.item10, "Choco Cake popsicles", "1100"));
        data.add(new MenuItems(R.drawable.item11, "Lava Cake", "700"));
        data.add(new MenuItems(R.drawable.item12, "Apple pie", "400"));
        data.add(new MenuItems(R.drawable.item13, "Cherry On Top Special", "1500"));

    }

    private void initRecyclerView() {
        rV = findViewById(R.id.rV);
        rV.addItemDecoration(new DividerItemDecoration(rV.getContext(), DividerItemDecoration.VERTICAL));
        rV.setLayoutManager(new LinearLayoutManager(this));

        adapter = new MenuAdapter(data);
        rV.setAdapter(adapter);
        adapter.notifyDataSetChanged();


    }
}