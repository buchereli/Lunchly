package com.example.lunchly;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Switch;

/**
 * Created by Eli on 6/19/2015.
 */
public class FinalizeActivity extends Activity {

    Switch privatePost;
    NumberPicker numberPicker;
    EditText premium;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finalize);

        privatePost = (Switch) findViewById(R.id.privateSwitch);

        numberPicker = (NumberPicker) findViewById(R.id.numberPicker);
        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(20);

        premium = (EditText) findViewById(R.id.premiumAmount);
    }

    public void finalize(View v){
        Post.privatePost = privatePost.isChecked();
        Post.maxOrders = numberPicker.getValue();
        try {
            Post.premium = Double.parseDouble(premium.toString());
        } catch (Exception e){
            Post.premium = 0.00;
        }

        Intent myIntent = new Intent(this, PreviewPostActivity.class);
//        myIntent.putExtra("key", value); //Optional parameters
        this.startActivity(myIntent);
    }
}
