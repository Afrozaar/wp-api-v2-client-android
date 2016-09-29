package com.afrozaar.wp_api_v2_client_android.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import com.afrozaar.wp_api_v2_client_android.data.repository.AttachmentRepository;
import com.afrozaar.wp_api_v2_client_android.data.repository.BlogRepository;
import com.afrozaar.wp_api_v2_client_android.data.repository.CommentRepository;
import com.afrozaar.wp_api_v2_client_android.data.repository.MetaRepository;
import com.afrozaar.wp_api_v2_client_android.data.repository.PostRepository;
import com.afrozaar.wp_api_v2_client_android.data.repository.TaxonomyRepository;
import com.afrozaar.wp_api_v2_client_android.data.repository.UserRepository;
import com.afrozaar.wp_api_v2_client_android.model.Blog;
import com.afrozaar.wp_api_v2_client_android.model.Comment;
import com.afrozaar.wp_api_v2_client_android.model.Media;
import com.afrozaar.wp_api_v2_client_android.model.Meta;
import com.afrozaar.wp_api_v2_client_android.model.Post;
import com.afrozaar.wp_api_v2_client_android.model.Taxonomy;
import com.afrozaar.wp_api_v2_client_android.model.User;
import com.afrozaar.wp_api_v2_client_android.util.LogUtils;

import java.util.Map;

/**
 * @author Jan-Louis Crafford
 *         Created on 2016/08/08.
 */
public class DatabaseUtil {

    public static synchronized void insertBlog(SQLiteDatabase database, Blog blog) {
        ContentValues values = BlogRepository.mapToContentValues(blog);

        // TODO add proper checks for updating blogs using id
        if (containsData(database, BlogRepository.TABLE_NAME, BlogRepository.getContainsMap(blog))) {
            String where = BlogRepository.URL + "=?";
            String[] whereArgs = {blog.url};

            database.update(BlogRepository.TABLE_NAME, values, where, whereArgs);
        } else {
            database.insert(BlogRepository.TABLE_NAME, null, values);
        }
    }

    public static synchronized void insertUser(SQLiteDatabase database, long blogId, User user) {
        ContentValues values = UserRepository.mapToContentValues(user, blogId);

        if (containsData(database, UserRepository.TABLE_NAME, UserRepository.getContainsMap(blogId, user.getId()))) {
            String where = UserRepository.WP_AUTHOR_ID + "=?";
            String[] whereArgs = {user.getId() + ""};

            database.update(UserRepository.TABLE_NAME, values, where, whereArgs);
        } else {
            database.insert(UserRepository.TABLE_NAME, null, values);
        }
    }

    public static synchronized long insertPost(SQLiteDatabase database, long blogId, long authorId, Post post, long postRowId) {
        ContentValues values = PostRepository.mapToContentValues(post, blogId, authorId);

        return insertPost(database, blogId, authorId, post.getId(), postRowId, values);
    }

    public static synchronized long insertPost(SQLiteDatabase database, long blogId, long authorId,
                                               long postId, long postRowId, ContentValues values) {
        if (containsData(database, PostRepository.TABLE_NAME, PostRepository.getContainsMap(
                blogId, authorId, postId, postRowId))) {
            StringBuilder builder = new StringBuilder();
            builder.append(BlogRepository.BLOG_ID)
                    .append("=? AND ")
                    .append(PostRepository.WP_AUTHOR_ID)
                    .append("=? AND ");

            String[] whereArgs = new String[3];
            whereArgs[0] = blogId + "";
            whereArgs[1] = authorId + "";

            if (postId != -1) {
                builder.append(PostRepository.WP_POST_ID)
                        .append("=?");
                whereArgs[2] = postId + "";
            } else if (postRowId != -1) {
                builder.append(PostRepository._ID)
                        .append("=?");
                whereArgs[2] = postRowId + "";
            }

            database.update(PostRepository.TABLE_NAME, values, builder.toString(), whereArgs);

            if (postRowId == -1) {
                String[] projection = {PostRepository._ID};
                String selection = PostRepository.WP_POST_ID + "=?";
                String[] selectionArgs = {postId + ""};

                Cursor cursor = database.query(PostRepository.TABLE_NAME, projection, selection,
                        selectionArgs, null, null, null);
                if (cursor != null) {
                    if (cursor.moveToFirst()) {
                        postRowId = cursor.getLong(0);
                    }
                    cursor.close();
                }
            }

            return postRowId;
        } else {
            return database.insert(PostRepository.TABLE_NAME, null, values);
        }
    }

    public static synchronized void insertMeta(SQLiteDatabase database, long blogId, long postId, long postRowId,
                                               Meta meta) {
        ContentValues values = MetaRepository.mapToContentValues(meta, blogId, postId, postRowId);

        if (containsData(database, MetaRepository.TABLE_NAME, MetaRepository.getContainsMap(meta,
                blogId, postId, postRowId))) {
            StringBuilder builder = new StringBuilder();
            builder.append(MetaRepository.KEY)
                    .append("=? AND ");

            String[] whereArgs = new String[2];
            whereArgs[0] = meta.getKey();

            if (postId != -1) {
                builder.append(MetaRepository.WP_POST_ID)
                        .append("=?");
                whereArgs[1] = postId + "";
            } else if (postRowId != -1) {
                builder.append(MetaRepository.POST_ROW_ID)
                        .append("=?");
                whereArgs[1] = postRowId + "";
            }

            database.update(MetaRepository.TABLE_NAME, values, builder.toString(), whereArgs);
        } else {
            database.insert(MetaRepository.TABLE_NAME, null, values);
        }
    }

    public static synchronized void insertTaxonomy(SQLiteDatabase database, long blogId, Taxonomy taxonomy) {
        ContentValues values = TaxonomyRepository.mapToContentValues(taxonomy, blogId);

        if (containsData(database, TaxonomyRepository.TABLE_NAME, TaxonomyRepository.getContainsMap(taxonomy, blogId))) {
            String where = TaxonomyRepository.WP_PARENT_ID + "=? AND "
                    + TaxonomyRepository.WP_TAXONOMY_ID + "=?";
            String[] whereArgs = {taxonomy.getParent() + "", taxonomy.getId() + ""};

            database.update(TaxonomyRepository.TABLE_NAME, values, where, whereArgs);
        } else {
            database.insert(TaxonomyRepository.TABLE_NAME, null, values);
        }
    }

    public static synchronized void insertAttachment(SQLiteDatabase database, long blogId, long authorId, long postId,
                                                     long postRowId, Media media, long origId) {
        ContentValues values = AttachmentRepository.mapToContentValues(media, blogId, authorId, postId, postRowId);

        if (containsData(database, AttachmentRepository.TABLE_NAME, AttachmentRepository.getContainsMap(
                postId, postRowId, media, origId))) {
            StringBuilder builder = new StringBuilder();
            String[] whereArgs = new String[3];

            if (postId != -1) {
                builder.append(AttachmentRepository.WP_POST_ID);
                whereArgs[0] = postId + "";
            } else if (postRowId != -1) {
                builder.append(AttachmentRepository.POST_ROW_ID);
                whereArgs[0] = postRowId + "";
            }
            builder.append("=? AND ")
                    .append(AttachmentRepository.ORIGIN_TYPE + "=? AND ");
            whereArgs[1] = media.origType;

            if (media.getId() != -1) {
                builder.append(AttachmentRepository.WP_MEDIA_ID + "=?");
                whereArgs[2] = media.getId() + "";
            } else if (origId != -1) {
                builder.append(AttachmentRepository.ORIGIN_ID + "=?");
                whereArgs[2] = origId + "";
            } else {
                builder.append(AttachmentRepository.ORIGIN_URI + "=?");
                whereArgs[2] = media.origUri;
            }

            int result = database.update(AttachmentRepository.TABLE_NAME, values, builder.toString(), whereArgs);

            LogUtils.v("insertAttachment - update result=" + result + "; values=" + values.toString());
        } else {
            long result = database.insert(AttachmentRepository.TABLE_NAME, null, values);
            LogUtils.v("insertAttachment - insert result=" + result);
        }
    }

    public static synchronized void insertComment(SQLiteDatabase database, long blogId, Comment comment) {
        ContentValues contentValues = CommentRepository.mapToContentValues(blogId, comment);

        if (containsData(database, CommentRepository.TABLE_NAME, CommentRepository.getContainsMap(blogId,
                comment.getAuthor(), comment.getPost(), comment.getParent(), comment.getId()))) {

            String where = getSelection(CommentRepository.BLOG_ID, CommentRepository.WP_AUTHOR_ID,
                    CommentRepository.WP_POST_ID, CommentRepository.WP_PARENT_ID, CommentRepository.WP_COMMENT_ID);
            String[] whereArgs = {blogId + "", comment.getAuthor() + "", comment.getPost() + "",
                    comment.getParent() + "", comment.getId() + ""};

            int result = database.update(CommentRepository.TABLE_NAME, contentValues, where, whereArgs);
            LogUtils.v("insertComment - update result=" + result);
        } else {
            long result = database.insert(CommentRepository.TABLE_NAME, null, contentValues);
            LogUtils.v("insertComment - insert result=" + result);
        }
    }

    /**
     * Checks if table contains a record
     *
     * @param db            Database to use
     * @param tableName     Table to read from
     * @param contentValues ContentValues to use for reading entry
     * @return true if entry exists in table
     */
    public static boolean containsData(SQLiteDatabase db, String tableName, ContentValues contentValues) {
        if (contentValues == null) {
            return false;
        }

        String[] columns = new String[contentValues.size()];
        String[] selectionArgs = new String[contentValues.size()];
        int cnt = 0;
        for (Map.Entry<String, Object> entry : contentValues.valueSet()) {
            columns[cnt] = entry.getKey();
            selectionArgs[cnt] = String.valueOf(entry.getValue());
            cnt++;
        }
        return containsData(db, tableName, columns, selectionArgs);
    }

    /**
     * Checks if table contains a record
     *
     * @param db            Database to use
     * @param tableName     Table to read from
     * @param columns       Column selections
     * @param selectionArgs Column selection arguments
     * @return true if entry exists
     */
    public static boolean containsData(SQLiteDatabase db, String tableName, String[] columns, String[] selectionArgs) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < columns.length; i++) {
            if (i > 0) {
                sb.append(" and ");
            }
            sb.append(columns[i]).append("=?");
        }
        Cursor c = db.query(tableName, columns, sb.toString(), selectionArgs, null, null, null);
        boolean val = c != null && c.moveToFirst();
        if (c != null) {
            c.close();
        }
        return val;
    }

    /**
     * Helper methods for creating a SQL where selection string
     *
     * @param columns Columns to add to WHERE string
     * @return Selection string
     */
    public static String getSelection(String... columns) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < columns.length; i++) {
            builder.append(columns[i]);
            builder.append("=?");
            if (i != columns.length - 1) {
                builder.append(" AND ");
            }
        }
        return builder.toString();
    }

    public static String getNotSelection(String... columns) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < columns.length; i++) {
            builder.append(columns[i]);
            builder.append(" IS NOT ?");
            if (i != columns.length - 1) {
                builder.append(" AND ");
            }
        }
        return builder.toString();
    }

    public static String getProjectionString(String[] projection, String table) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < projection.length; i++) {
            if (!TextUtils.isEmpty(table)) {
                builder.append(table)
                        .append(".");
            }
            builder.append(projection[i]);
            if (i != (projection.length - 1)) {
                builder.append(", ");
            }
        }

        return builder.toString();
    }

    /**
     * Utility method for printing out the contents of a Cursor object
     *
     * @param cursor Cursor to print to output
     */
    public static void printCursor(Cursor cursor) {
        if (cursor == null) {
            return;
        }

        cursor.moveToFirst();
        if (cursor.getCount() == 0) {
            return;
        }

        String[] columns = cursor.getColumnNames();
        do {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < columns.length; i++) {
                builder.append(columns[i])
                        .append("=")
                        .append(cursor.getString(cursor.getColumnIndex(columns[i])))
                        .append(" ; ");
            }
            LogUtils.d("CURSOR", builder.toString());
        } while (cursor.moveToNext());

        cursor.close();
    }

    public static void printCursor(Cursor cursor, int numOfRecords) {
        if (cursor == null || numOfRecords == 0) {
            return;
        }

        cursor.moveToFirst();
        if (cursor.getCount() == 0) {
            return;
        }
        if (cursor.getCount() < numOfRecords) {
            numOfRecords = cursor.getCount();
        }

        String[] columns = cursor.getColumnNames();
        for (int i = 0; i < numOfRecords; i++) {
            cursor.moveToPosition(i);

            StringBuilder builder = new StringBuilder();
            for (String column : columns) {
                builder.append(column)
                        .append("=")
                        .append(cursor.getString(cursor.getColumnIndex(column)))
                        .append(" ; ");
            }
            LogUtils.d("CURSOR", builder.toString());
        }
    }
}
