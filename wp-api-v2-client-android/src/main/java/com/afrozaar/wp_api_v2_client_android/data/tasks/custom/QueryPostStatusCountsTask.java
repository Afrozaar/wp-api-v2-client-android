package com.afrozaar.wp_api_v2_client_android.data.tasks.custom;

import android.content.Context;
import android.database.Cursor;

import com.afrozaar.wp_api_v2_client_android.data.WordPressContract;
import com.afrozaar.wp_api_v2_client_android.data.tasks.callback.WpTaskCallback;
import com.afrozaar.wp_api_v2_client_android.data.tasks.WpAsyncTask;
import com.afrozaar.wp_api_v2_client_android.util.WordpressPreferenceHelper;

import java.util.HashMap;

/**
 * @author Jan-Louis Crafford
 *         Created on 2016/03/04.
 */
public class QueryPostStatusCountsTask extends WpAsyncTask<Void, Void, HashMap<String, Integer>> {

    public QueryPostStatusCountsTask(Context context, WpTaskCallback<HashMap<String, Integer>> callback) {
        super(context, callback);
    }

    @Override
    protected HashMap<String, Integer> exec() throws Exception {
        HashMap<String, Integer> map = new HashMap<>();

        String query = "SELECT " + WordPressContract.Posts.STATUS + ", "
                + " COUNT(" + WordPressContract.Posts.STATUS + ") AS status_counts "
                + " FROM " + WordPressContract.Posts.TABLE_NAME
                + " WHERE " + WordPressContract.Posts.WP_AUTHOR_ID + "=?"
                + " GROUP BY " + WordPressContract.Posts.STATUS;

        long authorId = WordpressPreferenceHelper.with(context).getWordPressUserId();
        String[] selectionArgs = {authorId + ""};

        Cursor cursor = getReadableDatabase().rawQuery(query, selectionArgs);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String status = cursor.getString(0);
                int count = cursor.getInt(1);

                map.put(status, count);
            }
            cursor.close();
        }

        return map;
    }
}
