package com.example.flowershopapp;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.recyclerview.widget.LinearLayoutManager;

import static com.example.flowershopapp.StockManagement.stockItems;

public class AddItemStock {

    public Context mContext;
    public Activity activity;
    public Button addStock;
    public Button backButton;

    public AddItemStock(Context context) {

        mContext = context;
        activity = (Activity) mContext;
        activity.setContentView(R.layout.add_stock);
        addStock = activity.findViewById(R.id.addStockBotton);
        backButton = activity.findViewById(R.id.addStockBackButton);

        View.OnClickListener clickListener = v -> {
            switch (v.getId()) {
                case R.id.addStockBotton:
                    EditText nameEditText = activity.findViewById(R.id.editProductType);
                    String tempName = nameEditText.getText().toString();
                    EditText priceEditText = activity.findViewById(R.id.textEditPrice);
                    String tempPrice = priceEditText.getText().toString();
                    EditText quantityEditText = activity.findViewById(R.id.textEditQuantity);
                    String tempQuantity = quantityEditText.getText().toString();
                    if (tempName.equals("")||tempPrice.equals("")||tempQuantity.equals("")){
                        StockManagement stockManagement = new StockManagement(mContext);
                        break;
                    } else {
                        Item tempItem = new Item(tempName, Double.parseDouble(tempPrice), Integer.parseInt(tempQuantity));
                        stockItems.add(tempItem);
                    }
                    new StockManagement(mContext);
                    break;
                case R.id.addStockBackButton:
                    new StockManagement(mContext);
                    break;
                default:
                    break;
            }
        };
        addStock.setOnClickListener(clickListener);
        backButton.setOnClickListener(clickListener);
    }
}
