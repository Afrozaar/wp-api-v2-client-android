package com.afrozaar.wp_api_v2_client_android.api;


import com.afrozaar.wp_api_v2_client_android.model.wp_v2.Taxonomy;

import java.util.List;

public interface Taxonomies {

    String TAG = "tag";
    String TAGS = "tags";
    String CATEGORY = "category";
    String CATEGORIES = "categories";

    List<Taxonomy> getTaxonomies();

    Taxonomy getTaxonomy(String slug);
}
