package com.afrozaar.wp_api_v2_client_android.data.tasks;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * @author Jan-Louis Crafford
 *         Created on 2016/02/11.
 */
public class InsertTask extends DatabaseTask<Void, Void, Long> {

    private String table;
    private ContentValues values;

    public InsertTask(Context context, DatabaseTaskCallback<Long> callback, String table, ContentValues values) {
        super(context, callback);

        this.table = table;
        this.values = values;
    }

    @Override
    protected Long exec() throws Exception {
        SQLiteDatabase db = getWritableDatabase();

        return db.insert(table, null, values);
    }
}
