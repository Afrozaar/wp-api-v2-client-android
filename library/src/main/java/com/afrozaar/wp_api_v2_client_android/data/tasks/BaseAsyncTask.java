package com.afrozaar.wp_api_v2_client_android.data.tasks;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;

import com.afrozaar.wp_api_v2_client_android.data.WordPressDatabase;
import com.afrozaar.wp_api_v2_client_android.data.repository.PostRepository;
import com.afrozaar.wp_api_v2_client_android.data.tasks.callback.BaseTaskCallback;
import com.afrozaar.wp_api_v2_client_android.util.LogUtils;

import java.io.IOException;
import java.util.concurrent.Executor;

import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * @author Jan-Louis Crafford
 *         Created on 2016/02/10.
 */
public abstract class BaseAsyncTask<Result> extends AsyncTask<Void, Void, Result> {

    private static final String ERROR_NO_RESPONSE = "Response was null";

    private static final Executor ASYNC_TASK_POOL_EXECUTOR = AsyncTaskPoolExecutor.getInstance();

    private static final Executor ASYNC_TASK_SERIAL_EXECUTOR = AsyncTaskSerialExecutor.getInstance();

    protected Context context;
    protected BaseTaskCallback<Result> callback;

    private SQLiteOpenHelper sqLiteOpenHelper;
    private SQLiteDatabase openDatabase;

    protected boolean taskFailed = false;
    protected Exception taskException;

    public BaseAsyncTask(Context context, BaseTaskCallback<Result> callback) {
        this.context = context;
        this.callback = callback;

        sqLiteOpenHelper = WordPressDatabase.getInstance(context);
    }

    /**
     * Runs this task using a thread pool to allow parallel tasks.
     *
     * @param params The parameters of the task
     */
    public void run(Void... params) {
        executeOnExecutor(ASYNC_TASK_POOL_EXECUTOR, params);
    }

    /**
     * Runs this task on the Serial thread executor.
     *
     * @param params The parameters of the task
     */
    public void runOnSerialExecutor(Void... params) {
        executeOnExecutor(ASYNC_TASK_SERIAL_EXECUTOR, params);
    }

    @Override
    protected Result doInBackground(Void... params) {
        LogUtils.d("==== Starting task - " + getClass().getSimpleName());
        try {
            return exec();
        } catch (Exception e) {
            e.printStackTrace();
            taskFailed = true;
            taskException = e;
        }
        return null;
    }

    @Override
    protected void onCancelled(Result result) {
        super.onCancelled(result);

        if (callback != null) {
            callback.onTaskCancelled();
        }
    }

    @Override
    protected void onPostExecute(Result result) {
        super.onPostExecute(result);

        LogUtils.d("==== Ending task - " + getClass().getSimpleName() + ";result=" + (taskFailed ? "failed" : "success"));

        if (callback != null) {
            if (taskFailed) {
                callback.onTaskFailure(this, taskException.getMessage());
            } else {
                if (result != null) {
                    callback.onTaskSuccess(result);
                } else {
                    callback.onTaskResultNull();
                }
            }
        }
    }

    protected abstract Result exec() throws Exception;

    protected SQLiteDatabase getReadableDatabase() {
        if (sqLiteOpenHelper == null) {
            throw new IllegalStateException("Database has not been set");
        }

        if (openDatabase == null) {
            openDatabase = sqLiteOpenHelper.getReadableDatabase();
        }

        return openDatabase;
    }

    protected SQLiteDatabase getWritableDatabase() {
        if (sqLiteOpenHelper == null) {
            throw new IllegalStateException("Database has not been set");
        }

        if (openDatabase == null || openDatabase.isReadOnly()) {
            openDatabase = sqLiteOpenHelper.getWritableDatabase();
        }

        return openDatabase;
    }

    protected long getPostIdFromDatabase(long itemId) {
        String[] projection = {PostRepository.WP_POST_ID};
        String selection = PostRepository._ID + "=?";
        String[] selectionArgs = {itemId + ""};

        long postId = -1;
        Cursor cursor = getReadableDatabase().query(PostRepository.TABLE_NAME, projection, selection, selectionArgs, null, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                postId = cursor.getLong(0);
            }
            cursor.close();
        }

        return postId;
    }

    protected boolean setUpdatedFlag(long itemId) {
        ContentValues values = new ContentValues();
        values.put(PostRepository.UPDATED, 1);
        values.put(PostRepository.UPDATED_TIME, System.currentTimeMillis());

        String where = PostRepository._ID + "=?";
        String[] whereArgs = {itemId + ""};

        int result = getWritableDatabase().update(PostRepository.TABLE_NAME, values, where, whereArgs);
        return result == 1;
    }

    protected String parseErrorResponse(Response response) {
        if (response == null) {
            return ERROR_NO_RESPONSE;
        }

        if (response.errorBody() != null) {
            try {
                return response.errorBody().string();
            } catch (IOException e) {
                // ignore
            }
        }

        return response.message();
    }

    protected void onError(String msg, ResponseBody responseBody) {
        LogUtils.w(msg);
        if (responseBody != null) {
            try {
                LogUtils.w("Response : " + responseBody.string());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
