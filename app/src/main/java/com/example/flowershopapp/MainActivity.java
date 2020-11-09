package com.example.flowershopapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public StockManagement stockManagement;
    public Till till;
    public Button tillButton;
    public Button stockManagementButton;
    public Context mContext = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View v = this.findViewById(android.R.id.content).getRootView();


        tillButton = (Button) findViewById(R.id.tillButton);
        tillButton.setOnClickListener(this);
        stockManagementButton = (Button) findViewById(R.id.stockManagementButton);
        stockManagementButton.setOnClickListener(this);


    }



    //A method that opens the till
    public void openTill () {
        till=new Till(mContext);


    }




    //A method that opens the Stock Management
    public void openStockManagement() {
        stockManagement =new StockManagement(mContext);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tillButton:
                openTill();
                break;
            case R.id.stockManagementButton  :
                openStockManagement();
                break;
            default:
                break;


        }

    }
}
