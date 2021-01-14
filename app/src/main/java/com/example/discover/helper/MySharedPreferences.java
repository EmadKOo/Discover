package com.example.discover.helper;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class MySharedPreferences {
    Context context;

    public MySharedPreferences(Context context) {
        this.context = context;
    }

    public void addToSharedPreferences(String countryCode, String countryName){
        SharedPreferences.Editor editor = context.getSharedPreferences("location", MODE_PRIVATE).edit();
        editor.putString("countryCode", countryCode);
        editor.putString("countryName", countryName);
        editor.apply();
    }

    public String getCountryFromSharedPref(){
        SharedPreferences prefs = context.getSharedPreferences("location", MODE_PRIVATE);
        return prefs.getString("countryCode", "us");
    }

    public String getCountryNameFromSharedPref(){
        SharedPreferences prefs = context.getSharedPreferences("location", MODE_PRIVATE);
        return prefs.getString("countryName", "United States");
    }
}
