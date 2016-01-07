package com.afrozaar.wp_api_v2_client_android.model.wp_v2;

/**
 * Created by jay on 12/9/15.
 */

import com.google.common.collect.ImmutableMap;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class Post {

    public static final String JSON_FIELD_FEATURED_IMAGE = "featured_image";

    @SerializedName("id")
    @Expose
    private long id;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("date_gmt")
    @Expose
    private String dateGmt;
    @SerializedName("guid")
    @Expose
    private Guid guid;
    @SerializedName("modified")
    @Expose
    private String modified;
    @SerializedName("modified_gmt")
    @Expose
    private String modifiedGmt;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("title")
    @Expose
    private Title title;
    @SerializedName("content")
    @Expose
    private Content content;
    @SerializedName("excerpt")
    @Expose
    private Excerpt excerpt;
    @SerializedName("author")
    @Expose
    private long author;
    @SerializedName(JSON_FIELD_FEATURED_IMAGE)
    @Expose
    private long featuredImage;
    @SerializedName("comment_status")
    @Expose
    private String commentStatus;
    @SerializedName("ping_status")
    @Expose
    private String pingStatus;
    @SerializedName("sticky")
    @Expose
    private boolean sticky;
    @SerializedName("format")
    @Expose
    private String format;
    @SerializedName("_links")
    @Expose
    private Links Links;

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", dateGmt='" + dateGmt + '\'' +
                ", guid=" + guid +
                ", modified='" + modified + '\'' +
                ", modifiedGmt='" + modifiedGmt + '\'' +
                ", password='" + password + '\'' +
                ", slug='" + slug + '\'' +
                ", status='" + status + '\'' +
                ", type='" + type + '\'' +
                ", link='" + link + '\'' +
                ", title=" + title +
                ", content=" + content +
                ", excerpt=" + excerpt +
                ", author=" + author +
                ", featuredImage=" + featuredImage +
                ", commentStatus='" + commentStatus + '\'' +
                ", pingStatus='" + pingStatus + '\'' +
                ", sticky=" + sticky +
                ", format='" + format + '\'' +
                ", Links=" + Links +
                '}';
    }

    /**
     * @return The id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(long id) {
        this.id = id;
    }

    public Post withId(long id) {
        this.id = id;
        return this;
    }

    /**
     * @return The date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date The date
     */
    public void setDate(String date) {
        this.date = date;
    }

    public Post withDate(String date) {
        this.date = date;
        return this;
    }

    /**
     * @return The dateGmt
     */
    public String getDateGmt() {
        return dateGmt;
    }

    /**
     * @param dateGmt The date_gmt
     */
    public void setDateGmt(String dateGmt) {
        this.dateGmt = dateGmt;
    }

    public Post withDateGmt(String dateGmt) {
        this.dateGmt = dateGmt;
        return this;
    }

    /**
     * @return The guid
     */
    public Guid getGuid() {
        return guid;
    }

    /**
     * @param guid The guid
     */
    public void setGuid(Guid guid) {
        this.guid = guid;
    }

    public Post withGuid(Guid guid) {
        this.guid = guid;
        return this;
    }

    /**
     * @return The modified
     */
    public String getModified() {
        return modified;
    }

    /**
     * @param modified The modified
     */
    public void setModified(String modified) {
        this.modified = modified;
    }

    public Post withModified(String modified) {
        this.modified = modified;
        return this;
    }

    /**
     * @return The modifiedGmt
     */
    public String getModifiedGmt() {
        return modifiedGmt;
    }

    /**
     * @param modifiedGmt The modified_gmt
     */
    public void setModifiedGmt(String modifiedGmt) {
        this.modifiedGmt = modifiedGmt;
    }

    public Post withModifiedGmt(String modifiedGmt) {
        this.modifiedGmt = modifiedGmt;
        return this;
    }

    /**
     * @return The password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password The password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public Post withPassword(String password) {
        this.password = password;
        return this;
    }

    /**
     * @return The slug
     */
    public String getSlug() {
        return slug;
    }

    /**
     * @param slug The slug
     */
    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Post withSlug(String slug) {
        this.slug = slug;
        return this;
    }

    /**
     * @return The status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status The status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    public Post withStatus(String status) {
        this.status = status;
        return this;
    }

    /**
     * @return The type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type The type
     */
    public void setType(String type) {
        this.type = type;
    }

    public Post withType(String type) {
        this.type = type;
        return this;
    }

    /**
     * @return The link
     */
    public String getLink() {
        return link;
    }

    /**
     * @param link The link
     */
    public void setLink(String link) {
        this.link = link;
    }

    public Post withLink(String link) {
        this.link = link;
        return this;
    }

    /**
     * @return The title
     */
    public Title getTitle() {
        return title;
    }

    /**
     * @param title The title
     */
    public void setTitle(Title title) {
        this.title = title;
    }

    public Post withTitle(Title title) {
        this.title = title;
        return this;
    }

    /**
     * @return The content
     */
    public Content getContent() {
        return content;
    }

    /**
     * @param content The content
     */
    public void setContent(Content content) {
        this.content = content;
    }

    public Post withContent(Content content) {
        this.content = content;
        return this;
    }

    /**
     * @return The excerpt
     */
    public Excerpt getExcerpt() {
        return excerpt;
    }

    /**
     * @param excerpt The excerpt
     */
    public void setExcerpt(Excerpt excerpt) {
        this.excerpt = excerpt;
    }

    public Post withExcerpt(Excerpt excerpt) {
        this.excerpt = excerpt;
        return this;
    }

    /**
     * @return The author
     */
    public long getAuthor() {
        return author;
    }

    /**
     * @param author The author
     */
    public void setAuthor(long author) {
        this.author = author;
    }

    public Post withAuthor(long author) {
        this.author = author;
        return this;
    }

    /**
     * @return The featuredImage
     */
    public long getFeaturedImage() {
        return featuredImage;
    }

    /**
     * @param featuredImage The featured_image
     */
    public void setFeaturedImage(long featuredImage) {
        this.featuredImage = featuredImage;
    }

    public Post withFeaturedImage(long featuredImage) {
        this.featuredImage = featuredImage;
        return this;
    }

    /**
     * @return The commentStatus
     */
    public String getCommentStatus() {
        return commentStatus;
    }

    /**
     * @param commentStatus The comment_status
     */
    public void setCommentStatus(String commentStatus) {
        this.commentStatus = commentStatus;
    }

    public Post withCommentStatus(String commentStatus) {
        this.commentStatus = commentStatus;
        return this;
    }

    /**
     * @return The pingStatus
     */
    public String getPingStatus() {
        return pingStatus;
    }

    /**
     * @param pingStatus The ping_status
     */
    public void setPingStatus(String pingStatus) {
        this.pingStatus = pingStatus;
    }

    public Post withPingStatus(String pingStatus) {
        this.pingStatus = pingStatus;
        return this;
    }

    /**
     * @return The sticky
     */
    public boolean isSticky() {
        return sticky;
    }

    /**
     * @param sticky The sticky
     */
    public void setSticky(boolean sticky) {
        this.sticky = sticky;
    }

    public Post withSticky(boolean sticky) {
        this.sticky = sticky;
        return this;
    }

    /**
     * @return The format
     */
    public String getFormat() {
        return format;
    }

    /**
     * @param format The format
     */
    public void setFormat(String format) {
        this.format = format;
    }

    public Post withFormat(String format) {
        this.format = format;
        return this;
    }

    /**
     * @return The Links
     */
    public Links getLinks() {
        return Links;
    }

    /**
     * @param Links The _links
     */
    public void setLinks(Links Links) {
        this.Links = Links;
    }

    public Post withLinks(Links Links) {
        this.Links = Links;
        return this;
    }

    public static Map<String, Object> fieldsFrom(Post post) {
        ImmutableMap.Builder<String, Object> builder = new ImmutableMap.Builder<>();

        populateEntry(post.getDate(), builder, "date");
        populateEntry(post.getModifiedGmt(), builder, "modified_gmt");
        //populateEntry(post::getSlug, builder, "slug");
        //populateEntry(post::getCommentStatus, builder, "status");
        if (post.getTitle() != null) {
            populateEntry(post.getTitle().getRendered(), builder, "title");
        }
        if (post.getContent() != null) {
            populateEntry(post.getContent().getRendered(), builder, "content");
        }
        if (post.getExcerpt() != null) {
            populateEntry(post.getExcerpt().getRendered(), builder, "excerpt");
        }
        populateEntry(post.getAuthor(), builder, "author");
        populateEntry(post.getCommentStatus(), builder, "comment_status");
        populateEntry(post.getPingStatus(), builder, "ping_status");
        populateEntry(post.getFormat(), builder, "format");
        populateEntry(post.isSticky(), builder, "sticky");
        populateEntry(post.getFeaturedImage(), builder, "featured_image");

        return builder.build();
    }

    private static void populateEntry(Object value, ImmutableMap.Builder<String, Object> builder, String key) {
        if (value != null) { //Optional.fromNullable(value).isPresent()
            builder.put(key, value);
        }
    }

}
