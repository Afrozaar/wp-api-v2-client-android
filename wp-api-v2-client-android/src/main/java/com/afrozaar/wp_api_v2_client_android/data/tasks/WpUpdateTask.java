package com.afrozaar.wp_api_v2_client_android.data.tasks;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.afrozaar.wp_api_v2_client_android.data.tasks.callback.WpTaskCallback;

/**
 * @author Jan-Louis Crafford
 *         Created on 2016/02/11.
 */
public class WpUpdateTask extends WpAsyncTask<Void, Void, Integer> {

    private String table;
    private ContentValues values;
    private String where;
    private String[] whereArgs;

    public WpUpdateTask(Context context, String table, ContentValues values, String where, String[] whereArgs, WpTaskCallback<Integer> callback) {
        super(context, callback);

        this.table = table;
        this.values = values;
        this.where = where;
        this.whereArgs = whereArgs;
    }

    @Override
    protected Integer exec() throws Exception {
        SQLiteDatabase db = getWritableDatabase();

        return db.update(table, values, where, whereArgs);
    }
}
