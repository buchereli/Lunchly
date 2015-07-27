package com.example.lunchly;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.appevents.AppEventsLogger;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Eli on 6/30/2015.
 */
public class FeedActivity extends Activity {

    ImageView profileImage;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        profileImage = (ImageView) findViewById(R.id.profile);
        userID = "889570764470724";

        new GetFacebookProfilePicture().execute();
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

    public void goingSomewhere(View v){
        Intent myIntent = new Intent(this, PickupActivity.class);
//        myIntent.putExtra("key", value); //Optional parameters
        this.startActivity(myIntent);
    }

    public void openProfile(View v){
        Intent myIntent = new Intent(this, ProfileActivity.class);
//        myIntent.putExtra("key", value); //Optional parameters
        this.startActivity(myIntent);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Logs 'install' and 'app activate' App Events.
        AppEventsLogger.activateApp(this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Logs 'app deactivate' App Event.
        AppEventsLogger.deactivateApp(this);
    }
}
