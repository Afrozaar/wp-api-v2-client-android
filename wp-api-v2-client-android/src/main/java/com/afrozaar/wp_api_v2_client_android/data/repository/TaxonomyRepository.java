package com.afrozaar.wp_api_v2_client_android.data.repository;

import android.content.ContentValues;
import android.database.Cursor;
import android.provider.BaseColumns;

import com.afrozaar.wp_api_v2_client_android.data.WordPressContract;
import com.afrozaar.wp_api_v2_client_android.model.Taxonomy;

/**
 * @author Jan-Louis Crafford
 *         Created on 2016/08/12.
 */
public class TaxonomyRepository extends BaseWpRepository implements WordPressContract.TaxonomyColumns {

    public static final String TABLE_NAME = "taxonomies";

    public static final String SCHEMA = "CREATE TABLE " + TABLE_NAME + " ("
            + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + BLOG_ID + " INTEGER " + WordPressContract.References.BLOG_ID + ","
            + WP_TAXONOMY_ID + " INTEGER,"
            + WP_PARENT_ID + " INTEGER DEFAULT -1,"
            + TYPE + " TEXT NOT NULL,"
            + NAME + " TEXT NOT NULL,"
            + SLUG + " TEXT,"
            + DESCRIPTION + " TEXT,"
            + COUNT + " INTEGER,"
            + LINK + " TEXT)";

    public static final int IDX_BLOG_ID = 1;
    public static final int IDX_WP_TAXONOMY_ID = 2;
    public static final int IDX_WP_PARENT_ID = 3;
    public static final int IDX_TYPE = 4;
    public static final int IDX_NAME = 5;
    public static final int IDX_SLUG = 6;
    public static final int IDX_DESCRIPTION = 7;
    public static final int IDX_COUNT = 8;
    public static final int IDX_LINK = 9;

    public static ContentValues getContainsMap(Taxonomy taxonomy, long blogId) {
        ContentValues values = new ContentValues();

        values.put(BLOG_ID, blogId);
        values.put(WP_PARENT_ID, taxonomy.getParent());
        values.put(WP_TAXONOMY_ID, taxonomy.getId());

        return values;
    }

    public static ContentValues mapToContentValues(Taxonomy taxonomy, long blogId) {
        ContentValues values = new ContentValues();

        values.put(BLOG_ID, blogId);

        addValue(values, WP_TAXONOMY_ID, taxonomy.getId());
        addValue(values, WP_PARENT_ID, taxonomy.getParent());
        addValue(values, TYPE, taxonomy.getTaxonomy());
        addValue(values, NAME, taxonomy.getName());
        addValue(values, SLUG, taxonomy.getSlug());
        addValue(values, DESCRIPTION, taxonomy.getDescription());
        addValue(values, COUNT, taxonomy.getCount());
        addValue(values, LINK, taxonomy.getLink());

        return values;
    }

    public static Taxonomy mapFromCursor(Cursor cursor) {
        Taxonomy taxonomy = new Taxonomy();

        taxonomy.rowId = getRowId(cursor);

        taxonomy.withId(cursor.getLong(IDX_WP_TAXONOMY_ID))
                .withParent(cursor.getLong(IDX_WP_PARENT_ID))
                .withTaxonomy(cursor.getString(IDX_TYPE))
                .withName(cursor.getString(IDX_NAME))
                .withSlug(cursor.getString(IDX_SLUG))
                .withDescription(cursor.getString(IDX_DESCRIPTION))
                .withCount(cursor.getInt(IDX_COUNT))
                .withLink(cursor.getString(IDX_LINK));

        return taxonomy;
    }
}
