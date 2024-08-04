package com.example.cherryontop;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterCartItem extends RecyclerView.Adapter<AdapterCartItem.ViewHolder> {

    private ArrayList<CartDataModel> arrayList;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView cartItemName;
        TextView cartItemPrice;

        public ViewHolder(View view) {
            super(view);
            cartItemName = (TextView) view.findViewById(R.id.cartItemName);
            cartItemPrice = (TextView) view.findViewById(R.id.cartItemPrice);
        }
    }

    public AdapterCartItem(ArrayList<CartDataModel> dataSet) {
        arrayList = dataSet;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.cart_item_row, viewGroup, false);

        return new ViewHolder(view);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.cartItemName.setText(arrayList.get(position).getItemName());
        viewHolder.cartItemPrice.setText(arrayList.get(position).getPrice());
    }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
