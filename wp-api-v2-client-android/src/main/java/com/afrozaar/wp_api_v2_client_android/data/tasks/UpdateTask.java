package com.afrozaar.wp_api_v2_client_android.data.tasks;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * @author Jan-Louis Crafford
 *         Created on 2016/02/11.
 */
public abstract class UpdateTask extends DatabaseTask<Void, Void, Integer> {

    private String table;
    private ContentValues values;
    private String where;
    private String[] whereArgs;

    public UpdateTask(Context context, DatabaseTaskCallback callback, String table, ContentValues values, String where, String[] whereArgs) {
        super(context, callback);

        this.table = table;
        this.values = values;
        this.where = where;
        this.whereArgs = whereArgs;
    }

    @Override
    protected Integer doInBackground(Void... params) {
        SQLiteDatabase db = getWritableDatabase();
        int result = db.update(table, values, where, whereArgs);

        onUpdateDone(result);

        return result;
    }

    protected abstract void onUpdateDone(int result);
}
