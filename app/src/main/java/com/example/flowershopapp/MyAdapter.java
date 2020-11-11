package com.example.flowershopapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private ArrayList<Item> mDataset;
    public TextView finalPriceTextView;


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private ArrayList<Item> mDataset = new ArrayList<Item>();
        public LinearLayoutCompat linearLayoutCompat;


        public MyViewHolder(LinearLayoutCompat v) {
            super(v);
            linearLayoutCompat = v;
        }
    }

    public MyAdapter(ArrayList<Item> mDataset) {
       this.mDataset= mDataset;
    }

    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LinearLayoutCompat v = (LinearLayoutCompat) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_stock, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
                return vh;
    }

    public void onBindViewHolder(MyViewHolder holder, int position) {
        TextView nameTextView = holder.linearLayoutCompat.findViewById(R.id.nameTextView);
        nameTextView.setText(mDataset.get(position).getName());
        TextView priceTextView = holder.linearLayoutCompat.findViewById(R.id.priceTextView);
        String priceString = String.valueOf(mDataset.get(position).getPrice());
        priceTextView.setText(priceString);
        TextView quantityTextView = holder.linearLayoutCompat.findViewById(R.id.quantityTextView);
        int quantityInt = mDataset.get(position).getQuantity();
        quantityTextView.setText(String.valueOf(0));
        Button plusButton = holder.linearLayoutCompat.findViewById(R.id.plusButtonView);
        Button minusButton = holder.linearLayoutCompat.findViewById(R.id.minusButtonView);
        View.OnClickListener clickListener = v -> {
            switch (v.getId()) {
                case R.id.minusButtonView:
                        decreaseQuantity(quantityTextView, position, v);
                        break;
                case R.id.plusButtonView:
                        increaseQuantity(quantityTextView, position, v, quantityInt);
                    break;
                default:
                    break;
            }
            finalPriceTextView = holder.linearLayoutCompat.getRootView().findViewById(R.id.finalPriceTextView);
            finalPriceTextView.setText(String.valueOf(calculateTotal(Integer.parseInt(quantityTextView.getText().toString()))));
        };

        plusButton.setOnClickListener(clickListener);
        minusButton.setOnClickListener(clickListener);
    }

    private void increaseQuantity(TextView quantityTextView, int position, View view, int quantity) {
        int flowersValue = Integer.parseInt(quantityTextView.getText().toString());
        flowersValue++;
        Item tempItem = mDataset.get(position);
        if (flowersValue<=quantity){
            String flowersValueString = String.valueOf(flowersValue);
            quantityTextView.setText(flowersValueString);
            tempItem.setQuantity(flowersValue);
            mDataset.set(position, tempItem);
        } else {
            Toast.makeText(view.getContext(), "There are no more " + tempItem.getName() +
                    " available. Your current stock level is " + --flowersValue + ".",Toast.LENGTH_SHORT).show();
        }
    }

    private void decreaseQuantity(TextView quantityTextView, int position, View view) {
        int flowersValue = Integer.parseInt(quantityTextView.getText().toString());
        flowersValue--;
        if (flowersValue>=0){
            String flowersValueString = String.valueOf(flowersValue);
            quantityTextView.setText(flowersValueString);
            Item tempItem = mDataset.get(position);
            tempItem.setQuantity(flowersValue);
            mDataset.set(position, tempItem);
        } else {
            Toast.makeText(view.getContext(), "You can't sell a negative quantity of flowers!",Toast.LENGTH_SHORT).show();
        }
    }

    private double calculateTotal (int quantityValue) {
        double totalToReturn = 0;
        for(int x=0; x<mDataset.size(); x++) {
            int quantityTotal = quantityValue;
            double priceTotal = mDataset.get(x).getPrice();
            double finalTotal = quantityTotal * priceTotal;
            totalToReturn += finalTotal;
        }
        return totalToReturn;
    }



    public int getItemCount(){
        return mDataset.size();
    }
}
