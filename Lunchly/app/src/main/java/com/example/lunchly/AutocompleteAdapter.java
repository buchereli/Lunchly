package com.example.lunchly;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by Eli on 6/19/2015.
 */
public class AutocompleteAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] values;

    public AutocompleteAdapter(Context context, String[] values) {
        super(context, R.layout.autocomplete_adapter, values);
        this.context = context;
        this.values = values;
    }

    // Create ListView View
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View autocompleteView = inflater.inflate(R.layout.autocomplete_adapter, parent, false);
        View v = inflater.inflate(R.layout.activity_pickup, parent, false);
        final EditText autoComplete = (EditText) v.findViewById(R.id.autoComplete_pickup_location);

        System.out.println("HERE");
        System.out.println(autoComplete.getText().toString());
        ArrayList<String> suggestions = new ArrayList<>();
        for(int i = 0; i < values.length; i++)
            if(values[i].startsWith(autoComplete.getEditableText().toString()))
                suggestions.add(values[i]);


        TextView suggestion = (TextView) autocompleteView.findViewById(R.id.suggestion);
        suggestion.setText(suggestions.get(position));

        return autocompleteView;
    }
}
