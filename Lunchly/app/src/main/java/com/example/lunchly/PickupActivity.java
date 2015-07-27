package com.example.lunchly;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Eli on 6/24/2015.
 */
public class PickupActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pickup);

        final Button button = (Button) findViewById(R.id.button);

        final AutoCompleteTextView autoComplete = (AutoCompleteTextView) findViewById(R.id.autoComplete_pickup_location);
        final String[] restaurants = getResources().getStringArray(R.array.restaurants);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.autocomplete_adapter, restaurants);
        autoComplete.setAdapter(adapter);

        autoComplete.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    autoComplete.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(
                            INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                    if(!Arrays.asList(restaurants).contains(autoComplete.getText().toString())) {
                        Toast.makeText(getApplicationContext(), "We currently only support restaurants whose menu is contained within our database.", Toast.LENGTH_LONG).show();
                        button.setEnabled(false);
                    }
                    else {
                        Post.pickUp = autoComplete.getText().toString();
                        button.setEnabled(true);
                    }
                }
                return false;
            }
        });
    }

    public void next(View v){
        Intent myIntent = new Intent(this, TimeSelectActivity.class);
//        myIntent.putExtra("key", value); //Optional parameters
        this.startActivity(myIntent);
    }

}
