package com.example.flowershopapp;

import android.app.Activity;
import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

public class Item  implements Parcelable {
    private String name;
    private double price;
    private int quantity;
    private Context mContext;
    private Activity activity;


    public Item (String name, double price, int quantity){
        this.name = name;
        this.price = price;
        this.quantity = quantity;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeDouble(price);
        dest.writeInt(quantity);
    }
}




