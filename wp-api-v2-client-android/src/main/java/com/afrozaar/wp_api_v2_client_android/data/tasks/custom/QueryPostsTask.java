package com.afrozaar.wp_api_v2_client_android.data.tasks.custom;

import android.content.Context;
import android.database.Cursor;

import com.afrozaar.wp_api_v2_client_android.data.WordPressContract;
import com.afrozaar.wp_api_v2_client_android.data.tasks.callback.DatabaseTaskCallback;
import com.afrozaar.wp_api_v2_client_android.data.tasks.WpQueryCursorTask;
import com.afrozaar.wp_api_v2_client_android.model.Post;

/**
 * @author Jan-Louis Crafford
 *         Created on 2016/03/04.
 */
public class QueryPostsTask extends WpQueryCursorTask {

    public static QueryPostsTask newTask(Context context, String status, DatabaseTaskCallback<Cursor> callback) {
        String selection = null;
        String[] selectionArgs = null;

        if (status != null) {
            selection = WordPressContract.Posts.STATUS + "=?";
            selectionArgs = new String[1];
            selectionArgs[0] = status;
        } else {
            selection = WordPressContract.Posts.STATUS + "=? OR "
                    + WordPressContract.Posts.STATUS + " =?";
            selectionArgs = new String[2];
            selectionArgs[0] = Post.POST_STATUS_DRAFT;
            selectionArgs[1] = Post.POST_STATUS_PUBLISHED_PRIVATE;
        }

        return new QueryPostsTask(context, WordPressContract.Posts.TABLE_NAME, null, selection, selectionArgs, callback);
    }

    private QueryPostsTask(Context context, String table, String[] projection, String selection, String[] selectionArgs, DatabaseTaskCallback<Cursor> callback) {
        super(context, table, projection, selection, selectionArgs, callback);
    }
}
