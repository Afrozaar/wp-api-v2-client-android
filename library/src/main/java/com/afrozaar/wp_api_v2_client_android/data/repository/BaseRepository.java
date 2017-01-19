package com.afrozaar.wp_api_v2_client_android.data.repository;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.afrozaar.wp_api_v2_client_android.util.LogUtils;

import java.util.Map;

/**
 * @author Jan-Louis Crafford
 *         Created on 2016/06/06.
 */
public abstract class BaseRepository implements BaseColumns {

    public static final int IDX_ID = 0;

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

    public static String getProjectionString(String[] projection, @Nullable String table) {
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
