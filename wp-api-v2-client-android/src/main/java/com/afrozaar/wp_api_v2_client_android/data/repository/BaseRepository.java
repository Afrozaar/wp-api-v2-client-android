package com.afrozaar.wp_api_v2_client_android.data.repository;

import android.content.ContentValues;
import android.database.Cursor;
import android.provider.BaseColumns;

import com.afrozaar.wp_api_v2_client_android.data.WordPressContract;

/**
 * @author Jan-Louis Crafford
 *         Created on 2016/06/06.
 */
public abstract class BaseRepository<Model> implements BaseColumns, WordPressContract.BaseWPColumns {

    public abstract ContentValues mapToContentValues(Model model);

    public abstract Model mapFromCursor(Cursor cursor) throws Exception;

    protected static long getRowId(Cursor cursor) {
        if (cursor.getColumnIndex(_ID) != -1) {
            return cursor.getLong(IDX_ID);
        }
        return -1;
    }

    protected static void addValue(ContentValues contentValues, String key, Object value) {
        if (value == null) {
            return;
        } else if (value instanceof String) {
            addStringValue(contentValues, key, (String) value);
        } else if (value instanceof Byte) {
            addByteValue(contentValues, key, (Byte) value);
        } else if (value instanceof Integer) {
            addIntegerValue(contentValues, key, (Integer) value);
        } else if (value instanceof Float) {
            addFloatValue(contentValues, key, (Float) value);
        } else if (value instanceof byte[]) {
            addByteArrayValue(contentValues, key, (byte[]) value);
        } else if (value instanceof Double) {
            addDoubleValue(contentValues, key, (Double) value);
        } else if (value instanceof Long) {
            addLongValue(contentValues, key, (Long) value);
        } else if (value instanceof Boolean) {
            addBooleanValue(contentValues, key, (Boolean) value);
        }
    }

    private static void addStringValue(ContentValues contentValues, String key, String value) {
        contentValues.put(key, value);
    }

    private static void addByteValue(ContentValues contentValues, String key, byte value) {
        contentValues.put(key, value);
    }

    private static void addIntegerValue(ContentValues contentValues, String key, int value) {
        contentValues.put(key, value);
    }

    private static void addFloatValue(ContentValues contentValues, String key, float value) {
        contentValues.put(key, value);
    }

    private static void addByteArrayValue(ContentValues contentValues, String key, byte[] value) {
        contentValues.put(key, value);
    }

    private static void addDoubleValue(ContentValues contentValues, String key, double value) {
        contentValues.put(key, value);
    }

    private static void addLongValue(ContentValues contentValues, String key, long value) {
        contentValues.put(key, value);
    }

    private static void addBooleanValue(ContentValues contentValues, String key, boolean value) {
        contentValues.put(key, value);
    }
}
