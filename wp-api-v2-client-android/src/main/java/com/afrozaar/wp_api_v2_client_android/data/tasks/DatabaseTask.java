package com.afrozaar.wp_api_v2_client_android.data.tasks;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import com.afrozaar.wp_api_v2_client_android.data.WordPressDatabase;

/**
 * @author Jan-Louis Crafford
 *         Created on 2016/02/11.
 */
public abstract class DatabaseTask<Params, Progress, Result> extends AsyncTask<Params, Progress, Result> {

    private WordPressDatabase database;

    private DatabaseTaskCallback<Result> callback;

    protected DatabaseTask(Context context, DatabaseTaskCallback<Result> callback) {
        database = new WordPressDatabase(context);

        this.callback = callback;
    }

    @Override
    protected Result doInBackground(Params... params) {
        try {
            return exec();
        } catch (Exception e) {
            cancel(true);
            if (callback != null) {
                callback.onTaskFailure(e.getMessage());
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(Result result) {
        super.onPostExecute(result);

        if (callback != null) {
            callback.onTaskSuccess(result);
        }
    }

    protected abstract Result exec() throws Exception;

    protected SQLiteDatabase getReadableDatabase() {
        return database.getReadableDatabase();
    }

    protected SQLiteDatabase getWritableDatabase() {
        return database.getWritableDatabase();
    }

    public interface DatabaseTaskCallback<Result> {
        void onTaskSuccess(Result result);

        void onTaskFailure(String error);
    }
}
