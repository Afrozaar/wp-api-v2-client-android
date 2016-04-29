package com.afrozaar.wp_api_v2_client_android.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.afrozaar.wp_api_v2_client_android.data.legacy.DatabaseMigrator;

/**
 * @author Jan-Louis Crafford
 *         Created on 2016/02/11.
 */
public class WordPressDatabase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "wordpress.db";

    private static final int VERSION_INITIAL = 100;
    private static final int VERSION_COL_UPDATE_TIME = 101;
    private static final int VERSION_MEDIA_TABLE = 102;
    private static final int VERSION_USERS_TABLE_UPDATE = 103;

    private static final int VERSION_CURRENT = VERSION_USERS_TABLE_UPDATE;

    private static WordPressDatabase sInstance = null;

    public static WordPressDatabase getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new WordPressDatabase(context);
        }

        return sInstance;
    }

    private Context context;

    public WordPressDatabase(Context context) {
        super(context, DATABASE_NAME, null, VERSION_CURRENT);

        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(WordPressContract.Blogs.SCHEMA);
        db.execSQL(WordPressContract.Users.SCHEMA);
        db.execSQL(WordPressContract.Posts.SCHEMA);
        db.execSQL(WordPressContract.Taxonomies.SCHEMA);
        db.execSQL(WordPressContract.Metas.SCHEMA);
        db.execSQL(WordPressContract.Medias.SCHEMA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch (oldVersion) {
            case VERSION_INITIAL:
                upgradeV100To101(db);
            case VERSION_COL_UPDATE_TIME:
                upgradeV101To102(db);
            case VERSION_MEDIA_TABLE:
                upgradeV102To103(db);
        }
    }

    /**
     * Adds the update time column to POSTS table
     */
    private void upgradeV100To101(SQLiteDatabase db) {
        db.execSQL("ALTER TABLE " + WordPressContract.Posts.TABLE_NAME + " ADD COLUMN "
                + WordPressContract.Posts.UPDATED_TIME + " INTEGER");
    }

    private void upgradeV101To102(SQLiteDatabase db) {
        db.execSQL(WordPressContract.Medias.SCHEMA);

        DatabaseMigrator migrator = new DatabaseMigrator(context);

        Cursor cursor = migrator.getMediasCursor();
        if (cursor != null) {
            while (cursor.moveToNext()) {
                long postRowId = cursor.getLong(1);
                String type = cursor.getString(2);
                String uri = cursor.getString(3);
                String caption = cursor.getString(4);
                long extId = cursor.getLong(5);
                String extUrl = cursor.getString(6);

                ContentValues values = new ContentValues();
                values.put(WordPressContract.Medias.BLOG_ID, -1);
                values.put(WordPressContract.Medias.WP_POST_ID, -1);
                values.put(WordPressContract.Medias.POST_ROW_ID, postRowId);
                values.put(WordPressContract.Medias.TYPE, type);
                values.put(WordPressContract.Medias.URI, uri);
                values.put(WordPressContract.Medias.CAPTION, caption != null ? caption : "");
                values.put(WordPressContract.Medias.WP_MEDIA_ID, extId);
                values.put(WordPressContract.Medias.EXTERNAL_URL, extUrl != null ? extUrl : "");

                db.insert(WordPressContract.Medias.TABLE_NAME, null, values);
            }
            cursor.close();
        }

        migrator.deleteDatabase(context);
    }

    /**
     * Changes the 'authors' table to 'user' and adds more columns
     * @param db
     */
    private void upgradeV102To103(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS authors");

        db.execSQL(WordPressContract.Users.SCHEMA);
    }

    public void deleteDatabase(Context context) {
        context.deleteDatabase(DATABASE_NAME);
    }
}
