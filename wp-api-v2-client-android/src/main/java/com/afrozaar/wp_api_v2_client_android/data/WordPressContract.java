package com.afrozaar.wp_api_v2_client_android.data;

import android.content.ContentValues;
import android.provider.BaseColumns;
import android.text.TextUtils;

/**
 * @author Jan-Louis Crafford
 *         Created on 2016/02/11.
 */
public class WordPressContract {

    /**
     * Table for keeping track of multiple WP sites
     */
    interface BlogColumns {

        /**
         * Title of the blog
         * <P>Type: TEXT</P>
         */
        String TITLE = "title";

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
     * Shared columns for tables to link records
     */
    interface BaseWPColumns {

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
    }

    /**
     * WP author details
     */
    interface UserColumns {

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
         * User icon
         * <P>Type: TEXT</P>
         */
        String AVATAR_URL = "avatar_url";
    }

    /**
     * WP Post columns
     */
    interface PostColumns {

        /**
         * Title of the post
         * <P>Type: TEXT</P>
         */
        String TITLE = "title";

        /**
         * Body of the post
         * <P>Type: TEXT</P>
         */
        String CONTENT = "content";

        /**
         * Format of the post
         * <P>Type: TEXT</P>
         */
        String FORMAT = "format";

        /**
         * Public url of the post
         * <P>Type: TEXT</P>
         */
        String EXT_URL = "ext_url";

        /**
         * Date post was made, in GMT
         * <P>Type: TEXT</P>
         */
        String DATE_CREATE = "date_gmt";

        /**
         * Date psot was last modified, in GMT
         * <P>Type: TEXT</P>
         */
        String DATE_MODIFIED = "modified_gmt";

        /**
         * ID of the featured image
         * <P>Type: INTEGER (long)</P>
         */
        String FEATURED_IMAGE = "featured_image";

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

    interface TaxonomyColumns {

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
         * Taxonomy type; category or tag
         * <P>Type: TEXT</P>
         */
        String TYPE = "type";

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

        /**
         * Public link to object
         * <P>Type: TEXT</P>
         */
        String LINK = "link";
    }

    interface MetaColumns {

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

    interface References {
        String BLOG_ID = "REFERENCES " + Blogs.TABLE_NAME + "(" + Blogs.BLOG_ID + ")";
        String AUTHOR_ID = "REFERENCES " + Users.TABLE_NAME + "(" + Users.WP_AUTHOR_ID + ")";
        String POST_ID = "REFERENCES " + Posts.TABLE_NAME + "(" + Posts.WP_POST_ID + ")";
    }

    public static class BaseWpTable implements BaseColumns, BaseWPColumns {
        public static final int IDX_ID = 0;
    }

    public static class Blogs extends BaseWpTable implements BlogColumns {
        public static final String TABLE_NAME = "blogs";

        public static final int IDX_TITLE = 1;
        public static final int IDX_URL = 2;
        public static final int IDX_USER = 3;
        public static final int IDX_PASS = 4;

        public static final String SCHEMA = "CREATE TABLE " + TABLE_NAME + " ("
                + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + TITLE + " TEXT NOT NULL,"
                + URL + " TEXT NOT NULL,"
                + USER + " TEXT NOT NULL,"
                + PASS + " TEXT NOT NULL)";

        /**
         * Creates a {@link ContentValues} object for insert or updating a Blog entry.
         * <p/>
         * For <i>INSERT</i>, all values will be committed as is.
         * <br/>
         * For <i>UPDATE</i>, only values that are <B>NOT NULL</B> will be updated
         *
         * @param update True if this is an UPDATE action, false for INSERT
         * @param title  Title of the blog
         * @param url    Url of the blog
         * @param user   Admin user of the blog
         * @param pass   Password for the admin user
         * @return ContentValues for inserting/updating
         */
        private static ContentValues makeContentValues(boolean update, String title, String url, String user, String pass) {
            ContentValues values = new ContentValues();

            if (!update) {
                values.put(TITLE, title);
                values.put(URL, url);
                values.put(USER, user);
                values.put(PASS, pass);
            } else {
                if (!TextUtils.isEmpty(title)) {
                    values.put(TITLE, title);
                }
                if (!TextUtils.isEmpty(url)) {
                    values.put(URL, url);
                }
                if (!TextUtils.isEmpty(user)) {
                    values.put(USER, user);
                }
                if (!TextUtils.isEmpty(pass)) {
                    values.put(PASS, pass);
                }
            }

            return values;
        }

        /**
         * Inserts a blog entry
         *
         * @see Blogs#makeContentValues(boolean, String, String, String, String)
         */
        public static ContentValues insert(String title, String url, String user, String pass) {
            return makeContentValues(false, title, url, user, pass);
        }

        /**
         * Updates a blog entry
         *
         * @see Blogs#makeContentValues(boolean, String, String, String, String)
         */
        public static ContentValues update(String title, String url, String user, String pass) {
            return makeContentValues(true, title, url, user, pass);
        }
    }

    public static class Users extends BaseWpTable implements UserColumns {
        public static final String TABLE_NAME = "users";

        public static final int IDX_BLOG_ID = 1;
        public static final int IDX_WP_USER_ID = 2;
        public static final int IDX_USERNAME = 3;
        public static final int IDX_PASSWORD = 4;
        public static final int IDX_FULL_NAME = 5;
        public static final int IDX_AVATAR_URL = 6;

        public static final String SCHEMA = "CREATE TABLE " + TABLE_NAME + " ("
                + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + BLOG_ID + " INTEGER " + References.BLOG_ID + ","
                + WP_AUTHOR_ID + " INTEGER,"
                + USERNAME + " TEXT NOT NULL,"
                + PASS + " TEXT NOT NULL,"
                + FULL_NAME + " TEXT,"
                + AVATAR_URL + " TEXT)";

        private static ContentValues makeContentValues(boolean update, long blogId, long userId,
                                                       String username, String password, String fullName,
                                                       String avatarUrl) {
            ContentValues values = new ContentValues();
            if (!update) {
                values.put(BLOG_ID, blogId);
                values.put(WP_AUTHOR_ID, userId);
                values.put(USERNAME, username);
                values.put(PASS, password);
                values.put(FULL_NAME, fullName);
                values.put(AVATAR_URL, avatarUrl);
            } else {
                if (blogId != -1) {
                    values.put(BLOG_ID, blogId);
                }
                if (userId != -1) {
                    values.put(WP_AUTHOR_ID, userId);
                }
                if (!TextUtils.isEmpty(username)) {
                    values.put(USERNAME, username);
                }
                if (!TextUtils.isEmpty(password)) {
                    values.put(PASS, password);
                }
                if (!TextUtils.isEmpty(fullName)) {
                    values.put(FULL_NAME, fullName);
                }
                if (!TextUtils.isEmpty(avatarUrl)) {
                    values.put(AVATAR_URL, avatarUrl);
                }
            }

            return values;
        }

        public static ContentValues insert(long blogId, long userId, String username, String password,
                                           String fullName, String avatarUrl) {
            return makeContentValues(false, blogId, userId, username, password, fullName, avatarUrl);
        }

        public static ContentValues update(long blogId, long userId, String username, String password,
                                           String fullName, String avatarUrl) {
            return makeContentValues(true, blogId, userId, username, password, fullName, avatarUrl);
        }
    }

    public static class Posts extends BaseWpTable implements PostColumns {
        public static final String TABLE_NAME = "posts";

        public static final int IDX_BLOG_ID = 1;
        public static final int IDX_WP_AUTHOR_ID = 2;
        public static final int IDX_WP_POST_ID = 3;
        public static final int IDX_TITLE = 4;
        public static final int IDX_CONTENT = 5;
        public static final int IDX_FORMAT = 6;
        public static final int IDX_EXT_URL = 7;
        public static final int IDX_DATE_CREATE = 8;
        public static final int IDX_DATE_MODIFIED = 9;
        public static final int IDX_FEATURED_IMAGE = 10;
        public static final int IDX_CATEGORIES = 11;
        public static final int IDX_TAGS = 12;

        public static final String SCHEMA = "CREATE TABLE " + TABLE_NAME + " ("
                + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + BLOG_ID + " INTEGER " + References.BLOG_ID + ","
                + WP_AUTHOR_ID + " INTEGER " + References.AUTHOR_ID + ","
                + WP_POST_ID + " INTEGER,"
                + TITLE + " TEXT NOT NULL,"
                + CONTENT + " TEXT,"
                + FORMAT + " TEXT,"
                + EXT_URL + " TEXT,"
                + DATE_CREATE + " TEXT,"
                + DATE_MODIFIED + " TEXT,"
                + FEATURED_IMAGE + " INTEGER,"
                + CATEGORIES + " TEXT,"
                + TAGS + " TEXT)";

        private static ContentValues makeContentValues(boolean update, long blogId, long authorId, long postId,
                                                       String title, String content, String format, String extUrl,
                                                       String dateCreated, String dateModified, long featuredImage,
                                                       String categories, String tags) {
            ContentValues values = new ContentValues();
            if (!update) {
                values.put(BLOG_ID, blogId);
                values.put(WP_AUTHOR_ID, authorId);
                values.put(WP_POST_ID, postId);
                values.put(TITLE, title);
                values.put(CONTENT, content);
                values.put(FORMAT, format);
                values.put(EXT_URL, extUrl);
                values.put(DATE_CREATE, dateCreated);
                values.put(DATE_MODIFIED, dateModified);
                values.put(FEATURED_IMAGE, featuredImage);
                values.put(CATEGORIES, categories);
                values.put(TAGS, tags);
            } else {
                if (blogId != -1) {
                    values.put(BLOG_ID, blogId);
                }
                if (authorId != -1) {
                    values.put(WP_AUTHOR_ID, authorId);
                }
                if (postId != -1) {
                    values.put(WP_POST_ID, postId);
                }
                if (!TextUtils.isEmpty(title)) {
                    values.put(TITLE, title);
                }
                if (!TextUtils.isEmpty(content)) {
                    values.put(CONTENT, content);
                }
                if (!TextUtils.isEmpty(format)) {
                    values.put(FORMAT, format);
                }
                if (!TextUtils.isEmpty(extUrl)) {
                    values.put(EXT_URL, extUrl);
                }
                if (!TextUtils.isEmpty(dateCreated)) {
                    values.put(DATE_CREATE, dateCreated);
                }
                if (!TextUtils.isEmpty(dateModified)) {
                    values.put(DATE_MODIFIED, dateModified);
                }
                if (featuredImage != -1) {
                    values.put(FEATURED_IMAGE, featuredImage);
                }
                if (!TextUtils.isEmpty(categories)) {
                    values.put(CATEGORIES, categories);
                }
                if (!TextUtils.isEmpty(tags)) {
                    values.put(TAGS, tags);
                }
            }

            return values;
        }

        public static ContentValues insert(long blogId, long authorId, long postId,
                                           String title, String content, String format, String extUrl,
                                           String dateCreated, String dateModified, long featuredImage,
                                           String categories, String tags) {
            return makeContentValues(false, blogId, authorId, postId, title, content, format, extUrl,
                    dateCreated, dateModified, featuredImage, categories, tags);
        }

        public static ContentValues update(long blogId, long authorId, long postId,
                                           String title, String content, String format, String extUrl,
                                           String dateCreated, String dateModified, long featuredImage,
                                           String categories, String tags) {
            return makeContentValues(true, blogId, authorId, postId, title, content, format, extUrl,
                    dateCreated, dateModified, featuredImage, categories, tags);
        }
    }

    public static class Taxonomies extends BaseWpTable implements TaxonomyColumns {
        public static final String TABLE_NAME = "taxonomies";

        public static final int IDX_BLOG_ID = 1;
        public static final int IDX_WP_TAXONOMY_ID = 2;
        public static final int IDX_WP_PARENT_ID = 3;
        public static final int IDX_TYPE = 4;
        public static final int IDX_NAME = 5;
        public static final int IDX_DESCRIPTION = 6;
        public static final int IDX_COUNT = 7;
        public static final int IDX_LINK = 8;

        public static final String SCHEMA = "CREATE TABLE " + TABLE_NAME + " ("
                + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + BLOG_ID + " INTEGER " + References.BLOG_ID + ","
                + WP_TAXONOMY_ID + " INTEGER,"
                + WP_PARENT_ID + " INTEGER,"
                + TYPE + " TEXT NOT NULL,"
                + NAME + " TEXT NOT NULL,"
                + DESCRIPTION + " TEXT,"
                + COUNT + " INTEGER,"
                + LINK + " TEXT)";

        private static ContentValues makeContentValues(boolean update, long blogId, long taxId, long parentId,
                                                       String type, String name, String description,
                                                       int count, String link) {
            ContentValues values = new ContentValues();
            if (!update) {
                values.put(BLOG_ID, blogId);
                values.put(WP_TAXONOMY_ID, taxId);
                values.put(WP_PARENT_ID, parentId);
                values.put(TYPE, type);
                values.put(NAME, name);
                values.put(DESCRIPTION, description);
                values.put(COUNT, count);
                values.put(LINK, link);
            } else {
                if (blogId != -1) {
                    values.put(BLOG_ID, blogId);
                }
                if (taxId != -1) {
                    values.put(WP_TAXONOMY_ID, taxId);
                }
                if (parentId != -1) {
                    values.put(WP_PARENT_ID, parentId);
                }
                if (!TextUtils.isEmpty(type)) {
                    values.put(TYPE, type);
                }
                if (!TextUtils.isEmpty(name)) {
                    values.put(NAME, name);
                }
                if (!TextUtils.isEmpty(DESCRIPTION)) {
                    values.put(DESCRIPTION, description);
                }
                if (count != -1) {
                    values.put(COUNT, count);
                }
                if (!TextUtils.isEmpty(link)) {
                    values.put(LINK, link);
                }
            }
            return values;
        }

        public static ContentValues insert(long blogId, long taxId, long parentId, String type, String name,
                                           String description, int count, String link) {
            return makeContentValues(false, blogId, taxId, parentId, type, name, description, count, link);
        }

        public static ContentValues update(long blogId, long taxId, long parentId, String type, String name,
                                           String description, int count, String link) {
            return makeContentValues(true, blogId, taxId, parentId, type, name, description, count, link);
        }
    }

    public static class Metas extends BaseWpTable implements MetaColumns {
        public static final String TABLE_NAME = "metas";

        public static final int IDX_BLOG_ID = 1;
        public static final int IDX_WP_POST_ID = 2;
        public static final int IDX_WP_META_ID = 3;
        public static final int IDX_KEY = 4;
        public static final int IDX_VALUE = 5;

        public static final String SCHEMA = "CREATE TABLE " + TABLE_NAME + " ("
                + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + BLOG_ID + " INTEGER " + References.BLOG_ID + ","
                + WP_POST_ID + " INTEGER " + References.POST_ID + ","
                + WP_META_ID + " INTEGER,"
                + KEY + " TEXT NOT NULL,"
                + VALUE + " TEXT NOT NULL)";

        private static ContentValues makeContentValues(boolean update, long blogId, long postId, long metaId,
                                                       String key, String value) {
            ContentValues values = new ContentValues();
            if (!update) {
                values.put(BLOG_ID, blogId);
                values.put(WP_POST_ID, postId);
                values.put(WP_META_ID, metaId);
                values.put(KEY, key);
                values.put(VALUE, value);
            } else {
                if (blogId != -1) {
                    values.put(BLOG_ID, blogId);
                }
                if (postId != -1) {
                    values.put(WP_POST_ID, postId);
                }
                if (metaId != -1) {
                    values.put(WP_META_ID, metaId);
                }
                if (!TextUtils.isEmpty(key)) {
                    values.put(KEY, key);
                }
                if (!TextUtils.isEmpty(value)) {
                    values.put(VALUE, value);
                }
            }
            return values;
        }

        public static ContentValues insert(long blogId, long postId, long metaId,
                                           String key, String value) {
            return makeContentValues(false, blogId, postId, metaId, key, value);
        }

        public static ContentValues update(long blogId, long postId, long metaId,
                                           String key, String value) {
            return makeContentValues(true, blogId, postId, metaId, key, value);
        }
    }
}
