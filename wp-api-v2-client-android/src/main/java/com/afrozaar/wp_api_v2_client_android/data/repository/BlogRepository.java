package com.afrozaar.wp_api_v2_client_android.data.repository;

import android.content.ContentValues;
import android.database.Cursor;
import android.provider.BaseColumns;

import com.afrozaar.wp_api_v2_client_android.data.WordPressContract;
import com.afrozaar.wp_api_v2_client_android.model.Blog;

/**
 * @author Jan-Louis Crafford
 *         Created on 2016/08/08.
 */
public class BlogRepository extends BaseWpRepository implements WordPressContract.BlogColumns {

    public static final String TABLE_NAME = "blogs";

    public static final String SCHEMA = "CREATE TABLE " + TABLE_NAME + " ("
            + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + TITLE + " TEXT NOT NULL,"
            + URL + " TEXT NOT NULL,"
            + USER + " TEXT NOT NULL,"
            + PASS + " TEXT NOT NULL)";

    public static final int IDX_TITLE = 1;
    public static final int IDX_URL = 2;
    public static final int IDX_USER = 3;
    public static final int IDX_PASS = 4;

    public static ContentValues getContainsMap(Blog blog) {
        ContentValues values = new ContentValues();
        values.put(URL, blog.url);
        return values;
    }

    public static ContentValues mapToContentValues(Blog blog) {
        ContentValues values = new ContentValues();

        addValue(values, TITLE, blog.title);
        addValue(values, URL, blog.url);
        addValue(values, USER, blog.user);
        addValue(values, PASS, blog.user);

        return values;
    }

    public static Blog mapFromCursor(Cursor cursor) throws Exception {
        Blog blog = new Blog();

        blog.rowId = getRowId(cursor);
        blog.title = cursor.getString(IDX_TITLE);
        blog.url = cursor.getString(IDX_URL);
        blog.user = cursor.getString(IDX_USER);
        blog.pass = cursor.getString(IDX_PASS);

        return blog;
    }
}
