package com.example.flowershopapp;

import android.accessibilityservice.GestureDescription;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.appcompat.app.ActionBar;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Till  {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private Receipt receipt;
    public static Context mContext;
    public Activity activity;
    public Button emailButton;
    public Button backButton;
    public ArrayList<Integer> order = new ArrayList<>();
    public ArrayList<Item> tillItems = StockManagement.stockItems;



    public Till(Context context) {
        mContext = context;
        activity = (Activity) mContext;
        activity.setContentView(R.layout.till);
        recyclerView = activity.findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new MyAdapter(tillItems);
        recyclerView.setAdapter(mAdapter);
        emailButton = activity.findViewById(R.id.emailButton);
        backButton = activity.findViewById(R.id.tillBackButton);

        View.OnClickListener clickListener = v -> {
            String subject = String.valueOf(R.string.recipt);
            switch (v.getId()) {
                case R.id.emailButton:
                    restoreArrayList();
                    String receipt = "";
                    double total =0;
                    System.out.println(order.size() + " " + tillItems.size());
                    for (int i = 0; i < order.size(); i++){
                        if (order.get(i)!=0) {
                            Item temp = tillItems.get(i);
                            double tempValue = temp.getPrice()*order.get(i);
                            tillItems.get(i).setQuantity(tillItems.get(i).getQuantity()-order.get(i));
                            receipt += (temp.getName() + " : " + order.get(i) + " x £" + temp.getPrice() + " = " + tempValue + "\n");
                            total+=tempValue;
                        }
                    }
                    receipt += "Total = " + total;
                    System.out.print(receipt);
                    Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                            "mailto", "", null));
                    intent.putExtra(Intent.EXTRA_SUBJECT, subject);
                    intent.putExtra(Intent.EXTRA_TEXT, receipt);
                    activity.startActivity(Intent.createChooser(intent, "Choose an Email client :"));
                    break;
                case R.id.tillBackButton:
                    StockManagement stockManagement = new StockManagement(context);
                    break;
                default:
                    break;
            }
        };
        emailButton.setOnClickListener(clickListener);
        backButton.setOnClickListener(clickListener);
    }

    public void restoreArrayList (){
        SharedPreferences app_preferences = PreferenceManager.getDefaultSharedPreferences(Till.mContext);
        String tempString = app_preferences.getString("stock", "1");
        if (tempString != null) {
                List<String> splitValues = Arrays.asList(tempString.split(","));
                for (int i = 0; i < splitValues.size(); i ++) {
                    order.add(Integer.parseInt(splitValues.get(i)));
                }
            }
        }
    }
