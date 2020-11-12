package com.example.flowershopapp;

import android.accessibilityservice.GestureDescription;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Till {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    public Context mContext;
    public Activity activity;
    public Button emailButton;


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

        View.OnClickListener clickListener = v -> {
            String subject = String.valueOf(R.string.recipt);
            String message = "";
            switch (v.getId()) {
                case R.id.emailButton:
                    Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                            "mailto", "", null));
                    intent.putExtra(Intent.EXTRA_SUBJECT, subject);
                    intent.putExtra(Intent.EXTRA_TEXT, message);
                    activity.startActivity(Intent.createChooser(intent, "Choose an Email client :"));
                    break;
                default:
                    break;
            }
        };
        emailButton.setOnClickListener(clickListener);
    }
}