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
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Eli on 7/30/2015.
 */
public class MenuFragment extends Fragment {

    String line;
    ArrayList<String> headers;
    ArrayList<ArrayList<String>> children;
    LayoutInflater inflater;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.expandable_list, null);
        this.inflater = inflater;

        readFile();

        ExpandableListView list = (ExpandableListView) v.findViewById(R.id.list);
        list.setAdapter(new Adapter());

        return v;
    }

    public class Adapter extends BaseExpandableListAdapter {

//        private String[] groups = { "People Names", "Dog Names", "Cat Names", "Fish Names" };
//
//        private String[][] children = {
//                {},
//                { "Ace", "Bandit", "Cha-Cha", "Deuce" },
//                { "Fluffy", "Snuggles" },
//                { "Goldy", "Bubbles" }
//        };

        @Override
        public int getGroupCount() {
            return headers.size();
        }

        @Override
        public int getChildrenCount(int i) {
            return children.get(i).size();
        }

        @Override
        public Object getGroup(int i) {
            return headers.get(i);
        }

        @Override
        public Object getChild(int i, int i1) {
            return children.get(i).get(i1);
        }

        @Override
        public long getGroupId(int i) {
            return i;
        }

        @Override
        public long getChildId(int i, int i1) {
            return i1;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
            line = headers.get(i);

            View v = inflater.inflate(R.layout.menu_header_adapter, null);

            TextView header = (TextView) v.findViewById(R.id.header);
            TextView subtext = (TextView) v.findViewById(R.id.subtext);

            header.setText(getNext());
            subtext.setText(getNext());

            return v;
        }

        @Override
        public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
            line = children.get(i).get(i1);

            View v = inflater.inflate(R.layout.menu_item_adapter,null);

            TextView item = (TextView) v.findViewById(R.id.item);
            TextView cost = (TextView) v.findViewById(R.id.cost);
            ImageView image = (ImageView) v.findViewById(R.id.image);

            item.setText(getNext());
            cost.setText(getNext());
            image.setImageBitmap(getImage(getNext()));

            return v;
        }

        @Override
        public boolean isChildSelectable(int i, int i1) {
            return false;
        }

        public String getNext(){
            if(line.length() != 0) {
                int i = 1;

                while (i < line.length() && line.charAt(i) != '|')
                    i++;

                String item = line.substring(0, i);
                if (i < line.length())
                    line = line.substring(i + 1);

                return item;
            }
            return "";
        }

        public Bitmap getImage(String type){

            Point start = new Point(0, 0);

            if(type.equals("Donut") || type.equals("Muffin"))
                start = new Point(297, 335);
            if(type.equals("Bagel") || type.equals("Croissant") || type.equals("Flatbread") || type.equals("Sandwich"))
                start = new Point(0, 95);
            if(type.equals("Fries"))
                start = new Point(99, 115);
            if(type.equals("Wrap"))
                start = new Point(99, 230);
            if(type.equals("Coffee"))
                start = new Point(0, 335);
            if(type.equals("Tea"))
                start = new Point(99, 335);
            if(type.equals("Drink"))
                start = new Point(198, 115);

            InputStream is = getResources().openRawResource(R.raw.food_icons);
            Bitmap sheet = BitmapFactory.decodeStream(is);
            Bitmap image = Bitmap.createBitmap(sheet, start.x, start.y, 99, 105);

            return Bitmap.createScaledBitmap(image, 200, 200, true);
        }
    }

    private String[] readFile(){
        InputStream inputStream = getResources().openRawResource(R.raw.dunkin_donuts_menu);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        int i;
        try {
            i = inputStream.read();
            while (i != -1)
            {
                byteArrayOutputStream.write(i);
                i = inputStream.read();
            }
            inputStream.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        String[] lines = byteArrayOutputStream.toString().split("\\r?\\n");
        headers = new ArrayList<>();
        children = new ArrayList<>();

        for(int index = 0; index < lines.length; index++){
            if(lines[index].startsWith("*")) {
                headers.add(lines[index].substring(1));
                children.add(new ArrayList<String>());
            }
            else
                children.get(headers.size()-1).add(lines[index]);
        }

        return byteArrayOutputStream.toString().split("\\r?\\n");
    }
}

