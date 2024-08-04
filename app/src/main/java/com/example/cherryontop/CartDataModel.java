package com.example.cherryontop;

public class CartDataModel {
    String itemName;
    String price;
    public String getItemName() { return itemName; }
    public String getPrice() {
        return price;
    }

    public CartDataModel(String title, String price) {
        this.itemName = title;
        this.price = price;
    }
}
