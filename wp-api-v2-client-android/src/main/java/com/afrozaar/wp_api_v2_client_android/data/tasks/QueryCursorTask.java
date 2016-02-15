package com.afrozaar.wp_api_v2_client_android.data.tasks;

import android.content.Context;
import android.database.Cursor;

/**
 * @author Jan-Louis Crafford
 *         Created on 2016/02/11.
 */
public class QueryCursorTask extends DatabaseTask<Void, Void, Cursor> {

    private boolean distinct;
    private String table;
    private String[] projection;
    private String selection;
    private String[] selectionArgs;
    private String groupBy;
    private String having;
    private String orderBy;
    private String limit;

    public QueryCursorTask(Context context, String table, String[] projection, String selection, String[] selectionArgs, DatabaseTaskCallback<Cursor> callback) {
        this(context, false, table, projection, selection, selectionArgs, null, null, null, null, callback);
    }

    public QueryCursorTask(Context context, boolean distinct, String table, String[] projection, String selection,
                           String[] selectionArgs, String groupBy, String having, String orderBy, String limit, DatabaseTaskCallback<Cursor> callback) {
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
    protected Cursor exec() throws Exception {
        return getReadableDatabase().query(distinct, table, projection, selection, selectionArgs,
                groupBy, having, orderBy, limit);
    }
}
