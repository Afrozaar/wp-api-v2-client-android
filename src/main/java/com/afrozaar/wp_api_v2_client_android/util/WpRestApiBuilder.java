package com.afrozaar.wp_api_v2_client_android.util;

import android.content.Context;

import com.afrozaar.wp_api_v2_client_android.R;
import com.afrozaar.wp_api_v2_client_android.model.wordpress.Post;

import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

/**
 * @author Jan-Louis Crafford
 *         Created on 2015/12/04.
 */
public class WpRestApiBuilder {

    private static final String WP_JSON_API_PATH = "/wp-json/wp/v2";

    private static final String WP_ENDPOINT_POSTS = "/posts";

    private final Context mContext;

    private UriComponentsBuilder mUriBuilder;

    public WpRestApiBuilder(Context context) {
        mContext = context;
        init();
    }

    public URI getUri() {
        return mUriBuilder.build().toUri();
    }

    /**
     * Sets the base url for all WP REST calls.
     * Reads the url from the string resource, {@link com.afrozaar.mojodroid.R.string.wp_base_url}
     *
     * @return this Builder object to allow for chaining of calls to set methods
     */
    private void init() {
        String baseUrl = mContext.getString(R.string.wp_base_url);
        mUriBuilder = UriComponentsBuilder.fromHttpUrl(baseUrl);
        mUriBuilder.port(8080);
        mUriBuilder.path(WP_JSON_API_PATH);
    }

    public WpRestApiBuilder forPosts() {
        mUriBuilder.path(WP_ENDPOINT_POSTS);
        return this;
    }

    public WpRestApiBuilder forPost(int id) {
        mUriBuilder.path(WP_ENDPOINT_POSTS);
        mUriBuilder.path("/" + id + "");
        return this;
    }

    public WpRestApiBuilder forUpdatePost(int id, Post post){

        return this;
    }

    public WpRestApiBuilder withParams(String name, Object... values) {
        mUriBuilder.queryParam(name, values);
        return this;
    }

}
