package com.afrozaar.wp_api_v2_client_android.data.repository;

import android.provider.BaseColumns;

import com.afrozaar.wp_api_v2_client_android.data.WordPressContract;

/**
 * @author Jan-Louis Crafford
 *         Created on 2016/08/12.
 */
public class UserRepository extends BaseRepository implements WordPressContract.UsersColumns {

    public static final String TABLE_NAME = "users";

    public static final String SCHEMA = "CREATE TABLE " + TABLE_NAME + " ("
            + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + BLOG_ID + " INTEGER " + WordPressContract.References.BLOG_ID + ","
            + WP_AUTHOR_ID + " INTEGER,"
            + USERNAME + " TEXT,"
            + PASS + " TEXT,"
            + FULL_NAME + " TEXT,"
            + FIRST_NAME + " TEXT,"
            + LAST_NAME + " TEXT,"
            + EMAIL + " TEXT,"
            + URL + " TEXT,"
            + DESCRIPTION + " TEXT,"
            + LINK + " TEXT,"
            + NICKNAME + " TEXT,"
            + SLUG + " TEXT,"
            + REGISTERED_DATE + " TEXT,"
            + ROLES + " TEXT,"
            + AVATAR_URL + " TEXT)";

    public static final int IDX_BLOG_ID = 1;
    public static final int IDX_WP_AUTHOR_ID = 2;
    public static final int IDX_USERNAME = 3;
    public static final int IDX_PASSWORD = 4;
    public static final int IDX_FULL_NAME = 5;
    public static final int IDX_FIRST_NAME = 6;
    public static final int IDX_LAST_NAME = 7;
    public static final int IDX_EMAIL = 8;
    public static final int IDX_DESCRIPTION = 9;
    public static final int IDX_LINK = 10;
    public static final int IDX_NICKNAME = 11;
    public static final int IDX_SLUG = 12;
    public static final int IDX_REGISTERED_DATE = 13;
    public static final int IDX_ROLES = 14;
    public static final int IDX_AVATAR_URL = 15;

    

}
