package com.afrozaar.wp_api_v2_client_android.model.wp_v1;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * @author Jan-Louis Crafford
 *         Created on 2016/01/14.
 */
public class Page extends WPObject<Page> {

    public static final String JSON_FIELD_CONTENT = "content";
    public static final String JSON_FIELD_EXCERPT = "excerpt";
    public static final String JSON_FIELD_FEATURED_IMAGE = "featured_image";
    public static final String JSON_FIELDS_PARENT = "parent";
    public static final String JSON_FIELDS_MENU_ORDER = "menu_order";
    public static final String JSON_FIELDS_TEMPLATE = "template";

    /**
     * The content for the object.
     */
    @SerializedName(JSON_FIELD_CONTENT)
    private WPGeneric mContent;

    public void setContent(WPGeneric content) {
        mContent = content;
    }

    public WPGeneric getContent() {
        return mContent;
    }

    public Page withContent(String content) {
        WPGeneric generic = new WPGeneric();
        generic.setRaw(content);
        setContent(generic);
        return this;
    }

    /**
     * The excerpt for the object.
     */
    @SerializedName(JSON_FIELD_EXCERPT)
    private WPGeneric mExcerpt;

    public void setExcerpt(WPGeneric excerpt) {
        mExcerpt = excerpt;
    }

    public WPGeneric getExcerpt() {
        return mExcerpt;
    }

    public Page withExcerpt(WPGeneric excerpt) {
        setExcerpt(excerpt);
        return this;
    }

    /**
     * ID of the featured image for the object.
     */
    @SerializedName(JSON_FIELD_FEATURED_IMAGE)
    private int mFeaturedImage;

    public void setFeaturedImage(int featuredImage) {
        mFeaturedImage = featuredImage;
    }

    public int getFeaturedImage() {
        return mFeaturedImage;
    }

    public Page withFeaturedImage(int featuredImage) {
        setFeaturedImage(featuredImage);
        return this;
    }

    @SerializedName(JSON_FIELDS_PARENT)
    private long mParent;

    public void setParent(long parent) {
        mParent = parent;
    }

    public long getParent() {
        return mParent;
    }

    @Override
    public Page withId(long id) {
        return null;
    }

    @Override
    public Page withDate(String date) {
        return null;
    }

    @Override
    public Page withDateGMT(String dateGMT) {
        return null;
    }

    @Override
    public Page withGuid(WPGeneric guid) {
        return null;
    }

    @Override
    public Page withModified(String modified) {
        return null;
    }

    @Override
    public Page withModifiedGMT(String modifiedGMT) {
        return null;
    }

    @Override
    public Page withSlug(String slug) {
        return null;
    }

    @Override
    public Page withType(String type) {
        return null;
    }

    @Override
    public Page withLink(String link) {
        return null;
    }

    @Override
    public Page withTitle(String title) {
        return null;
    }

    @Override
    public Page withAuthor(int author) {
        return null;
    }

    @Override
    public Page withCommentStatus(WPStatus commentStatus) {
        return null;
    }

    @Override
    public Page withPingStatus(WPStatus pingStatus) {
        return null;
    }

    @Override
    public Page withLinks(ArrayList<Link> links) {
        return null;
    }

    @Override
    public Page withLink(Link link) {
        return null;
    }


    public Page() {
    }

    public Page(Parcel in) {

    }



    public static final Parcelable.Creator<Post> CREATOR = new Creator<Post>() {
        @Override
        public Post createFromParcel(Parcel source) {
            return new Post(source);
        }

        @Override
        public Post[] newArray(int size) {
            return new Post[size];
        }
    };
}
