package com.example.otimus.ecommerceapp;

import android.app.Activity;
import android.support.design.widget.Snackbar;

/**
 * Created by brain on 9/4/17.
 */

public class Utils {

    public static void showSnackbar(Activity activity, String message) {
        Snackbar snackbar = Snackbar.make(
                (activity.findViewById(android.R.id.content)),
                message, Snackbar.LENGTH_SHORT);
        snackbar.show();
    }
}
