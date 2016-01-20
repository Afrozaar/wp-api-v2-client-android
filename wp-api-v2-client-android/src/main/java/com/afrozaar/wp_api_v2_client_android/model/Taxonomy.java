package com.afrozaar.wp_api_v2_client_android.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * @author Jan-Louis Crafford
 *         Created on 2016/01/14.
 */
public class Taxonomy implements Parcelable {

    @SerializedName("id")
    private long mId;

    public void setId(long id) {
        mId = id;
    }

    public long getId() {
        return mId;
    }

    public Taxonomy withId(long id) {
        setId(id);
        return this;
    }

    @SerializedName("count")
    private int mCount;

    public void setCount(int count) {
        mCount = count;
    }

    public int getCount() {
        return mCount;
    }

    public Taxonomy withCount(int count) {
        setCount(count);
        return this;
    }

    @SerializedName("description")
    private String mDescription;

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getDescription() {
        return mDescription;
    }

    public Taxonomy withDescription(String description) {
        setDescription(description);
        return this;
    }

    @SerializedName("link")
    private String mLink;

    public void setLink(String link) {
        mLink = link;
    }

    public String getLink() {
        return mLink;
    }

    public Taxonomy withLink(String link) {
        setLink(link);
        return this;
    }

    @SerializedName("name")
    private String mName;

    public void setName(String name) {
        mName = name;
    }

    public String getName() {
        return mName;
    }

    public Taxonomy withName(String name) {
        setName(name);
        return this;
    }

    @SerializedName("slug")
    private String mSlug;

    public void setSlug(String slug) {
        mSlug = slug;
    }

    public String getSlug() {
        return mSlug;
    }

    public Taxonomy withSlug(String slug) {
        setSlug(slug);
        return this;
    }

    @SerializedName("taxonomy")
    private String mTaxonomy;

    public void setTaxonomy(String taxonomy) {
        mTaxonomy = taxonomy;
    }

    public String getTaxonomy() {
        return mTaxonomy;
    }

    public Taxonomy withTaxonomy(String taxonomy) {
        setTaxonomy(taxonomy);
        return this;
    }

    @SerializedName("parent")
    private long mParent;

    public void setParent(long parent) {
        mParent = parent;
    }

    public long getParent() {
        return mParent;
    }

    public Taxonomy withParent(long parent) {
        setParent(parent);
        return this;
    }

    /**
     * Links for this post; author, attachments, history, etc.
     */
    @JsonAdapter(LinksDeserializer.class)
    @SerializedName("_links")
    private ArrayList<Link> mLinks;

    public void setLinks(ArrayList<Link> links) {
        mLinks = links;
    }

    public void addLink(Link link) {
        if (mLinks == null) {
            mLinks = new ArrayList<>();
        }
        mLinks.add(link);
    }

    public ArrayList<Link> getLinks() {
        return mLinks;
    }

    public Taxonomy withLinks(ArrayList<Link> links) {
        setLinks(links);
        return this;
    }

    public Taxonomy withLink(Link link) {
        addLink(link);
        return this;
    }

    public Taxonomy() {
    }

    public Taxonomy(Parcel in) {
        mId = in.readLong();
        mCount = in.readInt();
        mDescription = in.readString();
        mLink = in.readString();
        mName = in.readString();
        mSlug = in.readString();
        mTaxonomy = in.readString();
        mParent = in.readLong();
        in.readTypedList(mLinks, Link.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(mId);
        dest.writeInt(mCount);
        dest.writeString(mDescription);
        dest.writeString(mLink);
        dest.writeString(mName);
        dest.writeString(mSlug);
        dest.writeString(mTaxonomy);
        dest.writeLong(mParent);
        dest.writeTypedList(mLinks);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<Taxonomy> CREATOR = new Creator<Taxonomy>() {
        @Override
        public Taxonomy createFromParcel(Parcel source) {
            return new Taxonomy(source);
        }

        @Override
        public Taxonomy[] newArray(int size) {
            return new Taxonomy[size];
        }
    };

    @Override
    public String toString() {
        return "Taxonomy{" +
                "mId=" + mId +
                ", mCount=" + mCount +
                ", mDescription='" + mDescription + '\'' +
                ", mLink='" + mLink + '\'' +
                ", mName='" + mName + '\'' +
                ", mSlug='" + mSlug + '\'' +
                ", mTaxonomy='" + mTaxonomy + '\'' +
                ", mParent=" + mParent +
                ", mLinks=" + mLinks +
                '}';
    }
}
