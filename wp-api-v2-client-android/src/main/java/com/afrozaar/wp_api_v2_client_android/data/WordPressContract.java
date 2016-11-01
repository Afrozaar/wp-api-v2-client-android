package com.afrozaar.wp_api_v2_client_android.data;

import android.content.ContentValues;
import android.provider.BaseColumns;
import android.text.TextUtils;

import com.afrozaar.wp_api_v2_client_android.data.repository.BlogRepository;
import com.afrozaar.wp_api_v2_client_android.data.repository.PostRepository;
import com.afrozaar.wp_api_v2_client_android.data.repository.UserRepository;

/**
 * @author Jan-Louis Crafford
 *         Created on 2016/02/11.
 */
public class WordPressContract {

    /**
     * Shared columns for tables to link records
     */
    public interface BaseWPColumns {

        /**
         * Date object was created
         * <P>Type: TEXT</P>
         */
        String DATE = "date";

        /**
         * Date object was created, in GMT
         * <P>Type: TEXT</P>
         */
        String DATE_GMT = "date_gmt";

        /**
         * Globally unique identifier for object
         * <P>Type : TEXT</P>
         */
        String GUID = "guid";

        /**
         * Date object was last modified
         * <P>Type: TEXT</P>
         */
        String MODIFIED = "modified";

        /**
         * Date object was last modified, in GMT
         * <P>Type: TEXT</P>
         */
        String MODIFIED_GMT = "modified_gmt";

        /**
         * Alphanumeric identifier for the object unique to its type.
         * <P>Type : TEXT</P>
         */
        String SLUG = "slug";

        /**
         * Type of the object
         * <P>Type : TEXT</P>
         */
        String TYPE = "type";

        /**
         * URL to the object
         * <P>Type : TEXT</P>
         */
        String LINK = "link";

        /**
         * Title of the post
         * <P>Type: TEXT</P>
         */
        String TITLE = "title";

        /**
         * Whether or not comments are open on the object
         * <P>Type : INTEGER (short)</P>
         *
         * @see com.afrozaar.wp_api_v2_client_android.model.WPStatus
         */
        String COMMENT_STATUS = "comment_status";

        /**
         * Whether or not the object can be pinged.
         * <P>Type : INTEGER (short)</P>
         *
         * @see com.afrozaar.wp_api_v2_client_android.model.WPStatus
         */
        String PING_STATUS = "ping_status";
    }

    public interface BaseExtraColumns {

        /**
         * ID of the blog this record is linked to
         * <P>Type: INTEGER (long)</P>
         */
        String BLOG_ID = "blog_id";

        /**
         * ID of the user on the WP site
         * <P>Type: TEXT</P>
         */
        String WP_AUTHOR_ID = "wp_author_id";

        /**
         * Post id on WP
         * <P>Type: INTEGER (long)</P>
         */
        String WP_POST_ID = "wp_post_id";

        /**
         * Row ID of Post item for use when Post hasn't been uploaded yet.
         * <P>Type: INTEGER (long)</P>
         */
        String POST_ROW_ID = "post_row_id";

        /**
         * Flag to set if content has been updated locally
         * <P>Type: INTEGER (short)</P>
         */
        String UPDATED = "updated";

        /**
         * Time local content was updated
         * <P>Type: INTEGER (long)</P>
         */
        String UPDATED_TIME = "updated_time";
    }

    /**
     * Table for keeping track of multiple WP sites
     */
    public interface BlogColumns {

        /**
         * Url of the blog
         * <P>Type: TEXT</P>
         */
        String URL = "url";

        /**
         * Admin user name
         * <P>Type: TEXT</P>
         */
        String USER = "user";

        /**
         * Admin password
         * <P>Type: TEXT</P>
         */
        String PASS = "pass";
    }

    /**
     * WP author details
     */
    public interface UsersColumns {

        /**
         * Username on WP blog
         * <P>Type: TEXT</P>
         */
        String USERNAME = "username";

        /**
         * User password for blog access
         * <P>Type: TEXT</P>
         */
        String PASS = "password";

        /**
         * Full name of the user
         * <P>Type: TEXT</P>
         */
        String FULL_NAME = "full_name";

        /**
         * First name of the user
         * <P>Type: TEXT</P>
         */
        String FIRST_NAME = "first_name";

        /**
         * Last name of the user
         * <P>Type: TEXT</P>
         */
        String LAST_NAME = "last_name";

        /**
         * Email of the user
         * <P>Type: TEXT</P>
         */
        String EMAIL = "email";

        /**
         * <P>Type: TEXT</P>
         */
        String URL = "url";

        /**
         * <P>Type: TEXT</P>
         */
        String DESCRIPTION = "description";

        /**
         * <P>Type: TEXT</P>
         */
        String NICKNAME = "nickname";

        /**
         * <P>Type: TEXT</P>
         */
        String REGISTERED_DATE = "registered_date";

        /**
         * <P>Type: TEXT</P>
         */
        String ROLES = "roles";

        /**
         * User icon
         * <P>Type: TEXT</P>
         */
        String AVATAR_URL = "avatar_url";
    }

    /**
     * WP Post columns
     * <p/>
     * http://v2.wp-api.org/reference/posts/
     */
    public interface PostColumns {

        /**
         * Password to protect access to post
         * <P>Type : TEXT</P>
         */
        String PASSWORD = "password";

        /**
         * Named status for the object
         * <P>Type : TEXT</P>
         * <P>Valid values : publish, future, draft, pending, private</P>
         */
        String STATUS = "status";

        /**
         * Body of the post
         * <P>Type: TEXT</P>
         */
        String CONTENT = "content";

        /**
         * The excerpt for the object
         * <P>Type : TEXT</P>
         */
        String EXCERPT = "excerpt";

        /**
         * ID of the featured media
         * <P>Type: INTEGER (long)</P>
         */
        String FEATURED_MEDIA = "featured_media";

        /**
         * Whether or not the object should be treated as sticky
         * <P>Type : INTEGER (short/boolean)</P>
         */
        String STICKY = "sticky";

        /**
         * Format of the post
         * <P>Type: TEXT</P>
         */
        String FORMAT = "format";

        /**
         * +
         * Array of categories for this post
         * <P>Type: TEXT (array)</P>
         */
        String CATEGORIES = "categories";

        /**
         * Array of tags for the post
         * <P>Type: TEXT (array)</P>
         */
        String TAGS = "tags";
    }

    public interface PostExtraColumns {

        /**
         * Flag to specify if Post is being uploaded
         * <P>Type: INTEGER (short)</P>
         */
        String UPLOADING = "uploading";

        /**
         * Flag to set if Post is from app, or was pulled from the Post Feed
         * <P>Type: INTEGER (short)</P>
         */
        String IS_FEED_POST = "is_feed_post";

        /**
         * Flag to specify if Post object has been downloaded if it is part of the Post Feed
         * <P>Type: INTEGER (short)</P>
         */
        String DOWNLOADED = "downloaded";

        /**
         * Flag to specifiy if Post content, media and meta has been downloaded for a Feed post
         */
        String DOWNLOADED_BODY = "downloaded_body";

        /**
         * Number of times upload task has ran for this post.
         * <P>Type: INTEGER</P>
         */
        String RETRY_COUNT = "retry_count";

        /**
         * Timestamp of the last retry
         * <P>Type: INTEGER (long)</P>
         */
        String LAST_RETRY_TIME = "last_retry_time";
    }

    public interface TaxonomyColumns {

        /**
         * ID of object on WP site
         * <P>Type: INTEGER (long)</P>
         */
        String WP_TAXONOMY_ID = "wp_taxonomy_id";

        /**
         * ID of parent object
         * <P>Type: INTEGER (long)</P>
         */
        String WP_PARENT_ID = "wp_parent_id";

        /**
         * Object's label
         * <P>Type: TEXT</P>
         */
        String NAME = "name";

        /**
         * Object's description
         * <P>Type: TEXT</P>
         */
        String DESCRIPTION = "description";

        /**
         * Usage count for object
         * <P>Type: INTEGER</P>
         */
        String COUNT = "count";
    }

    public interface MetaColumns {

        /**
         * ID of object on WP
         * <P>Type: INTEGER (long)</P>
         */
        String WP_META_ID = "wp_meta_id";

        /**
         * Key of the object
         * <P>Type: TEXT</P>
         */
        String KEY = "meta_key";

        /**
         * Value of the object
         * <P>Type: TEXT</P>
         */
        String VALUE = "meta_value";
    }

    public interface AttachmentColumns {

        /**
         * Id of the object on WP
         * <P>Type: INTEGER (long)</P>
         */
        String WP_MEDIA_ID = "wp_media_id";

        /**
         * Alt text for the object
         * <P>Type: TEXT</P>
         */
        String ALT_TEXT = "alt_text";

        /**
         * Caption for the object
         * <P>Type: TEXT</P>
         */
        String CAPTION = "caption";

        /**
         * Description for the object
         * <P>Type: TEXT</P>
         */
        String DESCRIPTION = "description";

        /**
         * Type of the media object
         * <P>Type: TEXT</P>
         */
        String MEDIA_TYPE = "media_type";

        /**
         * MIME type of the object
         * <P>Type: TEXT</P>
         */
        String MIME_TYPE = "mime_type";

        /**
         * URL to the original resource file
         * <P>Type: TEXT</P>
         */
        String SOURCE_URL = "source_url";
    }

    public interface AttachmentExtraColumns {

        /**
         * ID field used to identify media object in an external data set.
         * <P>Type: INTEGER (long)</P>
         */
        String ORIGIN_ID = "origin_id";

        /**
         * The URI pointing to the location of the original media item.
         * This will usually be the location of the object on a device.
         * <P>Type: TEXT</P>
         */
        String ORIGIN_URI = "origin_uri";

        /**
         * Type of the file from the original media item
         * <P>Type: TEXT</P>
         */
        String ORIGIN_TYPE = "origin_type";

        /**
         * Flag to show upload state of object
         * <P>Type: INTEGER</P>
         */
        String UPLOAD_STATE = "upload_state";
    }

    @Deprecated
    public interface MediaColumns {

        /**
         * Id of the object on WP
         * <P>Type: INTEGER (long)</P>
         */
        String WP_MEDIA_ID = "wp_media_id";

        /**
         * Uri pointing to media object
         * <P>Type: TEXT</P>
         */
        String URI = "uri";

        /**
         * Optional caption on media
         * <P>Type: TEXT</P>
         */
        String CAPTION = "caption";

        /**
         * External URL for the object
         * <P>Type: TEXT</P>
         */
        String EXTERNAL_URL = "external_url";

        /**
         * Flag to show upload state of object
         * <P>Type: INTEGER</P>
         */
        String UPLOAD_STATE = "upload_state";
    }

    public interface CommentColumns {

        /**
         * ID of the parent object
         * Model field is 'parent'
         * <P>Type: INTEGER (long)</P>
         */
        String WP_PARENT_ID = "wp_parent_id";

        /**
         * ID of the object on WP.
         * Model field is 'id'
         * <P>Type: INTEGER (long)</P>
         */
        String WP_COMMENT_ID = "wp_comment_id";

        /**
         * Avatar URLs for the object author.
         * <P>Type: TEXT</P>
         */
        String AUTHOR_AVATAR_URLS = "author_avatar_urls";

        /**
         * Email address of the author
         * <P>Type: TEXT</P>
         */
        String AUTHOR_EMAIL = "author_email";

        /**
         * IP address for the object author.
         * <P>Type: TEXT</P>
         */
        String AUTHOR_IP = "author_ip";

        /**
         * Name of the author
         * <P>Type: TEXT</P>
         */
        String AUTHOR_NAME = "author_name";

        /**
         * URL for the object author.
         * <P>Type: TEXT</P>
         */
        String AUTHOR_URL = "author_url";

        /**
         * User agent for the object author.
         * <P>Type: TEXT</P>
         */
        String AUTHOR_USER_AGENT = "author_user_agent";

        /**
         * Content for the object.
         * <P>Type: TEXT</P>
         */
        String CONTENT = "content";

        /**
         * Karma for the object.
         * <P>Type: INTEGER</P>
         */
        String KARMA = "karma";

        /**
         * State of the object.
         * <P>Type: TEXT</P>
         */
        String STATUS = "status";
    }

    public interface References {
        String BLOG_ID = "REFERENCES " + BlogRepository.TABLE_NAME + "(" + BlogRepository.BLOG_ID + ")";
        String AUTHOR_ID = "REFERENCES " + UserRepository.TABLE_NAME + "(" + UserRepository.WP_AUTHOR_ID + ")";
        String POST_ID = "REFERENCES " + PostRepository.TABLE_NAME + "(" + PostRepository.WP_POST_ID + ")";
        String POST_ROW_ID = "REFERENCES " + PostRepository.TABLE_NAME + "(" + PostRepository._ID + ")";
    }

    public static class BaseWpTable implements BaseColumns, BaseWPColumns, BaseExtraColumns {
        public static final int IDX_ID = 0;
    }

    @Deprecated
    public static class Medias extends BaseWpTable implements MediaColumns {
        public static final String TABLE_NAME = "medias";

        public static final int IDX_BLOG_ID = 1;
        public static final int IDX_WP_POST_ID = 2;
        public static final int IDX_POST_ROW_ID = 3;
        public static final int IDX_WP_MEDIA_ID = 4;
        public static final int IDX_TYPE = 5;
        public static final int IDX_URI = 6;
        public static final int IDX_CAPTION = 7;
        public static final int IDX_EXTERNAL_URL = 8;
        public static final int IDX_UPLOAD_STATE = 9;

        public static final String SCHEMA = "CREATE TABLE " + TABLE_NAME + "("
                + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + BLOG_ID + " INTEGER " + References.BLOG_ID + ","
                + WP_POST_ID + " INTEGER " + References.POST_ID + ","
                + POST_ROW_ID + " INTEGER " + References.POST_ROW_ID + ","
                + WP_MEDIA_ID + " INTEGER,"
                + TYPE + " TEXT NOT NULL,"
                + URI + " TEXT NOT NULL,"
                + CAPTION + " TEXT,"
                + EXTERNAL_URL + " TEXT,"
                + UPLOAD_STATE + " INTEGER)";

        public static ContentValues makeContentValues(boolean update, long blogId, long postId,
                                                      long postRowId, long mediaId, String type,
                                                      String uri, String caption, String externalUrl) {
            ContentValues values = new ContentValues();
            if (!update) {
                values.put(BLOG_ID, blogId);
                values.put(WP_POST_ID, postId);
                values.put(POST_ROW_ID, postRowId);
                values.put(WP_MEDIA_ID, mediaId);
                values.put(TYPE, type);
                values.put(URI, uri);
                values.put(CAPTION, caption);
                values.put(EXTERNAL_URL, externalUrl);
            } else {
                if (blogId != -1) {
                    values.put(BLOG_ID, blogId);
                }
                if (postId != -1) {
                    values.put(WP_POST_ID, postId);
                }
                if (postRowId != -1) {
                    values.put(POST_ROW_ID, postRowId);
                }
                if (mediaId != -1) {
                    values.put(WP_MEDIA_ID, mediaId);
                }
                if (!TextUtils.isEmpty(type)) {
                    values.put(TYPE, type);
                }
                if (!TextUtils.isEmpty(uri)) {
                    values.put(URI, uri);
                }
                if (!TextUtils.isEmpty(caption)) {
                    values.put(CAPTION, caption);
                }
                if (!TextUtils.isEmpty(externalUrl)) {
                    values.put(EXTERNAL_URL, externalUrl);
                }
            }
            return values;
        }
    }
}
