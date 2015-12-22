package com.afrozaar.wp_api_v2_client_android;

import com.afrozaar.wp_api_v2_client_android.api.Medias;
import com.afrozaar.wp_api_v2_client_android.api.PostMetas;
import com.afrozaar.wp_api_v2_client_android.api.Posts;
import com.afrozaar.wp_api_v2_client_android.api.Taxonomies;
import com.afrozaar.wp_api_v2_client_android.api.Terms;
import com.afrozaar.wp_api_v2_client_android.api.Users;
import com.afrozaar.wp_api_v2_client_android.response.PagedResponse;
import com.google.common.base.Function;

import java.net.URI;

/**
 * Created by jay on 12/10/15.
 */
public interface Wordpress extends Posts, PostMetas, Taxonomies, Terms, Medias, Users {
    String CONTEXT = "/wp-json/wp/v2";

    <T> PagedResponse<T> getPagedResponse(String context, Class<T> typeRef, String... expandParams);
    <T> PagedResponse<T> getPagedResponse(URI uri, Class<T> typeRef);
    <T> PagedResponse<T> traverse(PagedResponse<T> response, Function<PagedResponse<?>, String> direction);


}
