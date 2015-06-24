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
        calcTotalCost();
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

        // Format total because of messy double rounding
        String formattedTotal = ""+total;
        if(!formattedTotal.contains("."))
            formattedTotal = formattedTotal+".";
        int numberOfDecimals = formattedTotal.length()-formattedTotal.indexOf(".");
        if(numberOfDecimals < 2){
            if(numberOfDecimals == 1)
                formattedTotal = formattedTotal+"0";
            else
                formattedTotal = formattedTotal+"00";
        }
        else
            formattedTotal = formattedTotal.substring(0, formattedTotal.indexOf(".")+3);

        totalCost = "$"+formattedTotal;
    }

    public static String totalCost(){
        return totalCost;
    }

    public static Integer numberOfOrders() {
        return orders.length();
    }

}
