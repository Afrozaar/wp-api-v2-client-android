package com.afrozaar.wp_api_v2_client_android.model.wp_v1;

import android.os.Parcel;
import android.os.Parcelable;

import com.afrozaar.wp_api_v2_client_android.util.Validate;
import com.google.common.collect.ImmutableMap;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Map;

/**
 * @author Jan-Louis Crafford
 *         Created on 2015/12/03.
 */
public class Post extends WPObject<Post> {

    public static final String JSON_FIELD_CONTENT = "content";
    public static final String JSON_FIELD_EXCERPT = "excerpt";
    public static final String JSON_FIELD_FEATURED_IMAGE = "featured_image";
    public static final String JSON_FIELD_STICKY = "sticky";
    public static final String JSON_FIELD_FORMAT = "format";
    public static final String JSON_FIELD_LINKS = "_links";

    /**
     * The content for the object.
     */
    @SerializedName("content")
    private WPGeneric mContent;

    public void setContent(WPGeneric content) {
        mContent = content;
    }

    public WPGeneric getContent() {
        return mContent;
    }

    public Post withContent(String content) {
        WPGeneric generic = new WPGeneric();
        generic.setRendered(content);
        setContent(generic);
        return this;
    }

    /**
     * The excerpt for the object.
     */
    @SerializedName("excerpt")
    private WPGeneric mExcerpt;

    public void setExcerpt(WPGeneric excerpt) {
        mExcerpt = excerpt;
    }

    public WPGeneric getExcerpt() {
        return mExcerpt;
    }

    public Post withExcerpt(WPGeneric excerpt) {
        setExcerpt(excerpt);
        return this;
    }

    /**
     * ID of the featured image for the object.
     */
    @SerializedName("featured_image")
    private int mFeaturedImage;

    public void setFeaturedImage(int featuredImage) {
        mFeaturedImage = featuredImage;
    }

    public int getFeaturedImage() {
        return mFeaturedImage;
    }

    public Post withFeaturedImage(int featuredImage) {
        setFeaturedImage(featuredImage);
        return this;
    }

    /**
     * Whether or not the object should be treated as sticky.
     */
    @SerializedName("sticky")
    private boolean mSticky;

    public void setSticky(boolean sticky) {
        mSticky = sticky;
    }

    public boolean getSticky() {
        return mSticky;
    }

    public Post withSticky(boolean sticky) {
        setSticky(sticky);
        return this;
    }

    /**
     * The format for the object.
     */
    @SerializedName("format")
    private String mFormat;

    public void setFormat(String format) {
        mFormat = format;
    }

    public String getFormat() {
        return mFormat;
    }

    public Post withFormat(String format) {
        setFormat(format);
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

    public Post withLinks(ArrayList<Link> links) {
        setLinks(links);
        return this;
    }

    public Post withLink(Link link) {
        addLink(link);
        return this;
    }

    @Override
    public Post withId(long id) {
        setId(id);
        return this;
    }

    @Override
    public Post withDate(String date) {
        setDate(date);
        return this;
    }

    @Override
    public Post withDateGMT(String dateGMT) {
        setDateGMT(dateGMT);
        return this;
    }

    @Override
    public Post withGuid(WPGeneric guid) {
        setGuid(guid);
        return this;
    }

    @Override
    public Post withModified(String modified) {
        setModified(modified);
        return this;
    }

    @Override
    public Post withModifiedGMT(String modifiedGMT) {
        setModifiedGMT(modifiedGMT);
        return this;
    }

    @Override
    public Post withSlug(String slug) {
        setSlug(slug);
        return this;
    }

    @Override
    public Post withType(String type) {
        setType(type);
        return this;
    }

    @Override
    public Post withLink(String link) {
        setLink(link);
        return this;
    }

    @Override
    public Post withTitle(String title) {
        WPGeneric generic = new WPGeneric();
        generic.setRendered(title);
        setTitle(generic);
        return this;
    }

    @Override
    public Post withAuthor(int author) {
        setAuthor(author);
        return this;
    }

    @Override
    public Post withCommentStatus(WPStatus commentStatus) {
        setCommentStatus(commentStatus);
        return this;
    }

    @Override
    public Post withPingStatus(WPStatus pingStatus) {
        setPingStatus(pingStatus);
        return this;
    }

    public Post() {
    }

    public Post(Parcel in) {
        super(in);

        mContent = in.readParcelable(WPGeneric.class.getClassLoader());
        mExcerpt = in.readParcelable(WPGeneric.class.getClassLoader());
        mFeaturedImage = in.readInt();
        mSticky = in.readByte() == 1;
        mFormat = in.readString();
        in.readTypedList(mLinks, Link.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);

        dest.writeParcelable(mContent, flags);
        dest.writeParcelable(mExcerpt, flags);
        dest.writeInt(mFeaturedImage);
        dest.writeByte((byte) (mSticky ? 1 : 0));
        dest.writeString(mFormat);
        dest.writeTypedList(mLinks);
    }

    public static Map<String, Object> mapFromFields(Post post) {
        ImmutableMap.Builder<String, Object> builder = new ImmutableMap.Builder<>();

        WPObject.mapFromFields(post, builder);

        Validate.validateMapEntry(JSON_FIELD_CONTENT, post.getContent().getRendered(), builder);
        Validate.validateMapEntry(JSON_FIELD_EXCERPT, post.getExcerpt(), builder);
        Validate.validateMapEntry(JSON_FIELD_FEATURED_IMAGE, post.getFeaturedImage(), builder);
        Validate.validateMapEntry(JSON_FIELD_STICKY, post.getSticky(), builder);
        Validate.validateMapEntry(JSON_FIELD_FORMAT, post.getFormat(), builder);
        Validate.validateMapEntry(JSON_FIELD_LINKS, post.getLinks(), builder);

        return builder.build();
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

    @Override
    public String toString() {
        return super.toString() + "--> Post{" +
                ", mContent=" + mContent +
                ", mExcerpt=" + mExcerpt +
                ", mFeaturedImage=" + mFeaturedImage +
                ", mSticky=" + mSticky +
                ", mFormat='" + mFormat + '\'' +
                ", mLinks=" + mLinks +
                '}';
    }
}
