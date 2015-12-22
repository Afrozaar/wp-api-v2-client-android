package com.afrozaar.wp_api_v2_client_android.util;

import android.content.Context;

import com.afrozaar.wp_api_v2_client_android.Client;
import com.afrozaar.wp_api_v2_client_android.Wordpress;

import java.util.Properties;

/**
 * Created by jay on 12/10/15.
 */
public class ClientFactory {

   /* public static Wordpress fromProperties(Context context, Properties properties) {
        return new Client(context,properties.getProperty("baseUrl"), properties.getProperty("username"), properties.getProperty("password"), Boolean.getBoolean("debug"));
    }*/

    public static Wordpress fromConfig(Context context, ClientConfig config) {
        final ClientConfig.Wordpress wordpress = config.getWordpress();

        return new Client(wordpress.mBaseUrl, wordpress.mUsername, wordpress.mPassword, config.debug);
    }
}
