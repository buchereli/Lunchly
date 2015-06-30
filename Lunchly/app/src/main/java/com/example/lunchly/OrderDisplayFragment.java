package com.example.lunchly;

import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Eli on 6/30/2015.
 */
public class OrderDisplayFragment extends ListFragment {

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String[] values = new String[10];
        Adapter adapter = new Adapter(getActivity(), values);
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Toast toast = Toast.makeText(getActivity(), ""+position, Toast.LENGTH_LONG);
        toast.show();
    }

    private class Adapter extends ArrayAdapter<String> {
        private final Context context;
        private final String[] values;

        public Adapter(Context context, String[] values) {
            super(context, R.layout.orders_adapter, values);
            this.context = context;
            this.values = values;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View itemView = inflater.inflate(R.layout.items_adapter, parent, false);

            TextView item = (TextView) itemView.findViewById(R.id.item);
            item.setText("Burrito");

            TextView cost = (TextView) itemView.findViewById(R.id.cost);
            cost.setText("$7.49");

            return itemView;
        }
    }
}
