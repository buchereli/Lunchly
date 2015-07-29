package com.example.lunchly;

import android.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

/**
 * Created by Eli on 6/19/2015.
 */
public class OrdersFragment extends ListFragment {

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String[] values = Parser.parseOrdersToValues(FakeServerInfo.getOrders());
        Adapter adapter = new Adapter(getActivity(), values);
        setListAdapter(adapter);

        getListView().setDivider(null);
        getListView().setDividerHeight(50);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Intent myIntent = new Intent(getActivity(), OrderDisplayActivity.class);
//        myIntent.putExtra("key", value); //Optional parameters
        getActivity().startActivity(myIntent);
    }

    private class Adapter extends ArrayAdapter<String> {
        private final Context context;
        private final String[] values;

        public Adapter(Context context, String[] values) {
            super(context, R.layout.orders_adapter, values);
            this.context = context;
            this.values = values;
        }

        // Create ListView View
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view;
            if(position == 0)
                view = inflater.inflate(R.layout.details_view, parent, false);
            else {
                view = inflater.inflate(R.layout.orders_adapter, parent, false);

//            // Set Location
//            TextView location = (TextView) orderView.findViewById(R.id.location);
//            try {
//                location.setText(FakeServerInfo.getOrders().getJSONObject(position).getString("location"));
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }

                // Set Estimated Cost
//            TextView cost = (TextView) orderView.findViewById(R.id.cost);
//            try {
//                cost.setText(FakeServerInfo.getOrders().getJSONObject(position).getString("cost"));
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }

                // Set Profile Image
                ImageView imageView = (ImageView) view.findViewById(R.id.icon);
            }

            return view;
        }
    }
}
