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
public class Taxonomy extends BaseModel {

    public static String TYPE_CATEGORY = "category";
    public static String TYPE_TAG = "tag";

    @SerializedName("id")
    private long id;

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public Taxonomy withId(long id) {
        setId(id);
        return this;
    }

    @SerializedName("count")
    private int count;

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public Taxonomy withCount(int count) {
        setCount(count);
        return this;
    }

    @SerializedName("description")
    private String description;

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public Taxonomy withDescription(String description) {
        setDescription(description);
        return this;
    }

    @SerializedName("link")
    private String link;

    public void setLink(String link) {
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public Taxonomy withLink(String link) {
        setLink(link);
        return this;
    }

    @SerializedName("name")
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Taxonomy withName(String name) {
        setName(name);
        return this;
    }

    @SerializedName("slug")
    private String slug;

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getSlug() {
        return slug;
    }

    public Taxonomy withSlug(String slug) {
        setSlug(slug);
        return this;
    }

    @SerializedName("taxonomy")
    private String taxonomy;

    public void setTaxonomy(String taxonomy) {
        this.taxonomy = taxonomy;
    }

    public String getTaxonomy() {
        return taxonomy;
    }

    public Taxonomy withTaxonomy(String taxonomy) {
        setTaxonomy(taxonomy);
        return this;
    }

    @SerializedName("parent")
    private long parent;

    public void setParent(long parent) {
        this.parent = parent;
    }

    public long getParent() {
        return parent;
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
    private ArrayList<Link> links;

    public void setLinks(ArrayList<Link> links) {
        this.links = links;
    }

    public void addLink(Link link) {
        if (links == null) {
            links = new ArrayList<>();
        }
        links.add(link);
    }

    public ArrayList<Link> getLinks() {
        return links;
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
        super(in);
        id = in.readLong();
        count = in.readInt();
        description = in.readString();
        link = in.readString();
        name = in.readString();
        slug = in.readString();
        taxonomy = in.readString();
        parent = in.readLong();
        in.readTypedList(links, Link.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeLong(id);
        dest.writeInt(count);
        dest.writeString(description);
        dest.writeString(link);
        dest.writeString(name);
        dest.writeString(slug);
        dest.writeString(taxonomy);
        dest.writeLong(parent);
        dest.writeTypedList(links);
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
                "id=" + id +
                ", count=" + count +
                ", description='" + description + '\'' +
                ", link='" + link + '\'' +
                ", name='" + name + '\'' +
                ", slug='" + slug + '\'' +
                ", taxonomy='" + taxonomy + '\'' +
                ", parent=" + parent +
                ", links=" + links +
                '}';
    }
}
