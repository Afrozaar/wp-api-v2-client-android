package com.afrozaar.wp_api_v2_client_android.model.wordpress;

/**
 * Created by jay on 12/10/15.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MediaDetails {

    @SerializedName("width")
    @Expose
    private long width;
    @SerializedName("height")
    @Expose
    private long height;
    @SerializedName("file")
    @Expose
    private String file;
    @SerializedName("sizes")
    @Expose
    private Sizes sizes;
    @SerializedName("image_meta")
    @Expose
    private ImageMeta imageMeta;

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

    public MediaDetails withWidth(long width) {
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

    public MediaDetails withHeight(long height) {
        this.height = height;
        return this;
    }

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

    public MediaDetails withFile(String file) {
        this.file = file;
        return this;
    }

    /**
     *
     * @return
     * The sizes
     */
    public Sizes getSizes() {
        return sizes;
    }

    /**
     *
     * @param sizes
     * The sizes
     */
    public void setSizes(Sizes sizes) {
        this.sizes = sizes;
    }

    public MediaDetails withSizes(Sizes sizes) {
        this.sizes = sizes;
        return this;
    }

    /**
     *
     * @return
     * The imageMeta
     */
    public ImageMeta getImageMeta() {
        return imageMeta;
    }

    /**
     *
     * @param imageMeta
     * The image_meta
     */
    public void setImageMeta(ImageMeta imageMeta) {
        this.imageMeta = imageMeta;
    }

    public MediaDetails withImageMeta(ImageMeta imageMeta) {
        this.imageMeta = imageMeta;
        return this;
    }

}
