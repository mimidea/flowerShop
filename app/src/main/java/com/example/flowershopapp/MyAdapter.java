package com.example.flowershopapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private ArrayList<Item> mDataset;
    public TextView finalPriceTextView;
    public static ArrayList<Integer> quantityValue = new ArrayList<>();
    public String quantityValueString = "";



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
        quantityValue.add(Integer.parseInt(quantityTextView.getText().toString()));
        System.out.println(quantityValue);
        View.OnClickListener clickListener = v -> {
            switch (v.getId()) {
                case R.id.minusButtonView:
                        decreaseQuantity(quantityTextView, position, v);
                    finalPriceTextView = holder.linearLayoutCompat.getRootView().findViewById(R.id.finalPriceTextView);
                    finalPriceTextView.setText(String.valueOf(calculateTotal(quantityValue)));
                        break;
                case R.id.plusButtonView:
                        increaseQuantity(quantityTextView, position, v, quantityInt);
                    finalPriceTextView = holder.linearLayoutCompat.getRootView().findViewById(R.id.finalPriceTextView);
                    finalPriceTextView.setText(String.valueOf(calculateTotal(quantityValue)));
                    break;
                default:
                    break;
            }


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
            quantityValue.set(position, Integer.parseInt(quantityTextView.getText().toString()));
            quantityValueString = "";
            for (int i = 0; i<quantityValue.size(); i++){
                if (i!=0){
                    quantityValueString+=",";
                }
                quantityValueString+= quantityValue.get(i).toString();
            }
            SharedPreferences app_preferences = PreferenceManager.getDefaultSharedPreferences(Till.mContext);
            SharedPreferences.Editor editor = app_preferences.edit();
            editor.putString("stock", quantityValueString);
            editor.apply();
        } else {
            String message = Till.mContext.getResources().getString(R.string.toastPartOne) + tempItem.getName() +
                    Till.mContext.getResources().getString(R.string.toastPartTwo)+ --flowersValue + Till.mContext.getResources().getString(R.string.dot);
            Toast.makeText(view.getContext(), message,Toast.LENGTH_SHORT).show();
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
            quantityValue.set(position, Integer.parseInt(quantityTextView.getText().toString()));
            quantityValueString = "";
            for (int i = 0; i<quantityValue.size(); i++){
                if (i!=0){
                    quantityValueString+=",";
                }
                quantityValueString+= quantityValue.get(i).toString();
            }
            SharedPreferences app_preferences = PreferenceManager.getDefaultSharedPreferences(Till.mContext);
            SharedPreferences.Editor editor = app_preferences.edit();
            editor.putString("stock", quantityValueString);
            editor.apply();
        } else {
            Toast.makeText(view.getContext(), Till.mContext.getResources().getString(R.string.msgNegQty),Toast.LENGTH_SHORT).show();
        }
    }

    private double calculateTotal (ArrayList quantityValue) {
        double totalToReturn = 0;
        for(int x=0; x<mDataset.size(); x++) {
            System.out.println(quantityValue);
            int quantityTotal =Integer.parseInt(quantityValue.get(x).toString());
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
