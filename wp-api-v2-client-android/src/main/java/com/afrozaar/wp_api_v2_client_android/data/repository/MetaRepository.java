package com.afrozaar.wp_api_v2_client_android.data.repository;

import android.content.ContentValues;
import android.database.Cursor;
import android.provider.BaseColumns;

import com.afrozaar.wp_api_v2_client_android.data.WordPressContract;
import com.afrozaar.wp_api_v2_client_android.model.Meta;

/**
 * @author Jan-Louis Crafford
 *         Created on 2016/08/12.
 */
public class MetaRepository extends BaseWpRepository implements WordPressContract.MetaColumns {

    public static final String TABLE_NAME = "metas";

    public static final String SCHEMA = "CREATE TABLE " + TABLE_NAME + " ("
            + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + BLOG_ID + " INTEGER " + WordPressContract.References.BLOG_ID + ","
            + WP_POST_ID + " INTEGER " + WordPressContract.References.POST_ID + ","
            + POST_ROW_ID + " INTEGER " + WordPressContract.References.POST_ROW_ID + ","
            + WP_META_ID + " INTEGER DEFAULT -1,"
            + KEY + " TEXT NOT NULL,"
            + VALUE + " TEXT NOT NULL)";

    public static final int IDX_BLOG_ID = 1;
    public static final int IDX_WP_POST_ID = 2;
    public static final int IDX_POST_ROW_ID = 3;
    public static final int IDX_WP_META_ID = 4;
    public static final int IDX_KEY = 5;
    public static final int IDX_VALUE = 6;

    public static ContentValues getContainsMap(Meta meta, long blogId, long postId, long postRowId) {
        ContentValues values = new ContentValues();

        values.put(BLOG_ID, blogId);

        if (postId != -1) {
            values.put(WP_POST_ID, postId);
        } else if (postRowId != -1) {
            values.put(POST_ROW_ID, postRowId);
        } else {
            throw new IllegalArgumentException("Both WP and row IDs are -1:\n" + meta.toString());
        }

        values.put(KEY, meta.getKey());

        return values;
    }

    public static ContentValues mapToContentValues(Meta meta, long blogId, long postId, long postRowId) {
        ContentValues values = new ContentValues();

        values.put(BLOG_ID, blogId);
        values.put(POST_ROW_ID, postRowId);

        addValue(values, WP_POST_ID, postId);
        addValue(values, WP_META_ID, meta.getId());
        addValue(values, KEY, meta.getKey());
        addValue(values, VALUE, meta.getValue());

        return values;
    }

    public static Meta mapFromCursor(Cursor cursor) {
        Meta meta = new Meta();

        meta.rowId = getRowId(cursor);

        meta.withId(cursor.getLong(IDX_WP_META_ID))
                .withKey(cursor.getString(IDX_KEY))
                .withValue(cursor.getString(IDX_VALUE));

        return meta;
    }
}
