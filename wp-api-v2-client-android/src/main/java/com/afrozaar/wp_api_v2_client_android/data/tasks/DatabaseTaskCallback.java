package com.afrozaar.wp_api_v2_client_android.data.tasks;

/**
 * Created by jlo on 2016/02/28.
 */
public interface DatabaseTaskCallback<Result> {
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
     * Called on task failure
     *
     * @param error Error message returned from task
     */
    void onTaskFailure(String error);
}

