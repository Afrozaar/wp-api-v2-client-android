package com.afrozaar.wp_api_v2_client_android.model.wordpress;

/**
 * Created by jay on 12/9/15.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Guid {

    @SerializedName("rendered")
    @Expose
    private String rendered;
    @SerializedName("raw")
    @Expose
    private String raw;

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

    public Guid withRendered(String rendered) {
        this.rendered = rendered;
        return this;
    }

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

    public Guid withRaw(String raw) {
        this.raw = raw;
        return this;
    }

}
