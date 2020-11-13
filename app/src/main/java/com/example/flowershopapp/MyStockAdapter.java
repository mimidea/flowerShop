package com.example.flowershopapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyStockAdapter extends RecyclerView.Adapter<MyStockAdapter.MyViewHolder> {

    private ArrayList<Item> mDataset;
    public TextView finalPriceTextView;
    public ArrayList<Integer> quantityValue= new ArrayList<>();


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public LinearLayoutCompat linearLayoutCompat;

        public MyViewHolder(LinearLayoutCompat v) {
            super(v);
            linearLayoutCompat = v;
        }
    }

    public MyStockAdapter(ArrayList<Item> mDataset) {
       this.mDataset= mDataset;
    }

    public MyStockAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LinearLayoutCompat v = (LinearLayoutCompat) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_stock_management, parent, false);
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
        quantityTextView.setText(String.valueOf(quantityInt));
        ImageView binView = holder.linearLayoutCompat.findViewById(R.id.deleteView);
        Button plusButton = holder.linearLayoutCompat.findViewById(R.id.plusButtonView);
        Button minusButton = holder.linearLayoutCompat.findViewById(R.id.minusButtonView);
        quantityValue.add(Integer.parseInt(quantityTextView.getText().toString()));
        View.OnClickListener clickListener = v -> {
            switch (v.getId()) {
                case R.id.minusButtonView:
                        decreaseQuantity(quantityTextView, position, v);
                        break;
                case R.id.plusButtonView:
                        increaseQuantity(quantityTextView, position, v, quantityInt);
                    break;
                case R.id.deleteView:
                         deleteItem(position);
                         break;
                default:
                    break;
            }
        };

        plusButton.setOnClickListener(clickListener);
        minusButton.setOnClickListener(clickListener);
        binView.setOnClickListener(clickListener);
    }

    private void deleteItem(int position) {
        StockManagement.stockItems.remove(position);
        StockManagement.updateAdapter(position);
    }

    private void increaseQuantity(TextView quantityTextView, int position, View view, int quantity) {
        int flowersValue = Integer.parseInt(quantityTextView.getText().toString());
        flowersValue++;
            mDataset.get(position).setQuantity(flowersValue);
            quantityValue.set(position, Integer.parseInt(quantityTextView.getText().toString()));
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
        } else {
            Toast.makeText(view.getContext(), String.valueOf(R.string.msgNegQty),Toast.LENGTH_SHORT).show();
        }
    }


    public int getItemCount(){
        return mDataset.size();
    }
}
