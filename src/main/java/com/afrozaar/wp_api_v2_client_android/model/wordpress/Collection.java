package com.afrozaar.wp_api_v2_client_android.model.wordpress;

/**
 * Created by jay on 12/9/15.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Collection {

    @SerializedName("href")
    @Expose
    private String href;

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

    public Collection withHref(String href) {
        this.href = href;
        return this;
    }

}
