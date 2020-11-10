package com.example.flowershopapp;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Till {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private Context mContext;
    private Activity activity;

    public ArrayList<Item> tillItems = new ArrayList<Item>();

         public Till(Context context) {
             Item item = new Item("Rose", 100.00, 0, mContext);
             Item item2 = new Item("Muscata", 5, 0, mContext);
             tillItems.add(item);
             tillItems.add(item2);

             mContext = context;
             activity = (Activity) mContext;
             activity.setContentView(R.layout.till);
             recyclerView = (RecyclerView) activity.findViewById(R.id.my_recycler_view);





             recyclerView.setHasFixedSize(true);
             layoutManager = new LinearLayoutManager(mContext);
             recyclerView.setLayoutManager(layoutManager);

             mAdapter = new MyAdapter(tillItems);
             recyclerView.setAdapter(mAdapter);


         }

}
