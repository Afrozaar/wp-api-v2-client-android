package com.afrozaar.wp_api_v2_client_android.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.afrozaar.wp_api_v2_client_android.data.legacy.DatabaseMigrator;
import com.afrozaar.wp_api_v2_client_android.data.repository.AttachmentRepository;
import com.afrozaar.wp_api_v2_client_android.data.repository.BlogRepository;
import com.afrozaar.wp_api_v2_client_android.data.repository.CommentRepository;
import com.afrozaar.wp_api_v2_client_android.data.repository.MetaRepository;
import com.afrozaar.wp_api_v2_client_android.data.repository.PostRepository;
import com.afrozaar.wp_api_v2_client_android.data.repository.TaxonomyRepository;
import com.afrozaar.wp_api_v2_client_android.data.repository.UserRepository;
import com.afrozaar.wp_api_v2_client_android.util.WordpressPreferenceHelper;

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
    private static final int VERSION_POST_UPLOADING_FLAG = 104;
    private static final int VERSION_MEDIA_UPLOAD_STATE = 105;
    private static final int VERSION_STREAM_ITEM_TABLE = 106;

    private static final int VERSION_NEW_REPOSITORIES = 200;
    private static final int VERSION_COMMENT_TABLE = 201;
    private static final int VERSION_POST_RETRY = 202;

    private static final int VERSION_CURRENT = VERSION_POST_RETRY;

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
        db.execSQL(BlogRepository.SCHEMA);
        db.execSQL(UserRepository.SCHEMA);
        db.execSQL(PostRepository.SCHEMA);
        db.execSQL(TaxonomyRepository.SCHEMA);
        db.execSQL(MetaRepository.SCHEMA);
        db.execSQL(AttachmentRepository.SCHEMA);
        db.execSQL(CommentRepository.SCHEMA);
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
            case VERSION_USERS_TABLE_UPDATE:
                upgradeV103To104(db);
            case VERSION_POST_UPLOADING_FLAG:
                upgradeV104To105(db);
            case VERSION_MEDIA_UPLOAD_STATE:
                upgradeV105To106(db);
            case VERSION_STREAM_ITEM_TABLE:
                upgradeV106To200(db);
            case VERSION_NEW_REPOSITORIES:
                upgradeV200To201(db);
            case VERSION_COMMENT_TABLE:
                upgradeV201To202(db);
        }
    }

    /**
     * Adds the update time column to POSTS table
     * <p>
     * 22/03/2016
     */
    private void upgradeV100To101(SQLiteDatabase db) {
        db.execSQL("ALTER TABLE " + PostRepository.TABLE_NAME + " ADD COLUMN "
                + PostRepository.UPDATED_TIME + " INTEGER");
    }

    /**
     * Migrated old media from reporter database to wordpress database
     * <p>
     * 29/03/2016
     */
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
     * <p>
     * 29/04/2016
     */
    private void upgradeV102To103(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS authors");

        db.execSQL(UserRepository.SCHEMA);
    }

    /**
     * Add UPLOADING flag column to Post
     * <p>
     * 30/05/2016
     */
    private void upgradeV103To104(SQLiteDatabase db) {
        db.execSQL("ALTER TABLE " + PostRepository.TABLE_NAME + " ADD COLUMN "
                + PostRepository.UPLOADING + " INTEGER DEFAULT 0");
    }

    /**
     * Adding upload state column to media
     * <p>
     * 07/06/2016
     */
    private void upgradeV104To105(SQLiteDatabase db) {
        db.execSQL("ALTER TABLE " + WordPressContract.Medias.TABLE_NAME + " ADD COLUMN "
                + WordPressContract.Medias.UPLOAD_STATE + " INTEGER DEFAULT 0");
    }

    /**
     * Adding new Post Stream table
     * <p>
     * 05/08/2016
     */
    private void upgradeV105To106(SQLiteDatabase db) {
        //db.execSQL(WordPressContract.StreamPost.SCHEMA);
    }

    /**
     * Added new repository classes for accessing database tables.
     * - New Attachments table to replace old Media one
     * - New field on Post table to replace StreamPost table
     * <p>
     * 12/08/2016
     */
    private void upgradeV106To200(SQLiteDatabase db) {
        db.execSQL(AttachmentRepository.SCHEMA);

        String selection = WordPressContract.Medias.POST_ROW_ID + " IS NOT ?";
        String[] selectionArgs = {"-1"};

        long authorId = WordpressPreferenceHelper.with(context).getWordPressUserId();

        Cursor cursor = db.query(WordPressContract.Medias.TABLE_NAME, null, selection, selectionArgs, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                ContentValues values = new ContentValues();
                values.put(AttachmentRepository.BLOG_ID, cursor.getLong(1));
                values.put(AttachmentRepository.WP_POST_ID, cursor.getLong(2));
                values.put(AttachmentRepository.POST_ROW_ID, cursor.getLong(3));
                values.put(AttachmentRepository.WP_MEDIA_ID, cursor.getLong(4));
                values.put(AttachmentRepository.ORIGIN_TYPE, cursor.getString(5));
                values.put(AttachmentRepository.ORIGIN_URI, cursor.getString(6));
                values.put(AttachmentRepository.CAPTION, cursor.getString(7));
                values.put(AttachmentRepository.SOURCE_URL, cursor.getString(8));
                values.put(AttachmentRepository.UPLOAD_STATE, cursor.getInt(9));
                values.put(AttachmentRepository.WP_AUTHOR_ID, authorId);

                db.insert(AttachmentRepository.TABLE_NAME, null, values);
            }
            cursor.close();
        }

        db.execSQL("DROP TABLE IF EXISTS " + TaxonomyRepository.TABLE_NAME);
        db.execSQL(TaxonomyRepository.SCHEMA);

        db.execSQL("ALTER TABLE " + PostRepository.TABLE_NAME
                + " ADD COLUMN " + PostRepository.IS_FEED_POST + " INTEGER DEFAULT 0");
        db.execSQL("ALTER TABLE " + PostRepository.TABLE_NAME
                + " ADD COLUMN " + PostRepository.DOWNLOADED + " INTEGER DEFAULT 0");
        db.execSQL("ALTER TABLE " + PostRepository.TABLE_NAME
                + " ADD COLUMN " + PostRepository.DOWNLOADED_BODY + " INTEGER DEFAULT 0");

        db.execSQL("DROP TABLE IF EXISTS stream_post");
    }

    private void upgradeV200To201(SQLiteDatabase db) {
        db.execSQL(CommentRepository.SCHEMA);
    }

    private void upgradeV201To202(SQLiteDatabase db) {
        db.execSQL("ALTER TABLE " + PostRepository.TABLE_NAME
                + " ADD COLUMN " + PostRepository.RETRY_COUNT + " INTEGER DEFAULT 0");
        db.execSQL("ALTER TABLE " + PostRepository.TABLE_NAME
                + " ADD COLUMN " + PostRepository.LAST_RETRY_TIME + " INTEGER DEFAULT 0");
    }

    public void deleteDatabase(Context context) {
        context.deleteDatabase(DATABASE_NAME);
    }
}
