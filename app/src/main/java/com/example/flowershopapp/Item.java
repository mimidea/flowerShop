package com.example.flowershopapp;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Item implements View.OnClickListener {
    private String name;
    private double price;
    private int quantity;
    private Button plusButton;
    private Button minusButton;
    private TextView nameTextView;
    private TextView priceTextView;
    private TextView quantityTextView;
    private Context mContext;
    private Activity activity;


    public Item (String name, double price, int quantity, Context context){
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        mContext = context;
        activity = (Activity) mContext;
        activity.setContentView(R.layout.till);
        plusButton = (Button) activity.findViewById(R.id.plusButtonView);
        plusButton.setOnClickListener(this);
        minusButton = (Button) activity.findViewById(R.id.minusButton);
        minusButton.setOnClickListener(this);
        nameTextView = (TextView) activity.findViewById(R.id.nameTextView);
        priceTextView = (TextView) activity.findViewById(R.id.priceTextView);
        quantityTextView = (TextView) activity.findViewById(R.id.quantityTextView);

        setNameTextView(name);
        setPriceTextView(price);
        setQuantityTextView(quantity);

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

    private void getNameFromTextView () {
        name = nameTextView.getText().toString();
    }
    private void getPriceFromTextView () {
        price = Double.parseDouble(priceTextView.getText().toString());
    }
    private void getQuantityFromTextView() {
        quantity = Integer.parseInt(quantityTextView.getText().toString());
    }

    private void setNameTextView (String name) {
        nameTextView.setText(name);
    }

    private void setPriceTextView (double price) {
        String barca = String.valueOf(price);
        priceTextView.setText(barca);
    }
    private void setQuantityTextView (int quantity) {
        String barca = String.valueOf(quantity);
        quantityTextView.setText(barca);
    }
    private int increaseQuantity() {
        if (quantity<100) {
            quantity++;
        }
        return quantity;
    }
    private int decreaseQuantity () {
        if (quantity>0) {
            quantity--;

        }
        return quantity;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.plusButtonView:
                increaseQuantity();
                break;
            case R.id.minusButton:
                decreaseQuantity();
                break;
            default:
                break;
        }
        setQuantityTextView(quantity);

    }

}




