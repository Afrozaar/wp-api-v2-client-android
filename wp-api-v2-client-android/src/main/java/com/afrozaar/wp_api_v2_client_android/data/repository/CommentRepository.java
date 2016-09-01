package com.afrozaar.wp_api_v2_client_android.data.repository;

import android.content.ContentValues;
import android.database.Cursor;
import android.provider.BaseColumns;

import com.afrozaar.wp_api_v2_client_android.data.WordPressContract;
import com.afrozaar.wp_api_v2_client_android.model.Comment;
import com.afrozaar.wp_api_v2_client_android.model.WPGeneric;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Jan-Louis Crafford
 *         Created on 2016/08/26.
 */
public class CommentRepository extends BaseWpRepository implements WordPressContract.CommentColumns {

    public static final String TABLE_NAME = "comments";

    public static final String SCHEMA = "CREATE TABLE " + TABLE_NAME + " ("
            + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + BLOG_ID + " INTEGER " + WordPressContract.References.BLOG_ID + ","
            + WP_AUTHOR_ID + " INTEGER " + WordPressContract.References.AUTHOR_ID + ","
            + WP_POST_ID + " INTEGER " + WordPressContract.References.POST_ID + ","
            + WP_PARENT_ID + " INTEGER,"
            + WP_COMMENT_ID + " INTEGER,"
            + AUTHOR_AVATAR_URLS + " TEXT,"
            + AUTHOR_EMAIL + " TEXT,"
            + AUTHOR_IP + " TEXT,"
            + AUTHOR_NAME + " TEXT,"
            + AUTHOR_URL + " TEXT,"
            + AUTHOR_USER_AGENT + " TEXT,"
            + CONTENT + " TEXT,"
            + DATE + " TEXT,"
            + DATE_GMT + " TEXT,"
            + KARMA + " INTEGER,"
            + LINK + " TEXT,"
            + STATUS + " TEXT,"
            + TYPE + " TEXT)";

    public static final int IDX_BLOG_ID = 1;
    public static final int IDX_WP_AUTHOR_ID = 2;
    public static final int IDX_WP_POST_ID = 3;
    public static final int IDX_WP_PARENT_ID = 4;
    public static final int IDX_WP_COMMENT_ID = 5;
    public static final int IDX_AUTHOR_AVATAR_URLS = 6;
    public static final int IDX_AUTHOR_EMAIL = 7;
    public static final int IDX_AUTHOR_IP = 8;
    public static final int IDX_AUTHOR_NAME = 9;
    public static final int IDX_AUTHOR_URL = 10;
    public static final int IDX_AUTHOR_USER_AGENT = 11;
    public static final int IDX_CONTENT = 12;
    public static final int IDX_DATE = 13;
    public static final int IDX_DATE_GMT = 14;
    public static final int IDX_KARMA = 15;
    public static final int IDX_LINK = 16;
    public static final int IDX_STATUS = 17;
    public static final int IDX_TYPE = 18;

    public static ContentValues getContainsMap(long blogId, long authorId, long postId, long parentId,
                                               long commentId) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(BLOG_ID, blogId);
        contentValues.put(WP_AUTHOR_ID, authorId);
        contentValues.put(WP_POST_ID, postId);
        contentValues.put(WP_PARENT_ID, parentId);
        contentValues.put(WP_COMMENT_ID, commentId);

        return contentValues;
    }

    public static ContentValues mapToContentValues(long blogId, Comment comment) {
        ContentValues values = new ContentValues();

        addValue(values, BLOG_ID, blogId);
        addValue(values, WP_AUTHOR_ID, comment.getAuthor());
        addValue(values, WP_POST_ID, comment.getPost());
        addValue(values, WP_PARENT_ID, comment.getParent());
        addValue(values, WP_COMMENT_ID, comment.getId());
        addValue(values, AUTHOR_AVATAR_URLS, new JSONObject(comment.getAuthorAvatarUrls()).toString());
        addValue(values, AUTHOR_EMAIL, comment.getAuthorEmail());
        addValue(values, AUTHOR_IP, comment.getAuthorIp());
        addValue(values, AUTHOR_NAME, comment.getAuthorName());
        addValue(values, AUTHOR_URL, comment.getAuthorUrl());
        addValue(values, AUTHOR_USER_AGENT, comment.getAuthorUserAgent());
        addValue(values, CONTENT, comment.getContent().getRaw());
        addValue(values, DATE, comment.getDate());
        addValue(values, DATE_GMT, comment.getDateGMT());
        addValue(values, KARMA, comment.getKarma());
        addValue(values, LINK, comment.getLink());
        addValue(values, STATUS, comment.getStatus());
        addValue(values, TYPE, comment.getType());

        return values;
    }

    public static Comment mapFromCursor(Cursor cursor) {
        Comment comment = new Comment();
        comment.rowId = getRowId(cursor);

        comment.setAuthor(cursor.getInt(IDX_WP_AUTHOR_ID));
        comment.setPost(cursor.getLong(IDX_WP_POST_ID));
        comment.setParent(cursor.getInt(IDX_WP_PARENT_ID));
        comment.setId(cursor.getLong(IDX_WP_COMMENT_ID));

        Map<String, String> map = new Gson().fromJson(cursor.getString(IDX_AUTHOR_AVATAR_URLS),
                new TypeToken<HashMap<String, Object>>() {
                }.getType());
        comment.setAuthorAvatarUrls(map);

        comment.setAuthorEmail(cursor.getString(IDX_AUTHOR_EMAIL));
        comment.setAuthorIp(cursor.getString(IDX_WP_AUTHOR_ID));
        comment.setAuthorName(cursor.getString(IDX_AUTHOR_NAME));
        comment.setAuthorUrl(cursor.getString(IDX_AUTHOR_URL));
        comment.setAuthorUserAgent(cursor.getString(IDX_AUTHOR_USER_AGENT));
        comment.setContent(new WPGeneric().withRaw(cursor.getString(IDX_CONTENT)));
        comment.setDate(cursor.getString(IDX_DATE));
        comment.setDateGMT(cursor.getString(IDX_DATE_GMT));
        comment.setKarma(cursor.getInt(IDX_KARMA));
        comment.setLink(cursor.getString(IDX_LINK));
        comment.setStatus(cursor.getString(IDX_STATUS));
        comment.setType(cursor.getString(IDX_TYPE));

        return comment;
    }
}
