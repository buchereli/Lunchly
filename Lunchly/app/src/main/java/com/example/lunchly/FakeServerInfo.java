package com.example.lunchly;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Eli on 6/19/2015.
 */
public class FakeServerInfo {
    private static JSONArray orders = new JSONArray();

    public static void updateOrders(){
        for(int i = 0; i < 10; i++) {
            try {
                JSONObject order = new JSONObject();

                order.put("user id", "1234");
                order.put("location", "Starbucks");
                order.put("order", "Coffee");
                order.put("cost", "$2.49");

                orders.put(order);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public static JSONArray getOrders(){
        return orders;
    }
}
