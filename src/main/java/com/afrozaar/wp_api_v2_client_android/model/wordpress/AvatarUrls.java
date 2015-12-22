package com.afrozaar.wp_api_v2_client_android.model.wordpress;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by jay on 12/18/15.
 */
public class AvatarUrls {

    @SerializedName("24")
    @Expose
    private String _24;
    @SerializedName("48")
    @Expose
    private String _48;
    @SerializedName("96")
    @Expose
    private String _96;

    /**
     *
     * @return
     * The _24
     */
    public String get24() {
        return _24;
    }

    /**
     *
     * @param _24
     * The 24
     */
    public void set24(String _24) {
        this._24 = _24;
    }

    public AvatarUrls with24(String _24) {
        this._24 = _24;
        return this;
    }

    /**
     *
     * @return
     * The _48
     */
    public String get48() {
        return _48;
    }

    /**
     *
     * @param _48
     * The 48
     */
    public void set48(String _48) {
        this._48 = _48;
    }

    public AvatarUrls with48(String _48) {
        this._48 = _48;
        return this;
    }

    /**
     *
     * @return
     * The _96
     */
    public String get96() {
        return _96;
    }

    /**
     *
     * @param _96
     * The 96
     */
    public void set96(String _96) {
        this._96 = _96;
    }

    public AvatarUrls with96(String _96) {
        this._96 = _96;
        return this;
    }

}
