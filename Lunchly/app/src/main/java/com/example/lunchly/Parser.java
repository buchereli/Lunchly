package com.example.lunchly;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by Eli on 6/19/2015.
 */
public class Parser {

    public static String[] parseOrdersToValues(JSONArray orders){
        String[] values = new String[orders.length()];

        for(int i = 0; i < orders.length(); i++){
            try {
                values[i] = orders.getJSONObject(0).get("location")+"%"+orders.getJSONObject(0).get("cost");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return values;
    }

}
