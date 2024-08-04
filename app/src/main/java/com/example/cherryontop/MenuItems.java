package com.example.cherryontop;
// This is a model class for the menu items data

public class MenuItems {

    private int image;
    private String name;
    private String price;

    public void setImage(int image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public MenuItems(int imageview, String foodName, String foodPrice) {
        this.image = imageview;
        this.name = foodName;
        this.price = foodPrice;
    }

    //empty constructor for database
    public MenuItems() {}


    public int getImage() { return image; }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }
}
