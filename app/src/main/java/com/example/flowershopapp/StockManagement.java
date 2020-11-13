package com.example.flowershopapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StockManagement {
        private RecyclerView recyclerView;
        private static RecyclerView.Adapter mAdapter;
        private static RecyclerView.LayoutManager layoutManager;

        public Context mContext;
        public Activity activity;
        public Button addButton;
        public Button backButton;

        public static ArrayList<Item> stockItems = new ArrayList<>();

        public StockManagement(Context context) {
                mContext = context;
                activity = (Activity) mContext;
                activity.setContentView(R.layout.stock_management);
                recyclerView = activity.findViewById(R.id.my_recycler_stockView);
                recyclerView.setHasFixedSize(true);
                layoutManager = new LinearLayoutManager(mContext);
                recyclerView.setLayoutManager(layoutManager);
                mAdapter = new MyStockAdapter(stockItems);
                recyclerView.setAdapter(mAdapter);
                addButton = activity.findViewById(R.id.addNew);
                backButton = activity.findViewById(R.id.stockMangementBackButton);

                View.OnClickListener clickListener = v -> {
                        switch (v.getId()) {
                                case R.id.addNew:
                                        AddItemStock addItemStock = new AddItemStock(mContext);
                                        break;
                                case R.id.stockMangementBackButton:
                                        mContext=activity.getApplicationContext();
                                        new Till(context);
                                        break;
                                default:
                                        break;
                        }
                };
                addButton.setOnClickListener(clickListener);
                backButton.setOnClickListener(clickListener);
        }

        public static void updateAdapter(int position){
                mAdapter.notifyItemRemoved(position);
                mAdapter.notifyItemRangeChanged(position, stockItems.size());
        }
}