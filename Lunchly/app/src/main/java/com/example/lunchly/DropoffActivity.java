package com.example.lunchly;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class DropoffActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dropoff);

        GoogleMap googleMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();

    }

    public void setDropoffTime(View v){

    }
}
