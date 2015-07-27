package com.example.lunchly;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Eli on 7/27/2015.
 */
public class PreviewPostActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview_post);

        TextView text = (TextView) findViewById(R.id.info);
        text.setText("I'm going to "+Post.pickUp+".  I'll be back at "+"Kayak"+" around "+Post.hour+":"+Post.minute+".  Want me to grab you something?");
    }

    public void post(View v){
        Intent myIntent = new Intent(this, OrdersActivity.class);
//        myIntent.putExtra("key", value); //Optional parameters
        this.startActivity(myIntent);
    }
}
