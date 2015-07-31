package com.example.lunchly;

import android.app.Fragment;
import android.app.ListFragment;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Eli on 7/30/2015.
 */
public class MenuFragment extends ListFragment {

    String line;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String[] values  = {
                "*DD Combos|Combos Include Medium Hot Coffee",
                "2 Donuts|$3.59|Donut",
                "Muffin|$3.39|Muffin",
                "Bagel with Cream Cheese|$3.69|Bagel",
                "Sausage, Egg & Cheese on a Croissant - with Hash Browns|$5.79|Croissant",
                "Bacon, Egg & Cheese on a Bagel - with Hash Browns|$5.79|Bagel",
                "Veggie Egg White Flatbread|$4.59|Sandwich",
                "Ham, Egg & Cheese on an English Muffin - with Hash Browns|$5.79|Sandwich",
                "Big n' Toasted - with Hash Browns|$6.19|Sandwich",
                "Wake-Up Wrap (Bacon, Ham, or Sausage)|$3.09|Wrap",
                "Turkey Sausage Flatbread with Hash Browns|$5.79|Sandwich" };

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
            super(context, R.layout.activity_menu, values);
            this.context = context;
            this.values = values;
        }

        // Create ListView View
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view;

            line = values[position];

            if(line.startsWith("*")) {
                view = inflater.inflate(R.layout.menu_header_adapter, parent, false);

                line = line.substring(1);

                TextView header = (TextView) view.findViewById(R.id.header);
                TextView subtext = (TextView) view.findViewById(R.id.subtext);

                header.setText(getNext());
                subtext.setText(getNext());
            }
            else {
                view = inflater.inflate(R.layout.menu_item_adapter, parent, false);

                TextView item = (TextView) view.findViewById(R.id.item);
                TextView cost = (TextView) view.findViewById(R.id.cost);
                ImageView image = (ImageView) view.findViewById(R.id.image);

                item.setText(getNext());
                cost.setText(getNext());
                image.setImageBitmap(getImage(getNext()));
            }

            return view;
        }

        public String getNext(){
            int i = 1;

            while(i < line.length() && line.charAt(i) != '|')
                i++;

            String item = line.substring(0, i);
            if(i != line.length())
                line = line.substring(i+1);

            return item;
        }

        public Bitmap getImage(String type){

            Point start = new Point(0, 0);

            if(type.equals("Donut") || type.equals("Muffin"))
                start = new Point(297, 335);
            if(type.equals("Bagel") || type.equals("Croissant") || type.equals("Flatbread") || type.equals("Sandwich") || type.equals("Wrap"))
                start = new Point(0, 95);

            InputStream is = getResources().openRawResource(R.raw.food_icons);
            Bitmap sheet = BitmapFactory.decodeStream(is);
            Bitmap image = Bitmap.createBitmap(sheet, start.x, start.y, 99, 105);

            return Bitmap.createScaledBitmap(image, 200, 200, true);
        }
    }
}

