package com.afrozaar.wp_api_v2_client_android.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by jay on 12/15/15.
 */
public class NetworkUtil {

    public static boolean isNetworkConnected(Context context){
        boolean wifiConnected;
        boolean mobileConnected;

        ConnectivityManager connMgr =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeInfo = connMgr.getActiveNetworkInfo();
        if (activeInfo != null && activeInfo.isConnected()) {
            wifiConnected = activeInfo.getType() == ConnectivityManager.TYPE_WIFI;
            mobileConnected = activeInfo.getType() == ConnectivityManager.TYPE_MOBILE;
        } else {
            wifiConnected = false;
            mobileConnected = false;
        }
        return (wifiConnected || mobileConnected);
    }
}
