package com.afrozaar.wp_api_v2_client_android.data.tasks.callback;


import com.afrozaar.wp_api_v2_client_android.data.tasks.BaseAsyncTask;
import com.afrozaar.wp_api_v2_client_android.util.LogUtils;

/**
 * @author Jan-Louis Crafford
 *         Created on 2016/06/22.
 */
public abstract class SimpleNoResultTaskCallback<Result> implements BaseTaskCallback<Result> {

    @Override
    public void onTaskSuccess(Result result) {

    }

    @Override
    public void onTaskCancelled() {

    }

    @Override
    public void onTaskFailure(BaseAsyncTask task, String error) {
        LogUtils.w("Error while running task (" + task.getClass().getSimpleName() + ") : " + error);
    }
}
