package com.afrozaar.wp_api_v2_client_android.data.tasks;

import android.content.Context;

/**
 * @author Jan-Louis Crafford
 *         Created on 2016/02/11.
 */
public abstract class DeleteTask extends DatabaseTask<Void, Void, Integer> {

    private String table;
    private String where;
    private String[] whereArgs;

    public DeleteTask(Context context, DatabaseTaskCallback callback, String table, String where, String[] whereArgs) {
        super(context, callback);

        this.table = table;
        this.where = where;
        this.whereArgs = whereArgs;
    }


}
