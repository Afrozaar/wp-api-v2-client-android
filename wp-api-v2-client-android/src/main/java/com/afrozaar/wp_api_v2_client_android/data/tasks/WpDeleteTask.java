package com.afrozaar.wp_api_v2_client_android.data.tasks;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.afrozaar.wp_api_v2_client_android.data.tasks.callback.WpTaskCallback;

/**
 * @author Jan-Louis Crafford
 *         Created on 2016/02/11.
 */
public class WpDeleteTask extends WpAsyncTask<Void, Void, Integer> {

    private String table;
    private String where;
    private String[] whereArgs;

    public WpDeleteTask(Context context, String table, String where, String[] whereArgs, WpTaskCallback<Integer> callback) {
        super(context, callback);

        this.table = table;
        this.where = where;
        this.whereArgs = whereArgs;
    }

    @Override
    protected Integer exec() throws Exception {
        SQLiteDatabase db = getWritableDatabase();

        return db.delete(table, where, whereArgs);
    }
}
