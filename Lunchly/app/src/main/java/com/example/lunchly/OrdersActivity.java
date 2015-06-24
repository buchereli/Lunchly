package com.example.lunchly;

import android.app.Activity;
import android.app.ListActivity;
import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Eli on 6/19/2015.
 */
public class OrdersActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        FakeServerInfo.updateOrders();

        TextView timeTillDrop = (TextView) findViewById(R.id.timeTillDrop);
        TextView numberOfOrders = (TextView) findViewById(R.id.numberOfOrders);
        TextView totalCost = (TextView) findViewById(R.id.totalCost);
        TextView totalProfit = (TextView) findViewById(R.id.totalProfit);

        timeTillDrop.setText("1:21");
        numberOfOrders.setText(OrdersInfo.numberOfOrders()+"");
        totalCost.setText(OrdersInfo.totalCost());
        totalProfit.setText((OrdersInfo.numberOfOrders() * 0.00)+"");
    }

}
