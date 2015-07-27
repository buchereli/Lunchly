package com.example.lunchly;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Eli on 7/5/2015.
 */
public class ProfileActivity extends Activity {

    ImageView profileImage;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profileImage = (ImageView) findViewById(R.id.profileImage);
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

}
