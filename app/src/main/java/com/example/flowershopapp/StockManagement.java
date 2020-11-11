package com.example.flowershopapp;

import android.app.Activity;
import android.content.Context;

public class StockManagement {
        private Context mContext;
        private Activity activity;

        public StockManagement (Context mContext) {
                activity = (Activity) mContext;
                activity.setContentView(R.layout.stock_management);
        }

}
