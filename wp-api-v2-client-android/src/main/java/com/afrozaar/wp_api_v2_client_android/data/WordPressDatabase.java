package com.afrozaar.wp_api_v2_client_android.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author Jan-Louis Crafford
 *         Created on 2016/02/11.
 */
public class WordPressDatabase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "wordpress.db";

    private static final int VERSION_INITIAL = 100;
    private static final int VERSION_COL_UPDATE_TIME = 101;
    private static final int VERSION_MEDIA_TABLE = 102;

    private static final int VERSION_CURRENT = VERSION_MEDIA_TABLE;

    private static WordPressDatabase sInstance = null;

    public static WordPressDatabase getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new WordPressDatabase(context);
        }

        return sInstance;
    }

    public WordPressDatabase(Context context) {
        super(context, DATABASE_NAME, null, VERSION_CURRENT);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(WordPressContract.Blogs.SCHEMA);
        db.execSQL(WordPressContract.Authors.SCHEMA);
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
                break;
            case VERSION_COL_UPDATE_TIME:
                upgradeV101To102(db);
                break;
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
    }

    public void deleteDatabase(Context context) {
        context.deleteDatabase(DATABASE_NAME);
    }
}
