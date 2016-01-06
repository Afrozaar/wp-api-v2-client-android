package com.afrozaar.wp_api_v2_client_android.request;

import com.google.common.collect.ImmutableMap;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by jay on 12/10/15.
 */
public class SearchRequest<T> extends Request {

    private final Class<T> clazz;

    public SearchRequest(String uri, Map<String, List<String>> params, Class<T> clazz) {
        super(uri, params);
        this.clazz = clazz;
    }

    public Class<T> getClazz() {
        return clazz;
    }

    public static class Builder<BT> {
        ImmutableMap.Builder<String, List<String>> paramBuilder = new ImmutableMap.Builder<>();
        String uri = Request.POSTS;
        final Class<BT> clazz;

        private Builder(Class<BT> clazz) {
            this.clazz = clazz;
        }

        public static <BT> Builder<BT> aSearchRequest(Class<BT> clazz) {
            return new Builder<>(clazz);
        }

        public Builder<BT> withParam(String key, String... values) {
            paramBuilder.put(key, Arrays.asList(values));
            return this;
        }

        public Builder<BT> withParams(Map<String, List<String>> params) {
            this.paramBuilder.putAll(params);
            return this;
        }

        public Builder<BT> withUri(String uri) {
            this.uri = uri;
            return this;
        }

        public Builder<BT> but() {
            return Builder.aSearchRequest(clazz).withParams(paramBuilder.build()).withUri(uri);
        }

        public SearchRequest<BT> build() {
            return new SearchRequest<>(uri, paramBuilder.build(), clazz);
        }
    }
}