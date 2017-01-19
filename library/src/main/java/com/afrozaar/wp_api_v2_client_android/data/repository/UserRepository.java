package com.afrozaar.wp_api_v2_client_android.data.repository;

import android.content.ContentValues;
import android.database.Cursor;
import android.provider.BaseColumns;
import android.text.TextUtils;

import com.afrozaar.wp_api_v2_client_android.data.WordPressContract;
import com.afrozaar.wp_api_v2_client_android.model.User;
import com.afrozaar.wp_api_v2_client_android.util.DataConverters;

/**
 * @author Jan-Louis Crafford
 *         Created on 2016/08/12.
 */
public class UserRepository extends BaseWpRepository implements WordPressContract.UsersColumns {

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
    public static final int IDX_URL = 9;
    public static final int IDX_DESCRIPTION = 10;
    public static final int IDX_LINK = 11;
    public static final int IDX_NICKNAME = 12;
    public static final int IDX_SLUG = 13;
    public static final int IDX_REGISTERED_DATE = 14;
    public static final int IDX_ROLES = 15;
    public static final int IDX_AVATAR_URL = 16;

    public static ContentValues getContainsMap(long blogId, long authorId) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(BLOG_ID, blogId);
        contentValues.put(WP_AUTHOR_ID, authorId);

        return contentValues;
    }

    public static ContentValues mapToContentValues(User user, long blogId) {
        ContentValues values = new ContentValues();

        values.put(BLOG_ID, blogId);

        addValue(values, WP_AUTHOR_ID, user.getId());
        addValue(values, USERNAME, user.getUsername());
        addValue(values, PASS, user.getPassword());
        addValue(values, FULL_NAME, user.getName());
        addValue(values, FIRST_NAME, user.getFirstName());
        addValue(values, LAST_NAME, user.getLastName());
        addValue(values, EMAIL, user.getLastName());
        addValue(values, DESCRIPTION, user.getDescription());
        addValue(values, LINK, user.getLink());
        addValue(values, NICKNAME, user.getNickName());
        addValue(values, SLUG, user.getSlug());
        addValue(values, REGISTERED_DATE, user.getRegisteredDate());
        addValue(values, ROLES, DataConverters.makeUserRoleString(user.getRoles()));

        if (user.getAvatarUrls() != null) {
            String avatarUrl = user.getAvatarUrls().get("96");
            addValue(values, AVATAR_URL, avatarUrl);
        }

        return values;
    }

    public static User mapFromCursor(Cursor cursor) {
        User user = new User();

        user.rowId = getRowId(cursor);

        user.withId(cursor.getLong(IDX_WP_AUTHOR_ID))
                .withUsername(cursor.getString(IDX_USERNAME))
                .withPassword(cursor.getString(IDX_PASSWORD))
                .withName(cursor.getString(IDX_FULL_NAME))
                .withFirstName(cursor.getString(IDX_FIRST_NAME))
                .withLastName(cursor.getString(IDX_LAST_NAME))
                .withEmail(cursor.getString(IDX_EMAIL))
                .withUrl(cursor.getString(IDX_URL))
                .withDescription(cursor.getString(IDX_DESCRIPTION))
                .withLink(cursor.getString(IDX_LINK))
                .withNickName(cursor.getString(IDX_NICKNAME))
                .withSlug(cursor.getString(IDX_SLUG))
                .withRegisteredDate(cursor.getString(IDX_REGISTERED_DATE))
                .withRoles(DataConverters.makeUserRoleListFromString(cursor.getString(IDX_ROLES)));

        String avatarUrl = cursor.getString(IDX_AVATAR_URL);
        if (!TextUtils.isEmpty(avatarUrl)) {
            user.withAvatarUrl("96", avatarUrl);
        }

        return user;
    }
}
