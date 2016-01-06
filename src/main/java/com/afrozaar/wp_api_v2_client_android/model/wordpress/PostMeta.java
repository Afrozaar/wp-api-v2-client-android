package com.afrozaar.wp_api_v2_client_android.model.wordpress;

/**
 * Created by jay on 12/10/15.
 */

import com.google.common.collect.ImmutableMap;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class PostMeta {

    public static final String JSON_FIELD_ID = "id";
    public static final String JSON_FIELD_KEY = "key";
    public static final String JSON_FIELD_VALUE = "value";
    public static final String JSON_FIELD_LINKS = "_links";

    @SerializedName(JSON_FIELD_ID)
    @Expose
    private long id;
    @SerializedName(JSON_FIELD_KEY)
    @Expose
    private String key;
    @SerializedName(JSON_FIELD_VALUE)
    @Expose
    private String value;
    @SerializedName(JSON_FIELD_LINKS)
    @Expose
    private Links Links;

    /**
     * @return The id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(long id) {
        this.id = id;
    }

    public PostMeta withId(long id) {
        this.id = id;
        return this;
    }

    /**
     * @return The key
     */
    public String getKey() {
        return key;
    }

    /**
     * @param key The key
     */
    public void setKey(String key) {
        this.key = key;
    }

    public PostMeta withKey(String key) {
        this.key = key;
        return this;
    }

    /**
     * @return The value
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value The value
     */
    public void setValue(String value) {
        this.value = value;
    }

    public PostMeta withValue(String value) {
        this.value = value;
        return this;
    }

    /**
     * @return The Links
     */
    public Links getLinks() {
        return Links;
    }

    /**
     * @param Links The links
     */
    public void setLinks(Links Links) {
        this.Links = Links;
    }

    public PostMeta withLinks(Links Links) {
        this.Links = Links;
        return this;
    }

    public static Map<String, Object> fieldsFrom(PostMeta postMeta) {
        ImmutableMap.Builder<String, Object> builder = new ImmutableMap.Builder<>();

        populateEntry(postMeta.getId(), builder, JSON_FIELD_ID);
        populateEntry(postMeta.getKey(), builder, JSON_FIELD_KEY);
        populateEntry(postMeta.getValue(), builder, JSON_FIELD_VALUE);
        populateEntry(postMeta.getLinks(), builder, JSON_FIELD_LINKS);

        return builder.build();
    }

    private static void populateEntry(Object value, ImmutableMap.Builder<String, Object> builder, String key) {
        if (value != null) { //Optional.fromNullable(value).isPresent()
            builder.put(key, value);
        }
    }
}
