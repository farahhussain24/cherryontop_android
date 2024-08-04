package com.example.cherryontop;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {

    public static ArrayList<CartDataModel> cartList = new ArrayList<CartDataModel>();
    private List<MenuItems> data;
    private int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public MenuAdapter(List<MenuItems> data) {
        this.data = data;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int resource = data.get(position).getImage();
        String name = data.get(position).getName();
        String price = data.get(position).getPrice();


        holder.itemImage.setImageResource(resource);
        holder.cartItemName.setText(name);
        holder.cartItemPrice.setText(price);

        //        button add to cart
        holder.addCartImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cartList.add(new CartDataModel(name, price));

                Toast.makeText(v.getContext(),"Item added to your cart" , Toast.LENGTH_SHORT).show();

            }

        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView itemImage;
        private TextView cartItemName;
        private TextView cartItemPrice;
        ImageView addCartImgBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemImage = itemView.findViewById(R.id.itemImage);
            cartItemName = itemView.findViewById(R.id.cartItemName);
            cartItemPrice = itemView.findViewById(R.id.cartItemPrice);
            addCartImgBtn = (ImageView) itemView.findViewById(R.id.addCartImgBtn);

        }
    }
}
