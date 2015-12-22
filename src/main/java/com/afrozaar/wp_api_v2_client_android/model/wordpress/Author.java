package com.afrozaar.wp_api_v2_client_android.model.wordpress;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by jay on 12/9/15.
 */


public class Author {

    @SerializedName("embeddable")
    @Expose
    private boolean embeddable;
    @SerializedName("href")
    @Expose
    private String href;

    /**
     * @return The embeddable
     */
    public boolean isEmbeddable() {
        return embeddable;
    }

    /**
     * @param embeddable The embeddable
     */
    public void setEmbeddable(boolean embeddable) {
        this.embeddable = embeddable;
    }

    public Author withEmbeddable(boolean embeddable) {
        this.embeddable = embeddable;
        return this;
    }

    /**
     * @return The href
     */
    public String getHref() {
        return href;
    }

    /**
     * @param href The href
     */
    public void setHref(String href) {
        this.href = href;
    }

    public Author withHref(String href) {
        this.href = href;
        return this;
    }

}

