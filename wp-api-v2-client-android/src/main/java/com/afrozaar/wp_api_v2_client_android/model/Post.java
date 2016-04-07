package com.afrozaar.wp_api_v2_client_android.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.afrozaar.wp_api_v2_client_android.util.Validate;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Jan-Louis Crafford
 *         Created on 2015/12/03.
 */
public class Post extends WPObject<Post> {

    public static final String POST_STATUS_DRAFT = "draft";
    public static final String POST_STATUS_PUBLISH = "publish";
    public static final String POST_STATUS_PUBLISHED_PRIVATE = "private";
    public static final String POST_STATUS_FUTURE = "future";
    public static final String POST_STATUS_PENDING = "pending";
    public static final String POST_STATUS_TRASH = "trash";
    public static final String POST_STATUS_AUTO_DRAFT = "auto-draft";

    public static final String JSON_FIELD_CONTENT = "content";
    public static final String JSON_FIELD_EXCERPT = "excerpt";
    public static final String JSON_FIELD_FEATURED_IMAGE = "featured_media";
    public static final String JSON_FIELD_STICKY = "sticky";
    public static final String JSON_FIELD_FORMAT = "format";
    public static final String JSON_FIELD_STATUS = "status";
    public static final String JSON_FIELD_CATEGORIES = "categories";


    /**
     * A password to protect access to the post.
     */
    @SerializedName("password")
    private String password;

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public Post withPassword(String password) {
        setPassword(password);
        return this;
    }

    /**
     * The content for the object.
     */
    @SerializedName("content")
    private WPGeneric content;

    public void setContent(WPGeneric content) {
        this.content = content;
    }

    public WPGeneric getContent() {
        return content;
    }

    public Post withContent(String content) {
        WPGeneric generic = new WPGeneric();
        generic.setRaw(content);
        generic.setRendered(content);
        setContent(generic);
        return this;
    }

    /**
     * The excerpt for the object.
     */
    @SerializedName("excerpt")
    private WPGeneric excerpt;

    public void setExcerpt(WPGeneric excerpt) {
        this.excerpt = excerpt;
    }

    public WPGeneric getExcerpt() {
        return excerpt;
    }

    public Post withExcerpt(WPGeneric excerpt) {
        setExcerpt(excerpt);
        return this;
    }

    /**
     * ID of the featured image for the object.
     */
    @SerializedName("featured_media")
    private int featuredMedia = -1;

    public void setFeaturedMedia(int featuredMedia) {
        this.featuredMedia = featuredMedia;
    }

    public int getFeaturedMedia() {
        return featuredMedia;
    }

    public Post withFeaturedMedia(int featuredMedia) {
        setFeaturedMedia(featuredMedia);
        return this;
    }

    /**
     * Whether or not the object should be treated as sticky.
     */
    @SerializedName("sticky")
    private boolean sticky;

    public void setSticky(boolean sticky) {
        this.sticky = sticky;
    }

    public boolean getSticky() {
        return sticky;
    }

    public Post withSticky(boolean sticky) {
        setSticky(sticky);
        return this;
    }

    /**
     * The format for the object.
     */
    @SerializedName("format")
    private String format;

    public void setFormat(String format) {
        this.format = format;
    }

    public String getFormat() {
        return format;
    }

    public Post withFormat(String format) {
        setFormat(format);
        return this;
    }

    /**
     * A named status for the object.
     * <p/>
     * One of: publish, future, draft, pending, private
     */
    @SerializedName("status")
    private String status;

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public Post withStatus(String status) {
        setStatus(status);
        return this;
    }

    @SerializedName(JSON_FIELD_CATEGORIES)
    private List<Long> categories;

    public void setCategories(List<Long> categories) {
        this.categories = categories;
    }

    public List<Long> getCategories() {
        return categories;
    }

    public void addCategory(long catId) {
        if (categories == null) {
            categories = new ArrayList<>();
        }

        categories.add(catId);
    }

    public Post withCategories(List<Long> categories) {
        setCategories(categories);
        return this;
    }

    public Post withCategory(long catId) {
        addCategory(catId);
        return this;
    }


    @SerializedName("tags")
    private List<Long> tags;

    public void setTags(List<Long> tags) {
        this.tags = tags;
    }

    public List<Long> getTags() {
        return tags;
    }

    public void addTag(long tagId) {
        if (tags == null) {
            tags = new ArrayList<>();
        }
        tags.add(tagId);
    }

    public Post withTags(List<Long> tags) {
        setTags(tags);
        return this;
    }

    public Post withTag(long tagId) {
        addTag(tagId);
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

    @Override
    public Post withLinks(ArrayList<Link> links) {
        setLinks(links);
        return this;
    }

    @Override
    public Post withLink(Link link) {
        addLink(link);
        return this;
    }

    public Post() {
    }

    public Post(Parcel in) {
        super(in);

        password = in.readString();
        content = in.readParcelable(WPGeneric.class.getClassLoader());
        excerpt = in.readParcelable(WPGeneric.class.getClassLoader());
        featuredMedia = in.readInt();
        sticky = in.readByte() == 1;
        format = in.readString();
        status = in.readString();
        categories = new ArrayList<>();
        in.readList(categories, Long.class.getClassLoader());
        tags = new ArrayList<>();
        in.readList(tags, Long.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);

        dest.writeString(password);
        dest.writeParcelable(content, flags);
        dest.writeParcelable(excerpt, flags);
        dest.writeInt(featuredMedia);
        dest.writeByte((byte) (sticky ? 1 : 0));
        dest.writeString(format);
        dest.writeString(status);
        dest.writeList(categories);
        dest.writeList(tags);
    }

    public static Map<String, Object> mapFromFields(Post post) {
        Map<String, Object> builder = new HashMap<>();

        WPObject.mapFromFields(post, builder);

        if (post.getContent() != null) {
            Validate.validateMapEntry(JSON_FIELD_CONTENT, post.getContent().getRaw(), builder);
        }
        Validate.validateMapEntry(JSON_FIELD_EXCERPT, post.getExcerpt(), builder);
        Validate.validateMapLongEntry(JSON_FIELD_FEATURED_IMAGE, post.getFeaturedMedia(), builder);
        Validate.validateMapEntry(JSON_FIELD_STICKY, post.getSticky(), builder);
        Validate.validateMapEntry(JSON_FIELD_FORMAT, post.getFormat(), builder);
        Validate.validateMapEntry(JSON_FIELD_LINKS, post.getLinks(), builder);
        Validate.validateMapEntry(JSON_FIELD_STATUS, post.getStatus(), builder);
        Validate.validateMapEntry(JSON_FIELD_CATEGORIES, post.getCategories(), builder);

        return builder;
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
                ", content=" + content +
                ", excerpt=" + excerpt +
                ", featuredMedia=" + featuredMedia +
                ", sticky=" + sticky +
                ", format='" + format + '\'' +
                '}';
    }
}
