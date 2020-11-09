package com.example.flowershopapp;

import android.app.Activity;
import android.content.Context;
import android.view.View;

public class Till {
    private Context mContext;
    //private Activity activity;

         public Till(Context context) {
             mContext = context;
      //       activity = (Activity) mContext;
      //       activity.setContentView(R.layout.till);
             Item item = new Item("Rose", 100.00, 0, mContext);
         }

}
