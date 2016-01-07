package com.afrozaar.wp_api_v2_client_android.model.wp_v2;

/**
 * Created by jay on 12/9/15.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Excerpt {

    @SerializedName("raw")
    @Expose
    private String raw;
    @SerializedName("rendered")
    @Expose
    private String rendered;

    /**
     *
     * @return
     * The raw
     */
    public String getRaw() {
        return raw;
    }

    /**
     *
     * @param raw
     * The raw
     */
    public void setRaw(String raw) {
        this.raw = raw;
    }

    public Excerpt withRaw(String raw) {
        this.raw = raw;
        return this;
    }

    /**
     *
     * @return
     * The rendered
     */
    public String getRendered() {
        return rendered;
    }

    /**
     *
     * @param rendered
     * The rendered
     */
    public void setRendered(String rendered) {
        this.rendered = rendered;
    }

    public Excerpt withRendered(String rendered) {
        this.rendered = rendered;
        return this;
    }

}
