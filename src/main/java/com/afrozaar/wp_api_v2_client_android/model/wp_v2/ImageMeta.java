package com.afrozaar.wp_api_v2_client_android.model.wp_v2;

/**
 * Created by jay on 12/10/15.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ImageMeta {

    @SerializedName("aperture")
    @Expose
    private long aperture;
    @SerializedName("credit")
    @Expose
    private String credit;
    @SerializedName("camera")
    @Expose
    private String camera;
    @SerializedName("caption")
    @Expose
    private String caption;
    @SerializedName("created_timestamp")
    @Expose
    private long createdTimestamp;
    @SerializedName("copyright")
    @Expose
    private String copyright;
    @SerializedName("focal_length")
    @Expose
    private long focalLength;
    @SerializedName("iso")
    @Expose
    private long iso;
    @SerializedName("shutter_speed")
    @Expose
    private long shutterSpeed;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("orientation")
    @Expose
    private long orientation;
    @SerializedName("keywords")
    @Expose
    private List<Object> keywords = new ArrayList<Object>();

    /**
     * @return The aperture
     */
    public long getAperture() {
        return aperture;
    }

    /**
     * @param aperture The aperture
     */
    public void setAperture(long aperture) {
        this.aperture = aperture;
    }

    public ImageMeta withAperture(long aperture) {
        this.aperture = aperture;
        return this;
    }

    /**
     * @return The credit
     */
    public String getCredit() {
        return credit;
    }

    /**
     * @param credit The credit
     */
    public void setCredit(String credit) {
        this.credit = credit;
    }

    public ImageMeta withCredit(String credit) {
        this.credit = credit;
        return this;
    }

    /**
     * @return The camera
     */
    public String getCamera() {
        return camera;
    }

    /**
     * @param camera The camera
     */
    public void setCamera(String camera) {
        this.camera = camera;
    }

    public ImageMeta withCamera(String camera) {
        this.camera = camera;
        return this;
    }

    /**
     * @return The caption
     */
    public String getCaption() {
        return caption;
    }

    /**
     * @param caption The caption
     */
    public void setCaption(String caption) {
        this.caption = caption;
    }

    public ImageMeta withCaption(String caption) {
        this.caption = caption;
        return this;
    }

    /**
     * @return The createdTimestamp
     */
    public long getCreatedTimestamp() {
        return createdTimestamp;
    }

    /**
     * @param createdTimestamp The created_timestamp
     */
    public void setCreatedTimestamp(long createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }

    public ImageMeta withCreatedTimestamp(long createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
        return this;
    }

    /**
     * @return The copyright
     */
    public String getCopyright() {
        return copyright;
    }

    /**
     * @param copyright The copyright
     */
    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public ImageMeta withCopyright(String copyright) {
        this.copyright = copyright;
        return this;
    }

    /**
     * @return The focalLength
     */
    public long getFocalLength() {
        return focalLength;
    }

    /**
     * @param focalLength The focal_length
     */
    public void setFocalLength(long focalLength) {
        this.focalLength = focalLength;
    }

    public ImageMeta withFocalLength(long focalLength) {
        this.focalLength = focalLength;
        return this;
    }

    /**
     * @return The iso
     */
    public long getIso() {
        return iso;
    }

    /**
     * @param iso The iso
     */
    public void setIso(long iso) {
        this.iso = iso;
    }

    public ImageMeta withIso(long iso) {
        this.iso = iso;
        return this;
    }

    /**
     * @return The shutterSpeed
     */
    public long getShutterSpeed() {
        return shutterSpeed;
    }

    /**
     * @param shutterSpeed The shutter_speed
     */
    public void setShutterSpeed(long shutterSpeed) {
        this.shutterSpeed = shutterSpeed;
    }

    public ImageMeta withShutterSpeed(long shutterSpeed) {
        this.shutterSpeed = shutterSpeed;
        return this;
    }

    /**
     * @return The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    public ImageMeta withTitle(String title) {
        this.title = title;
        return this;
    }

    /**
     * @return The orientation
     */
    public long getOrientation() {
        return orientation;
    }

    /**
     * @param orientation The orientation
     */
    public void setOrientation(long orientation) {
        this.orientation = orientation;
    }

    public ImageMeta withOrientation(long orientation) {
        this.orientation = orientation;
        return this;
    }

    /**
     * @return The keywords
     */
    public List<Object> getKeywords() {
        return keywords;
    }

    /**
     * @param keywords The keywords
     */
    public void setKeywords(List<Object> keywords) {
        this.keywords = keywords;
    }

    public ImageMeta withKeywords(List<Object> keywords) {
        this.keywords = keywords;
        return this;
    }
}
