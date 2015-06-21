package com.example.lunchly;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;

/**
 * Created by Eli on 6/19/2015.
 */
public class OrdersAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] values;

    public OrdersAdapter(Context context, String[] values) {
        super(context, R.layout.orders_adapter, values);
        this.context = context;
        this.values = values;
    }

    // Create ListView View
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View orderView = inflater.inflate(R.layout.orders_adapter, parent, false);

        // Set Location
        TextView location = (TextView) orderView.findViewById(R.id.location);
        try {
            location.setText(FakeServerInfo.getOrders().getJSONObject(position).getString("location"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Set Estimated Cost
        TextView cost = (TextView) orderView.findViewById(R.id.cost);
        try {
            cost.setText(FakeServerInfo.getOrders().getJSONObject(position).getString("cost"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Set Profile Image
        ImageView imageView = (ImageView) orderView.findViewById(R.id.icon);

        return orderView;
    }
}
