package com.example.lunchly;

import android.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

import org.json.JSONException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

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

        getListView().setDivider(null);
        getListView().setDividerHeight(30);
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
        ImageView profileImage;
        String userID;

        public Adapter(Context context, String[] values) {
            super(context, R.layout.feed_adapter, values);
            this.context = context;
            this.values = values;
        }

        // Create ListView View
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View feedView = inflater.inflate(R.layout.feed_adapter, parent, false);

            profileImage = (ImageView) feedView.findViewById(R.id.profileImage);
            userID = "889570764470724";
            new GetFacebookProfilePicture().execute();

            TextView text = (TextView) feedView.findViewById(R.id.info);
            ArrayList<Integer> spans = new ArrayList<>();
            String infoCreate = "I'm going to *"+"pickup"+"*.  I'll be back at *"+"dropoff"+"* around "+"time"+".  Anyone want me to grab them something?";

            for(int i = 0; i < infoCreate.length(); i++){
                if(infoCreate.charAt(i) == '*') {
                    spans.add(i);
                    infoCreate = infoCreate.substring(0, i) + infoCreate.substring(i+1, infoCreate.length());
                    i--;
                }
            }

            SpannableString info = new SpannableString(infoCreate);

            ClickableSpan pickup = new ClickableSpan() {
                @Override
                public void onClick(View textView) {
                    Toast.makeText(context, ""+position, Toast.LENGTH_LONG).show();
//                    startActivity(new Intent(this, LoginActivity.class));
                }
            };
            ClickableSpan dropoff = new ClickableSpan() {
                @Override
                public void onClick(View textView) {
                    Toast.makeText(context, ""+position, Toast.LENGTH_LONG).show();
//                    startActivity(new Intent(this, LoginActivity.class));
                }
            };

            info.setSpan(pickup, spans.get(0), spans.get(1), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            info.setSpan(dropoff, spans.get(2), spans.get(3), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            text.setText(info);
            text.setMovementMethod(LinkMovementMethod.getInstance());

            return feedView;
        }

        private class GetFacebookProfilePicture extends AsyncTask<Void, Void, Bitmap> {
            @Override
            protected Bitmap doInBackground(Void... Void) {

                URL imageURL = null;

                try {
                    imageURL = new URL("https://graph.facebook.com/" + userID + "/picture?type=large");
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

                Bitmap bitmap = null;

                try {
                    bitmap = BitmapFactory.decodeStream(imageURL.openConnection().getInputStream());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return bitmap;
            }

            protected void onPostExecute(Bitmap bitmap) {
                profileImage.setImageBitmap(bitmap);
            }
        }
    }
}
