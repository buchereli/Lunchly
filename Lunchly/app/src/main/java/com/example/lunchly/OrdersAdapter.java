package com.example.lunchly;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View orderView = inflater.inflate(R.layout.orders_adapter, parent, false);
        TextView location = (TextView) orderView.findViewById(R.id.location);
        TextView cost = (TextView) orderView.findViewById(R.id.cost);
        cost.setText("$0.00");
        ImageView imageView = (ImageView) orderView.findViewById(R.id.icon);
        System.err.println(orderView.getHeight());
//        imageView.getLayoutParams().width = imageView.getHeight();
        location.setText(values[position]);

        return orderView;
    }
}
