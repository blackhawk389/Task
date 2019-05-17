package com.nextgeni.task.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;



public class Utils {

    public static boolean isOnline(Context context) {

        try {
            if (context != null) {
                ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo ni = cm.getActiveNetworkInfo();
                if (ni != null && ni.isConnected())
                    return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
