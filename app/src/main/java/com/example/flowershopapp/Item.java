package com.example.flowershopapp;

import android.app.Activity;
import android.content.Context;

public class Item {
    private String name;
    private double price;
    private int quantity;
    private Context mContext;
    private Activity activity;


    public Item (String name, double price, int quantity, Context context){
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        mContext = context;
        activity = (Activity) mContext;
    }

    public String getName() {
        return name;
    }

    public double getPrice(){
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setName(String name) {
        this.name= name;
    }

    public void setPrice(double price) {
        this.price= price;
    }

    public void setQuantity(int quantity) {
        this.quantity=quantity;
    }
}




