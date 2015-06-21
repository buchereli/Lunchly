package com.example.lunchly;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by Eli on 6/20/2015.
 */
public class OrdersInfo {

    private static JSONArray orders;
    private static String totalCost;

    public static void update(JSONArray updatedOrders){
        orders = updatedOrders;
    }

    private static void calcTotalCost(){
        double total = 0;
        for(int i = 0; i < orders.length(); i++) {
            try {
                total += Double.parseDouble(orders.getJSONObject(i).getString("cost").substring(1));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        totalCost = "$"+total;
    }

    public static String totalCost(){
        return totalCost;
    }
}
