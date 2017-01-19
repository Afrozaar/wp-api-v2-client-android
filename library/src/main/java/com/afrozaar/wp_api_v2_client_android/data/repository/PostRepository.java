package com.afrozaar.wp_api_v2_client_android.data.repository;

import android.content.ContentValues;
import android.database.Cursor;
import android.provider.BaseColumns;

import com.afrozaar.wp_api_v2_client_android.data.WordPressContract;
import com.afrozaar.wp_api_v2_client_android.model.Post;
import com.afrozaar.wp_api_v2_client_android.util.DataConverters;

/**
 * @author Jan-Louis Crafford
 *         Created on 2016/08/08.
 */
public class PostRepository extends BaseWpRepository implements WordPressContract.PostColumns,
        WordPressContract.PostExtraColumns {

    public static final String TABLE_NAME = "posts";

    public static final String SCHEMA = "CREATE TABLE " + TABLE_NAME + " ("
            + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + BLOG_ID + " INTEGER " + WordPressContract.References.BLOG_ID + ","
            + WP_AUTHOR_ID + " INTEGER " + WordPressContract.References.AUTHOR_ID + ","
            + WP_POST_ID + " INTEGER,"
            + DATE + " TEXT,"
            + DATE_GMT + " TEXT,"
            + GUID + " TEXT,"
            + MODIFIED + " TEXT,"
            + MODIFIED_GMT + " TEXT,"
            + PASSWORD + " TEXT,"
            + SLUG + " TEXT,"
            + STATUS + " TEXT,"
            + TYPE + " TEXT,"
            + LINK + " TEXT,"
            + TITLE + " TEXT,"
            + CONTENT + " TEXT,"
            + EXCERPT + " TEXT,"
            + FEATURED_MEDIA + " INTEGER,"
            + COMMENT_STATUS + " INTEGER,"
            + PING_STATUS + " INTEGER,"
            + STICKY + " INTEGER,"
            + FORMAT + " TEXT,"
            + CATEGORIES + " TEXT,"
            + TAGS + " TEXT,"
            + UPDATED + " INTEGER,"
            + UPDATED_TIME + " INTEGER,"
            + UPLOADING + " INTEGER DEFAULT 0,"
            + IS_FEED_POST + " INTEGER DEFAULT 0,"
            + DOWNLOADED + " INTEGER DEFAULT 0,"
            + DOWNLOADED_BODY + " INTEGER DEFAULT 0,"
            + RETRY_COUNT + " INTEGER DEFAULT 0,"
            + LAST_RETRY_TIME + " INTEGER DEFAULT 0)";

    public static final int IDX_BLOG_ID = 1;
    public static final int IDX_WP_AUTHOR_ID = 2;
    public static final int IDX_WP_POST_ID = 3;
    public static final int IDX_DATE = 4;
    public static final int IDX_DATE_GMT = 5;
    public static final int IDX_GUID = 6;
    public static final int IDX_MODIFIED = 7;
    public static final int IDX_MODIFIED_GMT = 8;
    public static final int IDX_PASSWORD = 9;
    public static final int IDX_SLUG = 10;
    public static final int IDX_STATUS = 11;
    public static final int IDX_TYPE = 12;
    public static final int IDX_LINK = 13;
    public static final int IDX_TITLE = 14;
    public static final int IDX_CONTENT = 15;
    public static final int IDX_EXCERPT = 16;
    public static final int IDX_FEATURED_MEDIA = 17;
    public static final int IDX_COMMENT_STATUS = 18;
    public static final int IDX_PING_STATUS = 19;
    public static final int IDX_STICKY = 20;
    public static final int IDX_FORMAT = 21;
    public static final int IDX_CATEGORIES = 22;
    public static final int IDX_TAGS = 23;
    public static final int IDX_UPDATED = 24;
    public static final int IDX_UPDATED_TIME = 25;
    public static final int IDX_UPLOADING = 26;
    public static final int IDX_IS_FEED_POST = 27;
    public static final int IDX_DOWNLOADED = 28;
    public static final int IDX_DOWNLOADED_BODY = 29;
    public static final int IDX_RETRY_COUNT = 30;
    public static final int IDX_LAST_RETRY_TIME = 31;

    public static ContentValues getContainsMap(long blogId, long authorId, long postId, long postRowId) {
        ContentValues values = new ContentValues();

        values.put(BLOG_ID, blogId);
        values.put(WP_AUTHOR_ID, authorId);

        if (postId != -1) {
            values.put(WP_POST_ID, postId);
        } else if (postRowId != -1) {
            values.put(_ID, postRowId);
        } else {
            return null;
        }

        return values;
    }

    public static ContentValues mapToContentValues(Post post, long blogId, long authorId) {
        ContentValues values = new ContentValues();

        values.put(BLOG_ID, blogId);
        values.put(WP_AUTHOR_ID, authorId);

        addValue(values, WP_POST_ID, post.getId());
        addValue(values, DATE, post.getDate());
        addValue(values, DATE_GMT, post.getDateGMT());
        addValue(values, GUID, post.getGuid() == null ? null : post.getGuid().getRendered());
        addValue(values, MODIFIED, post.getModified());
        addValue(values, MODIFIED_GMT, post.getModifiedGMT());
        addValue(values, PASSWORD, post.getPassword());
        addValue(values, SLUG, post.getSlug());
        addValue(values, STATUS, post.getStatus());
        addValue(values, TYPE, post.getType());
        addValue(values, LINK, post.getLink());
        addValue(values, TITLE, post.getTitle() == null ? null : post.getTitle().getRaw());
        addValue(values, CONTENT, post.getContent() == null ? null : post.getContent().getRendered());
        addValue(values, EXCERPT, post.getExcerpt() == null ? null : post.getExcerpt().getRendered());
        addValue(values, FEATURED_MEDIA, post.getFeaturedMedia());
        addValue(values, COMMENT_STATUS, post.getCommentStatus() == null ? null : post.getCommentStatus().getStatus());
        addValue(values, PING_STATUS, post.getPingStatus() == null ? null : post.getPingStatus().getStatus());
        addValue(values, STICKY, post.getSticky());
        addValue(values, FORMAT, post.getFormat());
        addValue(values, CATEGORIES, DataConverters.makeCategoryString(post.getCategories()));
        addValue(values, TAGS, DataConverters.makeTagString(post.getTags()));

        return values;
    }

    public static Post mapFromCursor(Cursor cursor) throws Exception {
        Post post = new Post();

        post.rowId = getRowId(cursor);

        post.withId(cursor.getLong(IDX_WP_POST_ID))
                .withDate(cursor.getString(IDX_DATE))
                .withDateGMT(cursor.getString(IDX_DATE_GMT))
                .withGuid(cursor.getString(IDX_GUID))
                .withModified(cursor.getString(IDX_MODIFIED))
                .withModifiedGMT(cursor.getString(IDX_MODIFIED_GMT))
                .withPassword(cursor.getString(IDX_PASSWORD))
                .withSlug(cursor.getString(IDX_SLUG))
                .withStatus(cursor.getString(IDX_STATUS))
                .withType(cursor.getString(IDX_TYPE))
                .withLink(cursor.getString(IDX_LINK))
                .withTitle(cursor.getString(IDX_TITLE))
                .withContent(cursor.getString(IDX_CONTENT))
                .withExcerpt(cursor.getString(IDX_EXCERPT))
                .withFeaturedMedia(cursor.getInt(IDX_FEATURED_MEDIA))
                .withCommentStatus(cursor.getShort(IDX_COMMENT_STATUS))
                .withPingStatus(cursor.getShort(IDX_PING_STATUS))
                .withSticky(cursor.getShort(IDX_STICKY) == 1)
                .withFormat(cursor.getString(IDX_FORMAT))
                .withCategories(DataConverters.makeCategoryIdList(cursor.getString(IDX_CATEGORIES)))
                .withTags(DataConverters.makeTagIdList(cursor.getString(IDX_TAGS)));

        return post;
    }
}
