package com.afrozaar.wp_api_v2_client_android.upload;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

/**
 * @author Jan-Louis Crafford
 *         Created on 2016/01/29.
 */
public class CancelPostUploadReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent service = new Intent();
        service.setComponent(new ComponentName(context, UploadPostIntentService.class));
        context.stopService(service);
    }
}
