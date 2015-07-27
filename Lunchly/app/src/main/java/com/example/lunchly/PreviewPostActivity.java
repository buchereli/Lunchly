package com.example.lunchly;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;

/**
 * Created by Eli on 7/27/2015.
 */
public class PreviewPostActivity extends Activity {

    ImageView profileImage;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview_post);

        profileImage = (ImageView) findViewById(R.id.profileImage);
        userID = "889570764470724";

        new GetFacebookProfilePicture().execute();

        Calendar calendar = Calendar.getInstance();
        TextView time = (TextView) findViewById(R.id.time);
        String postTime = calendar.getTime().toString();
        postTime = postTime.substring(4, postTime.length()-12);
        time.setText(postTime);

        TextView info = (TextView) findViewById(R.id.info);
        info.setText("I'm going to " + Post.pickUp + ".  I'll be back at " + "Kayak" + " around " + Post.hour + ":" + Post.minute + ".  Want me to grab you something?");

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


    public void post(View v){
        Intent myIntent = new Intent(this, OrdersActivity.class);
//        myIntent.putExtra("key", value); //Optional parameters
        this.startActivity(myIntent);
    }
}
