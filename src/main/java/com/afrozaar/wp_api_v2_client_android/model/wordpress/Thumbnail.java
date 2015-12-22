package com.afrozaar.wp_api_v2_client_android.model.wordpress;

/**
 * Created by jay on 12/10/15.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Thumbnail {

    @SerializedName("file")
    @Expose
    private String file;
    @SerializedName("width")
    @Expose
    private long width;
    @SerializedName("height")
    @Expose
    private long height;
    @SerializedName("mime-type")
    @Expose
    private String mimeType;
    @SerializedName("source_url")
    @Expose
    private String sourceUrl;

    /**
     *
     * @return
     * The file
     */
    public String getFile() {
        return file;
    }

    /**
     *
     * @param file
     * The file
     */
    public void setFile(String file) {
        this.file = file;
    }

    public Thumbnail withFile(String file) {
        this.file = file;
        return this;
    }

    /**
     *
     * @return
     * The width
     */
    public long getWidth() {
        return width;
    }

    /**
     *
     * @param width
     * The width
     */
    public void setWidth(long width) {
        this.width = width;
    }

    public Thumbnail withWidth(long width) {
        this.width = width;
        return this;
    }

    /**
     *
     * @return
     * The height
     */
    public long getHeight() {
        return height;
    }

    /**
     *
     * @param height
     * The height
     */
    public void setHeight(long height) {
        this.height = height;
    }

    public Thumbnail withHeight(long height) {
        this.height = height;
        return this;
    }

    /**
     *
     * @return
     * The mimeType
     */
    public String getMimeType() {
        return mimeType;
    }

    /**
     *
     * @param mimeType
     * The mime-type
     */
    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public Thumbnail withMimeType(String mimeType) {
        this.mimeType = mimeType;
        return this;
    }

    /**
     *
     * @return
     * The sourceUrl
     */
    public String getSourceUrl() {
        return sourceUrl;
    }

    /**
     *
     * @param sourceUrl
     * The source_url
     */
    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public Thumbnail withSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
        return this;
    }

}
