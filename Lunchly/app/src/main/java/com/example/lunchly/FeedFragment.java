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

import org.json.JSONException;

/**
 * Created by Eli on 6/19/2015.
 */
public class FeedFragment extends ListFragment {

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String[] values = new String[6];
        Adapter adapter = new Adapter(getActivity(), values);
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
//        Intent myIntent = new Intent(getActivity(), OrderDisplayActivity.class);
////        myIntent.putExtra("key", value); //Optional parameters
//        getActivity().startActivity(myIntent);
    }

    private class Adapter extends ArrayAdapter<String> {
        private final Context context;
        private final String[] values;

        public Adapter(Context context, String[] values) {
            super(context, R.layout.feed_adapter, values);
            this.context = context;
            this.values = values;
        }

        // Create ListView View
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View feedView = inflater.inflate(R.layout.feed_adapter, parent, false);

            return feedView;
        }
    }
}
