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
public class BlogRepository extends BaseRepository<Blog> implements WordPressContract.BlogColumns{

    public static final String TABLE_NAME = "blogs";

    public static final String SCHEMA = "CREATE TABLE " + TABLE_NAME + " ("
            + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + TITLE + " TEXT NOT NULL,"
            + URL + " TEXT NOT NULL,"
            + USER + " TEXT NOT NULL,"
            + PASS + " TEXT NOT NULL)";

    public static BlogRepository get() {
        return new BlogRepository();
    }

    public ContentValues getContainsMap(Blog blog) {
        return null;
    }

    @Override
    public ContentValues mapToContentValues(Blog blog) {
        return null;
    }

    @Override
    public Blog mapFromCursor(Cursor cursor) throws Exception {
        return null;
    }
}
