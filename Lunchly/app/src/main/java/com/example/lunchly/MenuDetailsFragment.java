package com.example.lunchly;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Eli on 7/30/2015.
 */
public class MenuDetailsFragment extends Fragment {

    LayoutInflater inflater;
    JSONObject menu;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.expandable_list, null);
        this.inflater = inflater;

        readFile();

        final ExpandableListView list = (ExpandableListView) v.findViewById(R.id.list);
        final Adapter adapter = new Adapter();
        list.setAdapter(adapter);

        list.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int i, int i1, long id) {

                if (i != menu.optJSONArray("main").length() - 1)
                    try {
                        menu.optJSONArray("main").optJSONObject(i).optJSONArray("items").optJSONObject(i1).put("number",
                                menu.optJSONArray("main").optJSONObject(i).optJSONArray("items").optJSONObject(i1).optInt("number") + 1);

                        JSONObject addToOrder = new JSONObject(menu.optJSONArray("main").optJSONObject(i).optJSONArray("items").optJSONObject(i1).toString());
                        addToOrder.put("number", 0);

                        menu.optJSONArray("main").optJSONObject(menu.optJSONArray("main").length() - 1).getJSONArray("items").put(addToOrder);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                adapter.notifyDataSetChanged();
                return true;
            }
        });

        list.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                if (groupPosition == menu.optJSONArray("main").length() - 1)
                    return true;
                return false;
            }
        });

        list.expandGroup(menu.optJSONArray("main").length()-1);

        return v;
    }


    public class Adapter extends BaseExpandableListAdapter {

        @Override
        public int getGroupCount() {
            return menu.optJSONArray("main").length();
        }

        @Override
        public int getChildrenCount(int i) {
            return menu.optJSONArray("main").optJSONObject(i).optJSONArray("items").length();
        }

        @Override
        public Object getGroup(int i) {
            return menu.optJSONArray("main").optJSONObject(i).optString("header");
        }

        @Override
        public Object getChild(int i, int i1) {
            return menu.optJSONArray("main").optJSONObject(i).optJSONArray("items").optJSONObject(i1).optString("text");
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
            View v = inflater.inflate(R.layout.menu_header_adapter, null);

            TextView header = (TextView) v.findViewById(R.id.header);
            TextView subtext = (TextView) v.findViewById(R.id.subtext);

            header.setText(menu.optJSONArray("main").optJSONObject(i).optString("header"));
            subtext.setText(menu.optJSONArray("main").optJSONObject(i).optString("subheader"));

            return v;
        }

        @Override
        public View getChildView(final int i, final int i1, boolean b, View view, ViewGroup viewGroup) {
            View v = inflater.inflate(R.layout.menu_item_adapter,null);

            final TextView item = (TextView) v.findViewById(R.id.item);
            final TextView cost = (TextView) v.findViewById(R.id.cost);
            final ImageView image = (ImageView) v.findViewById(R.id.image);
            final TextView number = (TextView) v.findViewById(R.id.number);

            item.setText(menu.optJSONArray("main").optJSONObject(i).optJSONArray("items").optJSONObject(i1).optString("text"));
            cost.setText("$" + menu.optJSONArray("main").optJSONObject(i).optJSONArray("items").optJSONObject(i1).optString("cost"));
            image.setImageBitmap(getImage(menu.optJSONArray("main").optJSONObject(i).optJSONArray("items").optJSONObject(i1).optString("image")));

            final TextView x = (TextView) v.findViewById(R.id.x);
            x.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (i != menuLength())
                        try {
                            menu.optJSONArray("main").optJSONObject(i).optJSONArray("items").optJSONObject(i1).put("number", 0);

                            for (int index = 0; index < menu.optJSONArray("main").optJSONObject(menuLength()).optJSONArray("items").length(); index++) {
                                System.err.println(index);
                                System.err.println(menu.optJSONArray("main").optJSONObject(menuLength()).optJSONArray("items").length());

                                if (menu.optJSONArray("main").optJSONObject(menuLength()).optJSONArray("items").optJSONObject(index).optString("text")
                                        .equals(menu.optJSONArray("main").optJSONObject(i).optJSONArray("items").optJSONObject(i1).optString("text"))) {

                                    System.err.println("inside");

                                    menu.optJSONArray("main").optJSONObject(menuLength()).optJSONArray("items").remove(index);
                                    index--;
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    else
                        menu.optJSONArray("main").optJSONObject(i).optJSONArray("items").remove(i1);

                    notifyDataSetChanged();
                }
            });

            number.setText("");
            if(menu.optJSONArray("main").optJSONObject(i).optJSONArray("items").optJSONObject(i1).optInt("number") != 0) {
                number.setText(" x " + menu.optJSONArray("main").optJSONObject(i).optJSONArray("items").optJSONObject(i1).optString("number"));
                x.setText("X");
            }
            else if(i != menuLength())
                x.setText("");

            return v;
        }

        @Override
        public boolean isChildSelectable(int i, int i1) {
            return true;
        }

        public int menuLength(){
            return menu.optJSONArray("main").length()-1;
        }

        public Object remove(JSONArray array, int index) {

            JSONArray output = new JSONArray();
            int len = array.length();
            for (int i = 0; i < len; i++)   {
                if (i != index) {
                    try {
                        output.put(array.get(i));
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            return output;
        }

        public Bitmap getImage(String type){

//            Point start = new Point(0, 0);
//
//            if(type.equals("Donut") || type.equals("Muffin"))
//                start = new Point(297, 335);
//            else if(type.equals("Bagel") || type.equals("Croissant") || type.equals("Flatbread") || type.equals("Sandwich"))
//                start = new Point(0, 95);
//            else if(type.equals("Fries"))
//                start = new Point(99, 115);
//            else if(type.equals("Wrap"))
//                start = new Point(99, 230);
//            else if(type.equals("Coffee"))
//                start = new Point(0, 335);
//            else if(type.equals("Tea"))
//                start = new Point(99, 335);
//            else if(type.equals("Drink"))
//                start = new Point(198, 115);
//
//            InputStream is = getResources().openRawResource(R.raw.food_icons);
//            Bitmap sheet = BitmapFactory.decodeStream(is);
//            Bitmap image = Bitmap.createBitmap(sheet, start.x, start.y, 99, 105);

            InputStream is = getResources().openRawResource(R.raw.baconeggcheese);
            Bitmap image = BitmapFactory.decodeStream(is);

            return Bitmap.createScaledBitmap(image, 150, 150, true);
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

        try {
            menu = new JSONObject(byteArrayOutputStream.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return byteArrayOutputStream.toString().split("\\r?\\n");
    }
}

