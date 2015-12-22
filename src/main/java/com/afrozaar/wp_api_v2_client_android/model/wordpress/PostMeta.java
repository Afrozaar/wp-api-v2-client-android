package com.afrozaar.wp_api_v2_client_android.model.wordpress;

/**
 * Created by jay on 12/10/15.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostMeta {

    @SerializedName("id")
    @Expose
    private long id;
    @SerializedName("key")
    @Expose
    private String key;
    @SerializedName("value")
    @Expose
    private String value;
    @SerializedName("_links")
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
     * @param Links The _links
     */
    public void setLinks(Links Links) {
        this.Links = Links;
    }

    public PostMeta withLinks(Links Links) {
        this.Links = Links;
        return this;
    }

}
