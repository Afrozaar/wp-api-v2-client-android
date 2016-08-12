package com.afrozaar.wp_api_v2_client_android.data.repository;

import android.content.ContentValues;
import android.database.Cursor;
import android.provider.BaseColumns;

import com.afrozaar.wp_api_v2_client_android.data.WordPressContract;
import com.afrozaar.wp_api_v2_client_android.model.Media;

/**
 * @author Jan-Louis Crafford
 *         Created on 2016/08/12.
 */
public class AttachmentRepository extends BaseRepository implements WordPressContract.AttachmentColumns,
        WordPressContract.AttachmentExtraColumns {

    public static final String TABLE_NAME = "attachments";

    public static final String SCHEMA = "CREATE TABLE " + TABLE_NAME + "("
            + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + BLOG_ID + " INTEGER " + WordPressContract.References.BLOG_ID + ","
            + WP_AUTHOR_ID + " INTEGER " + WordPressContract.References.AUTHOR_ID + ","
            + WP_POST_ID + " INTEGER " + WordPressContract.References.POST_ID + ","
            + POST_ROW_ID + " INTEGER " + WordPressContract.References.POST_ROW_ID + ","
            + WP_MEDIA_ID + " INTEGER,"
            + DATE + " TEXT,"
            + DATE_GMT + " TEXT,"
            + GUID + " TEXT,"
            + MODIFIED + " TEXT,"
            + MODIFIED_GMT + " TEXT,"
            + SLUG + " TEXT,"
            + TYPE + " TEXT,"
            + LINK + " TEXT,"
            + TITLE + " TEXT,"
            + COMMENT_STATUS + " INTEGER,"
            + PING_STATUS + " INTEGER,"
            + ALT_TEXT + " TEXT,"
            + CAPTION + " TEXT,"
            + DESCRIPTION + " TEXT,"
            + MEDIA_TYPE + " TEXT,"
            + MIME_TYPE + " TEXT,"
            + SOURCE_URL + " TEXT,"
            + ORIGIN_ID + " INTEGER DEFAULT -1,"
            + ORIGIN_URI + " TEXT,"
            + UPLOAD_STATE + " INTEGER DEFAULT 0)";

    public static final int IDX_BLOG_ID = 1;
    public static final int IDX_WP_AUTHOR_ID = 2;
    public static final int IDX_WP_POST_ID = 3;
    public static final int IDX_POST_ROW_ID = 4;
    public static final int IDX_WP_MEDIA_ID = 5;
    public static final int IDX_DATE = 6;
    public static final int IDX_DATE_GMT = 7;
    public static final int IDX_GUID = 8;
    public static final int IDX_MODIFIED = 9;
    public static final int IDX_MODIFIED_GMT = 10;
    public static final int IDX_SLUG = 11;
    public static final int IDX_TYPE = 12;
    public static final int IDX_LINK = 13;
    public static final int IDX_TITLE = 14;
    public static final int IDX_COMMENT_STATUS = 15;
    public static final int IDX_PING_STATUS = 16;
    public static final int IDX_ALT_TEXT = 17;
    public static final int IDX_CAPTION = 18;
    public static final int IDX_DESCRIPTION = 19;
    public static final int IDX_MEDIA_TYPE = 20;
    public static final int IDX_MIME_TYPE = 21;
    public static final int IDX_SOURCE_URL = 22;
    public static final int IDX_ORIGIN_ID = 23;
    public static final int IDX_ORIGIN_URI = 24;
    public static final int IDX_UPLOAD_STATE = 25;

    public static ContentValues getContainsMap(long postId, long postRowId, Media media, long origId) {
        ContentValues values = new ContentValues();

        if (postId != -1) {
            values.put(WP_POST_ID, postId);
        } else if (postRowId != -1) {
            values.put(POST_ROW_ID, postRowId);
        } else {
            throw new IllegalStateException("Media item does not have valid post id!\n" + media.toString());
        }

        values.put(MIME_TYPE, media.getMimeType());

        if (media.getId() != -1) {
            values.put(WP_MEDIA_ID, media.getId());
        } else if (origId != -1) {
            values.put(ORIGIN_ID, origId);
        } else {
            throw new IllegalStateException("Media item does not have WP or orig id!\n" + media.toString());
        }

        return values;
    }

    public static ContentValues mapToContentValues(Media media) {
        ContentValues values = new ContentValues();

        addValue(values, WP_MEDIA_ID, media.getId());
        addValue(values, DATE, media.getDate());
        addValue(values, DATE_GMT, media.getDateGMT());
        addValue(values, GUID, media.getGuid().getRaw());
        addValue(values, MODIFIED, media.getModified());
        addValue(values, MODIFIED_GMT, media.getModifiedGMT());
        addValue(values, SLUG, media.getSlug());
        addValue(values, TYPE, media.getType());
        addValue(values, LINK, media.getLink());
        addValue(values, TITLE, media.getTitle().getRaw());
        addValue(values, COMMENT_STATUS, media.getCommentStatus().getStatus());
        addValue(values, PING_STATUS, media.getPingStatus().getStatus());

        addValue(values, ALT_TEXT, media.getAltText());
        addValue(values, CAPTION, media.getCaption());
        addValue(values, DESCRIPTION, media.getDescription());
        addValue(values, MEDIA_TYPE, media.getMediaType());
        addValue(values, SOURCE_URL, media.getSourceUrl());

        return values;
    }

    public static Media mapFromCursor(Cursor cursor) {
        Media media = new Media();

        media.rowId = getRowId(cursor);

        media.withId(cursor.getLong(IDX_WP_MEDIA_ID))
                .withDate(cursor.getString(IDX_DATE))
                .withDateGMT(cursor.getString(IDX_DATE_GMT))
                .withGuid(cursor.getString(IDX_GUID))
                .withModified(cursor.getString(IDX_MODIFIED))
                .withModifiedGMT(cursor.getString(IDX_MODIFIED_GMT))
                .withSlug(cursor.getString(IDX_SLUG))
                .withType(cursor.getString(IDX_TYPE))
                .withLink(cursor.getString(IDX_LINK))
                .withTitle(cursor.getString(IDX_TITLE))
                .withCommentStatus(cursor.getShort(IDX_COMMENT_STATUS))
                .withPingStatus(cursor.getShort(IDX_PING_STATUS))
                .withAltText(cursor.getString(IDX_ALT_TEXT))
                .withCaption(cursor.getString(IDX_CAPTION))
                .withDescription(cursor.getString(IDX_DESCRIPTION))
                .withMediaType(cursor.getString(IDX_MEDIA_TYPE))
                .withMimeType(cursor.getString(IDX_MIME_TYPE))
                .withSourceUrl(cursor.getString(IDX_SOURCE_URL));

        return media;
    }
}
