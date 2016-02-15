package com.afrozaar.wp_api_v2_client_android.data.tasks;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * @author Jan-Louis Crafford
 *         Created on 2016/02/11.
 */
public class DeleteTask extends DatabaseTask<Void, Void, Integer> {

    private String table;
    private String where;
    private String[] whereArgs;

    public DeleteTask(Context context, DatabaseTaskCallback<Integer> callback, String table, String where, String[] whereArgs) {
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
