package com.example.lunchly;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by Eli on 6/19/2015.
 */
public class OrdersFragment extends ListFragment {

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String[] values = Parser.parseOrdersToValues(FakeServerInfo.getOrders());
        OrdersAdapter adapter = new OrdersAdapter(getActivity(), values);
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Toast toast = Toast.makeText(getActivity(), ""+position, Toast.LENGTH_LONG);
        toast.show();
    }
}