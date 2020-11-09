package com.example.flowershopapp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class StockManagement {
        private Context mContext;
        private Activity activity;

        public StockManagement (Context mContext) {
                activity = (Activity) mContext;
                activity.setContentView(R.layout.stock_management);
        }

}
