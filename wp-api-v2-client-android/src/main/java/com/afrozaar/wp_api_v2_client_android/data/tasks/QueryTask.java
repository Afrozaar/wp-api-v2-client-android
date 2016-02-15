package com.afrozaar.wp_api_v2_client_android.data.tasks;

import android.content.Context;
import android.database.Cursor;

/**
 * @author Jan-Louis Crafford
 *         Created on 2016/02/11.
 */
public abstract class QueryTask extends DatabaseTask<Void, Void, Cursor> {

    private boolean distinct;
    private String table;
    private String[] projection;
    private String selection;
    private String[] selectionArgs;
    private String groupBy;
    private String having;
    private String orderBy;
    private String limit;

    public QueryTask(Context context, DatabaseTaskCallback callback, String table, String[] projection, String selection, String[] selectionArgs) {
        this(context, callback, false, table, projection, selection, selectionArgs, null, null, null, null);
    }

    public QueryTask(Context context, DatabaseTaskCallback callback, boolean distinct, String table, String[] projection, String selection,
                     String[] selectionArgs, String groupBy, String having, String orderBy, String limit) {
        super(context, callback);

        this.distinct = distinct;
        this.table = table;
        this.projection = projection;
        this.selection = selection;
        this.selectionArgs = selectionArgs;
        this.groupBy = groupBy;
        this.having = having;
        this.orderBy = orderBy;
        this.limit = limit;
    }

    @Override
    protected Cursor doInBackground(Void... params) {
        Cursor cursor = getReadableDatabase().query(distinct, table, projection, selection, selectionArgs,
                groupBy, having, orderBy, limit);

        onQueryDone(cursor);

        return cursor;
    }

    @Override
    protected void onPostExecute(Cursor cursor) {
        super.onPostExecute(cursor);

        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
    }

    protected abstract void onQueryDone(Cursor cursor);
}
