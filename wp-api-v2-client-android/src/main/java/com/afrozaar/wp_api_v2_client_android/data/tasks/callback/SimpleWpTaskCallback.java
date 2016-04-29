package com.afrozaar.wp_api_v2_client_android.data.tasks.callback;

import com.afrozaar.wp_api_v2_client_android.data.tasks.WpAsyncTask;
import com.afrozaar.wp_api_v2_client_android.util.LogUtils;

/**
 * @author Jan-Louis Crafford
 *         Created on 2016/03/15.
 */
public abstract class SimpleWpTaskCallback<Result> implements WpTaskCallback<Result> {

    @Override
    public void onTaskResultNull() {

    }

    @Override
    public void onTaskCancelled() {

    }

    @Override
    public void onTaskFailure(WpAsyncTask task, String error) {
        LogUtils.w("Task failure for (" + task.getClass().getSimpleName() + ") : " + error);
    }
}
