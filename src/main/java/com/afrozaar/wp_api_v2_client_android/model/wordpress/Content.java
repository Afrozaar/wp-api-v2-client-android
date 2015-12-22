package com.afrozaar.wp_api_v2_client_android.model.wordpress;

/**
 * Created by jay on 12/9/15.
 */

import android.content.Context;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Content {

    public Content(String content){
        this.rendered = content;
    }

    @SerializedName("raw")
    @Expose
    private String raw;
    @SerializedName("rendered")
    @Expose
    private String rendered;

    /**
     * @return The raw
     */
    public String getRaw() {
        return raw;
    }

    /**
     * @param raw The raw
     */
    public void setRaw(String raw) {
        this.raw = raw;
    }

    public Content withRaw(String raw) {
        this.raw = raw;
        return this;
    }

    /**
     * @return The rendered
     */
    public String getRendered() {
        return rendered;
    }

    /**
     * @param rendered The rendered
     */
    public void setRendered(String rendered) {
        this.rendered = rendered;
    }

    public Content withRendered(String rendered) {
        this.rendered = rendered;
        return this;
    }

}
