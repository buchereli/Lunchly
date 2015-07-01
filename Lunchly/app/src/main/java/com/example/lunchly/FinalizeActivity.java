package com.example.lunchly;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;

/**
 * Created by Eli on 6/19/2015.
 */
public class FinalizeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finalize);

        NumberPicker np = (NumberPicker) findViewById(R.id.numberPicker);
        np.setMinValue(0);
        np.setMaxValue(20);
    }

    public void finalize(View v){
        Intent myIntent = new Intent(this, OrdersActivity.class);
//        myIntent.putExtra("key", value); //Optional parameters
        this.startActivity(myIntent);
    }
}
