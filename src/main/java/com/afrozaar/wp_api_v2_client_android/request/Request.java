package com.afrozaar.wp_api_v2_client_android.request;

import com.afrozaar.wp_api_v2_client_android.Client;
import com.google.common.collect.ImmutableMap;

import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

/**
 * Created by jay on 12/10/15.
 */
public abstract class Request {
    public static final String POSTS = "/posts";
    public static final String POST = "/posts/{id}";
    public static final String POST_TERMS = "/posts/{postId}/{taxonomy}";
    public static final String POST_TERM = "/posts/{postId}/{taxonomy}/{termId}";

    public static final String METAS = "/posts/{postId}/meta";
    public static final String META = "/posts/{postId}/meta/{metaId}";
    public static final String TAXONOMIES = "/taxonomies";
    public static final String TAXONOMY = "/taxonomies/{slug}";
    public static final String TERMS = "/terms/{taxonomySlug}";
    public static final String TERM = "/terms/{taxonomySlug}/{termId}";
    public static final String MEDIAS = "/media";
    public static final String MEDIA = "/media/{mediaId}";
    public static final String PAGES = "/pages";
    public static final String PAGE = "/pages/{pageId}";
    public static final String USERS = "/users";
    public static final String USER = "/users/{userId}";
    public static final String TAGS = "/tags";
    public static final String TAG = "/tags/{tagId}";
    public static final String CATEGORIES = "/categories";
    public static final String CATEGORY = "/categories/{categoryId}";

    final String uri;
    final Map<String, List<String>> params;

    public Request(String uri, Map<String, List<String>> params) {
        this.params = params;
        this.uri = uri;
    }

    public static Request of(String uri) {
        return of(uri, ImmutableMap.<String, List<String>>of());
    }

    public static Request of(String uri, Map<String, List<String>> params) {
        return new Request(uri, params) {
        };
    }

    protected UriComponentsBuilder init(String baseUrl, String context) {
        return UriComponentsBuilder.fromHttpUrl(baseUrl + context + this.uri);
    }

    public UriComponentsBuilder usingClient(Client client) {
        return forHost(client.baseUrl, Client.CONTEXT);
    }

    public UriComponentsBuilder forHost(String baseUrl, String context) {
        final UriComponentsBuilder builder = init(baseUrl, context);
        for (Map.Entry<String, List<String>> entry : params.entrySet()) {
            builder.queryParam(entry.getKey(), entry.getValue().toArray());
        }
        return builder;
    }

    public static URI fromLink(String apply) {
        return UriComponentsBuilder.fromHttpUrl(apply).build().toUri();
    }
}
