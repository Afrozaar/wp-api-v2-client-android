package com.afrozaar.wp_api_v2_client_android.data.tasks.callback;

import com.afrozaar.wp_api_v2_client_android.data.tasks.WpAsyncTask;

/**
 * Created by jlo on 2016/02/28.
 */
public interface WpTaskCallback<Result> {

    /**
     * Called when task has finished successfully
     *
     * @param result Result object
     */
    void onTaskSuccess(Result result);

    /**
     * Called when task was successful but the result is null, which should probably not have happened
     */
    void onTaskResultNull();


    /**
     * Called when task was cancelled
     */
    void onTaskCancelled();

    /**
     * Called on task failure
     *
     * @param task  The failed task, for retrying if required.
     * @param error Error message returned from task
     */
    void onTaskFailure(WpAsyncTask task, String error);
}

