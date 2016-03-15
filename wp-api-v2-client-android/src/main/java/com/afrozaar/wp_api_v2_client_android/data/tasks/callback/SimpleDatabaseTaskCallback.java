package com.afrozaar.wp_api_v2_client_android.data.tasks.callback;

import com.afrozaar.wp_api_v2_client_android.data.tasks.WpDatabaseTask;
import com.afrozaar.wp_api_v2_client_android.util.LogUtils;

/**
 * @author Jan-Louis Crafford
 *         Created on 2016/03/15.
 */
public abstract class SimpleDatabaseTaskCallback<Result> implements DatabaseTaskCallback<Result> {

    @Override
    public void onTaskResultNull() {

    }

    @Override
    public void onTaskCancelled() {

    }

    @Override
    public void onTaskFailure(WpDatabaseTask task, String error) {
        LogUtils.w("Task failure for (" + task.getClass().getSimpleName() + ") : " + error);
    }
}
