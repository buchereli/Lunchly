package com.example.lunchly;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Eli on 6/19/2015.
 */
public class TimeSelectActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeselect);
    }

    public void next(View v){
        Intent myIntent = new Intent(TimeSelectActivity.this, FinalizeActivity.class);
//        myIntent.putExtra("key", value); //Optional parameters
        TimeSelectActivity.this.startActivity(myIntent);
    }
}
