package com.afrozaar.wp_api_v2_client_android.data.tasks;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import com.afrozaar.wp_api_v2_client_android.data.WordPressDatabase;
import com.afrozaar.wp_api_v2_client_android.data.tasks.callback.WpTaskCallback;

import java.util.ArrayDeque;
import java.util.concurrent.Executor;

/**
 * @author Jan-Louis Crafford
 *         Created on 2016/02/11.
 */
public abstract class WpAsyncTask<Params, Progress, Result> extends AsyncTask<Params, Progress, Result> {

    private static final Executor ASYNC_TASK_POOL_EXECUTOR = new DatabaseTaskExecutorService();

    protected static final Executor DATABASE_TASK_SERIAL_EXECUTOR = new DatabaseTaskSerialExecutor();

    protected Context context;

    private WordPressDatabase database;
    private SQLiteDatabase openDatabase;

    private WpTaskCallback<Result> callback;

    protected WpAsyncTask(Context context, WpTaskCallback<Result> callback) {
        this.context = context;
        database = WordPressDatabase.getInstance(context);

        this.callback = callback;
    }

    public void run(Params... params) {
        executeOnExecutor(ASYNC_TASK_POOL_EXECUTOR, params);
    }

    @Override
    protected Result doInBackground(Params... params) {
        try {
            return exec();
        } catch (Exception e) {
            //cancel(true);
            e.printStackTrace();
            if (callback != null) {
                callback.onTaskFailure(this, e.getMessage());
            }
        }
        return null;
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();

        if (callback != null) {
            callback.onTaskCancelled();
        }
    }

    @Override
    protected void onPostExecute(Result result) {
        super.onPostExecute(result);

        if (callback != null) {
            if (result != null) {
                callback.onTaskSuccess(result);
            } else {
                callback.onTaskResultNull();
            }
        }
    }

    protected abstract Result exec() throws Exception;

    protected SQLiteDatabase getReadableDatabase() {
        if (openDatabase == null) {
            openDatabase = database.getReadableDatabase();
        }

        return openDatabase;
    }

    protected SQLiteDatabase getWritableDatabase() {
        if (openDatabase == null || openDatabase.isReadOnly()) {
            openDatabase = database.getWritableDatabase();
        }

        return openDatabase;
    }

    private static class DatabaseTaskSerialExecutor implements Executor {

        final ArrayDeque<Runnable> tasks = new ArrayDeque<>();
        Runnable active;

        @Override
        public synchronized void execute(final Runnable command) {
            tasks.offer(new Runnable() {
                @Override
                public void run() {
                    try {
                        command.run();
                    } finally {
                        scheduleNext();
                    }
                }
            });
            if (active == null) {
                scheduleNext();
            }
        }

        protected synchronized void scheduleNext() {
            if ((active = tasks.poll()) != null) {
                ASYNC_TASK_POOL_EXECUTOR.execute(active);
            }
        }
    }

}
