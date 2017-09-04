package com.example.otimus.ecommerceapp.data.local;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;

/**
 * Created by brain on 9/4/17.
 */

public class LocalPrefs {
    Gson gson;
    SharedPreferences sharedPreferences;
    private static final String KEY_CART_ITEMS = "_cart_items";


    public LocalPrefs(Context context, Gson gson) {
        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        this.gson = gson;
    }

}
