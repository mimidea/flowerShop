package com.example.flowershopapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public StockManagement stockManagement;
    public Till till;
    public Button tillButton;
    public Button stockManagementButton;
    public Context mContext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences app_preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String tempString = app_preferences.getString("stock", "1");
        restoreArrayList(tempString);

        setContentView(R.layout.activity_main);

        tillButton = findViewById(R.id.tillButton);
        tillButton.setOnClickListener(this);
        stockManagementButton = findViewById(R.id.stockManagementButton);
        stockManagementButton.setOnClickListener(this);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        String saveString = "";
        for (int i = 0; i < StockManagement.stockItems.size(); i++) {
            if (i != 0) {
                saveString += "," + StockManagement.stockItems.get(i).getName() + "," + StockManagement.stockItems.get(i).
                        getPrice() + "," + StockManagement.stockItems.get(i).getQuantity();
            } else {
                saveString += StockManagement.stockItems.get(i).getName() + "," + StockManagement.stockItems.get(i).
                        getPrice() + "," + StockManagement.stockItems.get(i).getQuantity();
            }
        }
        savedInstanceState.putString("array", saveString);
        SharedPreferences app_preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = app_preferences.edit();
        editor.putString("stock", saveString);
        editor.apply();
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        SharedPreferences app_preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String tempString = app_preferences.getString("stock", "1");
        restoreArrayList(tempString);
    }

    //A method that opens the till
    public void openTill() {
        till = new Till(mContext);
    }

    //A method that opens the Stock Management
    public void openStockManagement() {
        stockManagement = new StockManagement(mContext);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tillButton:
                openTill();
                break;
            case R.id.stockManagementButton:
                openStockManagement();
                break;
            default:
                break;
        }
    }

    public void restoreArrayList (String tempString){
        if (tempString != null) {
            if (StockManagement.stockItems.size() != 0) {
                List<String> splitValues = Arrays.asList(tempString.split(","));
                for (int i = 0; i < splitValues.size(); i += 3) {
                    String tempName = splitValues.get(i);
                    double tempPrice = Double.parseDouble(splitValues.get(i + 1));
                    int tempQuant = Integer.parseInt(splitValues.get(i + 2));
                    Item tempItem = new Item(tempName, tempPrice, tempQuant);
                    StockManagement.stockItems.add(tempItem);
                }
            }
        }
    }

    public void returnToStart(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
