package com.afrozaar.wp_api_v2_client_android.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import com.afrozaar.wp_api_v2_client_android.data.repository.BlogRepository;
import com.afrozaar.wp_api_v2_client_android.data.repository.PostRepository;
import com.afrozaar.wp_api_v2_client_android.model.Blog;
import com.afrozaar.wp_api_v2_client_android.model.Post;
import com.afrozaar.wp_api_v2_client_android.util.LogUtils;

import java.util.Map;

/**
 * @author Jan-Louis Crafford
 *         Created on 2016/08/08.
 */
public class DatabaseUtil {

    public static void insertBlog(SQLiteDatabase database, Blog blog) {
        ContentValues values = BlogRepository.mapToContentValues(blog);

        if (containsData(database, BlogRepository.TABLE_NAME, BlogRepository.getContainsMap(blog))) {
            String where = BlogRepository.TITLE + "=?";
            String[] whereArgs = {blog.title};

            database.update(BlogRepository.TABLE_NAME, values, where, whereArgs);
        } else {
            database.insert(BlogRepository.TABLE_NAME, null, values);
        }
    }


    public static void insertPost(SQLiteDatabase database, long blogId, Post post) {
        ContentValues values = PostRepository.mapToContentValues(post);

        if (containsData(database, PostRepository.TABLE_NAME, PostRepository.getContainsMap(blogId, post))) {
            String where = BlogRepository.BLOG_ID + "=? AND "
                    + PostRepository.WP_AUTHOR_ID + "=?";
            String[] whereArgs = {blogId + "", post.getAuthor() + ""};

            database.update(PostRepository.TABLE_NAME, values, where, whereArgs);
        } else {
            database.insert(PostRepository.TABLE_NAME, null, values);
        }
    }


    /**
     * Checks if table contains a record
     *
     * @param db            Database to use
     * @param tableName     Table to read from
     * @param contentValues ContentValues to use for reading entry
     * @return true if entry exists in table
     */
    public static boolean containsData(SQLiteDatabase db, String tableName, ContentValues contentValues) {
        String[] columns = new String[contentValues.size()];
        String[] selectionArgs = new String[contentValues.size()];
        int cnt = 0;
        for (Map.Entry<String, Object> entry : contentValues.valueSet()) {
            columns[cnt] = entry.getKey();
            selectionArgs[cnt] = String.valueOf(entry.getValue());
            cnt++;
        }
        return containsData(db, tableName, columns, selectionArgs);
    }

    /**
     * Checks if table contains a record
     *
     * @param db            Database to use
     * @param tableName     Table to read from
     * @param columns       Column selections
     * @param selectionArgs Column selection arguments
     * @return true if entry exists
     */
    public static boolean containsData(SQLiteDatabase db, String tableName, String[] columns, String[] selectionArgs) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < columns.length; i++) {
            if (i > 0) {
                sb.append(" and ");
            }
            sb.append(columns[i]).append("=?");
        }
        Cursor c = db.query(tableName, columns, sb.toString(), selectionArgs, null, null, null);
        boolean val = c != null && c.moveToFirst();
        if (c != null) {
            c.close();
        }
        return val;
    }

    /**
     * Helper methods for creating a SQL where selection string
     *
     * @param columns Columns to add to WHERE string
     * @return Selection string
     */
    public static String getSelection(String... columns) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < columns.length; i++) {
            builder.append(columns[i]);
            builder.append("=?");
            if (i != columns.length - 1) {
                builder.append(" AND ");
            }
        }
        return builder.toString();
    }

    public static String getNotSelection(String... columns) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < columns.length; i++) {
            builder.append(columns[i]);
            builder.append(" IS NOT ?");
            if (i != columns.length - 1) {
                builder.append(" AND ");
            }
        }
        return builder.toString();
    }

    public static String getProjectionString(String[] projection, String table) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < projection.length; i++) {
            if (!TextUtils.isEmpty(table)) {
                builder.append(table)
                        .append(".");
            }
            builder.append(projection[i]);
            if (i != (projection.length - 1)) {
                builder.append(", ");
            }
        }

        return builder.toString();
    }

    /**
     * Utility method for printing out the contents of a Cursor object
     *
     * @param cursor Cursor to print to output
     */
    public static void printCursor(Cursor cursor) {
        if (cursor == null) {
            return;
        }

        cursor.moveToFirst();
        if (cursor.getCount() == 0) {
            return;
        }

        String[] columns = cursor.getColumnNames();
        do {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < columns.length; i++) {
                builder.append(columns[i])
                        .append("=")
                        .append(cursor.getString(cursor.getColumnIndex(columns[i])))
                        .append(" ; ");
            }
            LogUtils.d("CURSOR", builder.toString());
        } while (cursor.moveToNext());

        cursor.close();
    }

    public static void printCursor(Cursor cursor, int numOfRecords) {
        if (cursor == null || numOfRecords == 0) {
            return;
        }

        cursor.moveToFirst();
        if (cursor.getCount() == 0) {
            return;
        }
        if (cursor.getCount() < numOfRecords) {
            numOfRecords = cursor.getCount();
        }

        String[] columns = cursor.getColumnNames();
        for (int i = 0; i < numOfRecords; i++) {
            cursor.moveToPosition(i);

            StringBuilder builder = new StringBuilder();
            for (String column : columns) {
                builder.append(column)
                        .append("=")
                        .append(cursor.getString(cursor.getColumnIndex(column)))
                        .append(" ; ");
            }
            LogUtils.d("CURSOR", builder.toString());
        }
    }
}
