package com.afrozaar.wp_api_v2_client_android.api;

import com.afrozaar.wp_api_v2_client_android.model.wordpress.Taxonomy;

import java.util.List;

/**
 * Created by jay on 12/10/15.
 */
public interface Taxonomies {

    String TAG = "tag";
    String CATEGORY = "category";

    List<Taxonomy> getTaxonomies();

    Taxonomy getTaxonomy(String slug);
}
