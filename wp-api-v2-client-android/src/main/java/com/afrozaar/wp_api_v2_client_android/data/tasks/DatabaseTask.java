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

    private DatabaseTaskCallback callback;

    protected DatabaseTask(Context context, DatabaseTaskCallback callback) {
        database = new WordPressDatabase(context);

        this.callback = callback;
    }

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
