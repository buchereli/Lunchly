package com.example.lunchly;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TimePicker;

/**
 * Created by Eli on 6/19/2015.
 */
public class TimeSelectActivity extends Activity {

    TimePicker timePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeselect);

        timePicker = (TimePicker) findViewById(R.id.timePicker);
    }

    public void next(View v){
        Post.hour = timePicker.getCurrentHour();
        Post.minute = timePicker.getCurrentMinute();

        Intent myIntent = new Intent(TimeSelectActivity.this, FinalizeActivity.class);
//        myIntent.putExtra("key", value); //Optional parameters
        this.startActivity(myIntent);
    }
}
