package com.afrozaar.wp_api_v2_client_android.model;

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

    /**
     * The id for the parent of the object.
     */
    @SerializedName(JSON_FIELDS_PARENT)
    private long mParent;

    public void setParent(long parent) {
        mParent = parent;
    }

    public long getParent() {
        return mParent;
    }

    public Page withParent(long parent) {
        setParent(parent);
        return this;
    }

    /**
     * The order of the object in relation to other object of its type.
     */
    @SerializedName(JSON_FIELDS_MENU_ORDER)
    private int mMenuOrder;

    public void setMenuOrder(int menuOrder) {
        mMenuOrder = menuOrder;
    }

    public int getMenuOrder() {
        return mMenuOrder;
    }

    public Page withMenuOrder(int menuOrder) {
        setMenuOrder(menuOrder);
        return this;
    }

    /**
     * The theme file to use to display the object.
     */
    @SerializedName(JSON_FIELDS_TEMPLATE)
    private String mTemplate;

    public void setTemplate(String template) {
        mTemplate = template;
    }

    public String getTemplate() {
        return mTemplate;
    }

    public Page withTemplate(String template) {
        setTemplate(template);
        return this;
    }

    @Override
    public Page withId(long id) {
        setId(id);
        return this;
    }

    @Override
    public Page withDate(String date) {
        setDate(date);
        return this;
    }

    @Override
    public Page withDateGMT(String dateGMT) {
        setDateGMT(dateGMT);
        return this;
    }

    @Override
    public Page withGuid(WPGeneric guid) {
        setGuid(guid);
        return this;
    }

    @Override
    public Page withModified(String modified) {
        setModified(modified);
        return this;
    }

    @Override
    public Page withModifiedGMT(String modifiedGMT) {
        setModifiedGMT(modifiedGMT);
        return this;
    }

    @Override
    public Page withSlug(String slug) {
        setSlug(slug);
        return this;
    }

    @Override
    public Page withType(String type) {
        setType(type);
        return this;
    }

    @Override
    public Page withLink(String link) {
        setLink(link);
        return this;
    }

    @Override
    public Page withTitle(String title) {
        WPGeneric generic = new WPGeneric();
        generic.setRendered(title);
        setTitle(generic);
        return this;
    }

    @Override
    public Page withAuthor(int author) {
        setAuthor(author);
        return this;
    }

    @Override
    public Page withCommentStatus(WPStatus commentStatus) {
        setCommentStatus(commentStatus);
        return this;
    }

    @Override
    public Page withPingStatus(WPStatus pingStatus) {
        setPingStatus(pingStatus);
        return this;
    }

    @Override
    public Page withLinks(ArrayList<Link> links) {
        setLinks(links);
        return this;
    }

    @Override
    public Page withLink(Link link) {
        addLink(link);
        return this;
    }

    public Page() {
    }

    public Page(Parcel in) {
        super(in);

        mContent = in.readParcelable(WPGeneric.class.getClassLoader());
        mExcerpt = in.readParcelable(WPGeneric.class.getClassLoader());
        mFeaturedImage = in.readInt();
        mParent = in.readLong();
        mMenuOrder = in.readInt();
        mTemplate = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);

        dest.writeParcelable(mContent, flags);
        dest.writeParcelable(mExcerpt, flags);
        dest.writeInt(mFeaturedImage);
        dest.writeLong(mParent);
        dest.writeInt(mMenuOrder);
        dest.writeString(mTemplate);
    }

    public static final Parcelable.Creator<Page> CREATOR = new Creator<Page>() {
        @Override
        public Page createFromParcel(Parcel source) {
            return new Page(source);
        }

        @Override
        public Page[] newArray(int size) {
            return new Page[size];
        }
    };
}
