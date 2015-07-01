package com.example.lunchly;

import android.app.Activity;
import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

/**
 * Created by Eli on 6/30/2015.
 */
public class OrderDisplayActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderdisplay);
    }

    public View createView(String item, String cost) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View orderView = inflater.inflate(R.layout.items_adapter, null);

        TextView itemView = (TextView) orderView.findViewById(R.id.item);
        itemView.setText(item);
        TextView costView = (TextView) orderView.findViewById(R.id.cost);
        costView.setText(cost);

        return orderView;
    }
}