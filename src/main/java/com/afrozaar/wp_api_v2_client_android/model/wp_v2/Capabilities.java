package com.afrozaar.wp_api_v2_client_android.model.wp_v2;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by jay on 12/18/15.
 */
public class Capabilities {

    @SerializedName("read")
    @Expose
    private boolean read;
    @SerializedName("level_0")
    @Expose
    private boolean level0;
    @SerializedName("subscriber")
    @Expose
    private boolean subscriber;

    /**
     *
     * @return
     * The read
     */
    public boolean isRead() {
        return read;
    }

    /**
     *
     * @param read
     * The read
     */
    public void setRead(boolean read) {
        this.read = read;
    }

    public Capabilities withRead(boolean read) {
        this.read = read;
        return this;
    }

    /**
     *
     * @return
     * The level0
     */
    public boolean isLevel0() {
        return level0;
    }

    /**
     *
     * @param level0
     * The level_0
     */
    public void setLevel0(boolean level0) {
        this.level0 = level0;
    }

    public Capabilities withLevel0(boolean level0) {
        this.level0 = level0;
        return this;
    }

    /**
     *
     * @return
     * The subscriber
     */
    public boolean isSubscriber() {
        return subscriber;
    }

    /**
     *
     * @param subscriber
     * The subscriber
     */
    public void setSubscriber(boolean subscriber) {
        this.subscriber = subscriber;
    }

    public Capabilities withSubscriber(boolean subscriber) {
        this.subscriber = subscriber;
        return this;
    }

}
