package com.example.cherryontop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;


public class ShowCartActivity extends AppCompatActivity {
    RecyclerView rV;
    Button buyNowBtn;

    public static final String TOTAL_PRICE = "TOTAL_PRICE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_cart);

        //        Transparent action bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                (WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS));
        getSupportActionBar().hide();

        rV = findViewById(R.id.rV);
        buyNowBtn = findViewById(R.id.buyNowBtn);
        ArrayList<CartDataModel> arrayList = new ArrayList<CartDataModel>();
        arrayList = MenuAdapter.cartList;

        if (arrayList.size() == 0) {
            Toast.makeText(ShowCartActivity.this, "Cart is empty. Nothing to show" , Toast.LENGTH_SHORT).show();

        }
        else {
            buyNowBtn.setVisibility(View.VISIBLE); //SHOW the button
            // for recycler view
            rV.addItemDecoration(new DividerItemDecoration(rV.getContext(), DividerItemDecoration.VERTICAL));
            rV.setLayoutManager(new LinearLayoutManager(this));

            AdapterCartItem customAdapter = new AdapterCartItem(arrayList);
            rV.setAdapter(customAdapter);



            // on below line we are creating a method to create item touch helper
            // method for adding swipe to delete functionality.
            // in this we are specifying drag direction and position to right
            ArrayList<CartDataModel> finalArrayList = arrayList;
            new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
                @Override
                public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                    // this method is called
                    // when the item is moved.
                    return false;
                }

                @Override
                public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                    // this method is called when we swipe our item to right direction.
                    // on below line we are getting the item at a particular position.
                    CartDataModel cartDeletedItem = finalArrayList.get(viewHolder.getBindingAdapterPosition());

                    // below line is to get the position
                    // of the item at that position.
                    int position = viewHolder.getBindingAdapterPosition();

                    // this method is called when item is swiped.
                    // below line is to remove item from our array list.
                    finalArrayList.remove(viewHolder.getBindingAdapterPosition());

                    // below line is to notify our item is removed from adapter.
                    customAdapter.notifyItemRemoved(viewHolder.getBindingAdapterPosition());

                    // below line is to display our snackbar with action.
                    Snackbar.make(rV, cartDeletedItem.getItemName(), Snackbar.LENGTH_LONG).setAction("Undo", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // adding on click listener to our action of snack bar.
                            // below line is to add our item to array list with a position.
                            finalArrayList.add(position, cartDeletedItem);

                            // below line is to notify item is
                            // added to our adapter class.
                            customAdapter.notifyItemInserted(position);
                        }
                    }).show();
                }
                // at last we are adding this
                // to our recycler view.
            }).attachToRecyclerView(rV);





        }
    }

    public void buy(View view) {
        ArrayList<CartDataModel> arrayList = new ArrayList<CartDataModel>();
        arrayList = MenuAdapter.cartList;
        if (arrayList.size() == 0) {
            Toast.makeText(ShowCartActivity.this, "No bill yet is made.", Toast.LENGTH_SHORT).show();
        }
        else {
            ArrayList<Double> prices = new ArrayList<Double>();
            for (CartDataModel element : arrayList) {
                prices.add(Double.parseDouble(element.getPrice()));
            }
            double sum = 0.0;
            for (double value : prices) {
                sum += value;
            }

            Intent intent = new Intent(this, PaymentActivity.class);
            intent.putExtra(TOTAL_PRICE, String.valueOf(sum));
            startActivity(intent);
        }


    }
}

