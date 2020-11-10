package com.example.flowershopapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private ArrayList<Item> mDataset;



    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ArrayList<Item> mDataset = new ArrayList<Item>();
        public LinearLayoutCompat linearLayoutCompat;
        Button plusButton;
        Button minusButton;
        TextView quantityTextView;

        public MyViewHolder(LinearLayoutCompat v) {
            super(v);
            linearLayoutCompat = v;
            Button plusButton = (Button) linearLayoutCompat.findViewById(R.id.plusButtonView);
            Button minusButton = (Button) linearLayoutCompat.findViewById(R.id.minusButtonView);
            TextView quantityTextView = (TextView) linearLayoutCompat.findViewById(R.id.quantityTextView);
            plusButton.setOnClickListener(this);
            minusButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.minusButtonView:
                    quantityTextView = (TextView) linearLayoutCompat.findViewById(R.id.quantityTextView);
                    int tempQuantity = Integer.parseInt(quantityTextView.getText().toString());
                    if (tempQuantity>0) {
                        decreaseQuantity();
                    }
                    break;
                case R.id.plusButtonView:
                    quantityTextView = (TextView) linearLayoutCompat.findViewById(R.id.quantityTextView);
                    int tempiQuan = Integer.parseInt(quantityTextView.getText().toString());
                    if (tempiQuan<100){
                        increaseQuantity();
                    }
                    break;
                default: break;
            }
        }


        private void increaseQuantity() {
                System.out.println("hi");
        }

        private void decreaseQuantity() {
                System.out.println("yeah bitch");
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
        TextView nameTextView = (TextView) holder.linearLayoutCompat.findViewById(R.id.nameTextView);
        nameTextView.setText(mDataset.get(position).getName());
        TextView priceTextView = (TextView) holder.linearLayoutCompat.findViewById(R.id.priceTextView);
        String priceString = String.valueOf(mDataset.get(position).getPrice());
        priceTextView.setText(priceString);
        TextView quantityTextView = (TextView) holder.linearLayoutCompat.findViewById(R.id.quantityTextView);
        String quantityString = String.valueOf(mDataset.get(position).getQuantity());
        quantityTextView.setText(quantityString);
    }

    public int getItemCount(){
        return mDataset.size();

    }
}
