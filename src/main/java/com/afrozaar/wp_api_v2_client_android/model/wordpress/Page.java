package com.afrozaar.wp_api_v2_client_android.model.wordpress;

import com.google.common.collect.ImmutableMap;
import com.google.gson.annotations.SerializedName;

import java.util.Map;

/**
 * @author Jan-Louis Crafford
 *         Created on 2016/01/05.
 */
public class Page {

    @SerializedName("_links")
    private Links Links;
    @SerializedName("author")
    private Long author;
    @SerializedName("comment_status")
    private String commentStatus;
    @SerializedName("content")
    private Content content;
    @SerializedName("date")
    private String date;
    @SerializedName("date_gmt")
    private String dateGmt;
    @SerializedName("excerpt")
    private Excerpt excerpt;
    @SerializedName("featured_image")
    private Long featuredImage;
    @SerializedName("guid")
    private Guid guid;
    @SerializedName("id")
    private Long id;
    @SerializedName("link")
    private String link;
    @SerializedName("menu_order")
    private Long menuOrder;
    @SerializedName("modified")
    private String modified;
    @SerializedName("modified_gmt")
    private String modifiedGmt;
    @SerializedName("parent")
    private Long parent;
    @SerializedName("password")
    private String password;
    @SerializedName("ping_status")
    private String pingStatus;
    @SerializedName("slug")
    private String slug;
    @SerializedName("status")
    private String status;
    @SerializedName("template")
    private String template;
    @SerializedName("title")
    private Title title;
    @SerializedName("type")
    private String type;

    //@JsonIgnore
    //private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * @return The Links
     */
    @SerializedName("_links")
    public Links getLinks() {
        return Links;
    }

    /**
     * @param Links The _links
     */
    @SerializedName("_links")
    public void setLinks(Links Links) {
        this.Links = Links;
    }

    /**
     * @return The author
     */
    @SerializedName("author")
    public Long getAuthor() {
        return author;
    }

    /**
     * @param author The author
     */
    @SerializedName("author")
    public void setAuthor(Long author) {
        this.author = author;
    }

    /**
     * @return The commentStatus
     */
    @SerializedName("comment_status")
    public String getCommentStatus() {
        return commentStatus;
    }

    /**
     * @param commentStatus The comment_status
     */
    @SerializedName("comment_status")
    public void setCommentStatus(String commentStatus) {
        this.commentStatus = commentStatus;
    }

    /**
     * @return The content
     */
    @SerializedName("content")
    public Content getContent() {
        return content;
    }

    /**
     * @param content The content
     */
    @SerializedName("content")
    public void setContent(Content content) {
        this.content = content;
    }

    /**
     * @return The date
     */
    @SerializedName("date")
    public String getDate() {
        return date;
    }

    /**
     * @param date The date
     */
    @SerializedName("date")
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return The dateGmt
     */
    @SerializedName("date_gmt")
    public String getDateGmt() {
        return dateGmt;
    }

    /**
     * @param dateGmt The date_gmt
     */
    @SerializedName("date_gmt")
    public void setDateGmt(String dateGmt) {
        this.dateGmt = dateGmt;
    }

    /**
     * @return The excerpt
     */
    @SerializedName("excerpt")
    public Excerpt getExcerpt() {
        return excerpt;
    }

    /**
     * @param excerpt The excerpt
     */
    @SerializedName("excerpt")
    public void setExcerpt(Excerpt excerpt) {
        this.excerpt = excerpt;
    }

    /**
     * @return The featuredImage
     */
    @SerializedName("featured_image")
    public Long getFeaturedImage() {
        return featuredImage;
    }

    /**
     * @param featuredImage The featured_image
     */
    @SerializedName("featured_image")
    public void setFeaturedImage(Long featuredImage) {
        this.featuredImage = featuredImage;
    }

    /**
     * @return The guid
     */
    @SerializedName("guid")
    public Guid getGuid() {
        return guid;
    }

    /**
     * @param guid The guid
     */
    @SerializedName("guid")
    public void setGuid(Guid guid) {
        this.guid = guid;
    }

    /**
     * @return The id
     */
    @SerializedName("id")
    public Long getId() {
        return id;
    }

    /**
     * @param id The id
     */
    @SerializedName("id")
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return The link
     */
    @SerializedName("link")
    public String getLink() {
        return link;
    }

    /**
     * @param link The link
     */
    @SerializedName("link")
    public void setLink(String link) {
        this.link = link;
    }

    /**
     * @return The menuOrder
     */
    @SerializedName("menu_order")
    public Long getMenuOrder() {
        return menuOrder;
    }

    /**
     * @param menuOrder The menu_order
     */
    @SerializedName("menu_order")
    public void setMenuOrder(Long menuOrder) {
        this.menuOrder = menuOrder;
    }

    /**
     * @return The modified
     */
    @SerializedName("modified")
    public String getModified() {
        return modified;
    }

    /**
     * @param modified The modified
     */
    @SerializedName("modified")
    public void setModified(String modified) {
        this.modified = modified;
    }

    /**
     * @return The modifiedGmt
     */
    @SerializedName("modified_gmt")
    public String getModifiedGmt() {
        return modifiedGmt;
    }

    /**
     * @param modifiedGmt The modified_gmt
     */
    @SerializedName("modified_gmt")
    public void setModifiedGmt(String modifiedGmt) {
        this.modifiedGmt = modifiedGmt;
    }

    /**
     * @return The parent
     */
    @SerializedName("parent")
    public Long getParent() {
        return parent;
    }

    /**
     * @param parent The parent
     */
    @SerializedName("parent")
    public void setParent(Long parent) {
        this.parent = parent;
    }

    /**
     * @return The password
     */
    @SerializedName("password")
    public String getPassword() {
        return password;
    }

    /**
     * @param password The password
     */
    @SerializedName("password")
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return The pingStatus
     */
    @SerializedName("ping_status")
    public String getPingStatus() {
        return pingStatus;
    }

    /**
     * @param pingStatus The ping_status
     */
    @SerializedName("ping_status")
    public void setPingStatus(String pingStatus) {
        this.pingStatus = pingStatus;
    }

    /**
     * @return The slug
     */
    @SerializedName("slug")
    public String getSlug() {
        return slug;
    }

    /**
     * @param slug The slug
     */
    @SerializedName("slug")
    public void setSlug(String slug) {
        this.slug = slug;
    }

    /**
     * @return The status
     */
    @SerializedName("status")
    public String getStatus() {
        return status;
    }

    /**
     * @param status The status
     */
    @SerializedName("status")
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return The template
     */
    @SerializedName("template")
    public String getTemplate() {
        return template;
    }

    /**
     * @param template The template
     */
    @SerializedName("template")
    public void setTemplate(String template) {
        this.template = template;
    }

    /**
     * @return The title
     */
    @SerializedName("title")
    public Title getTitle() {
        return title;
    }

    /**
     * @param title The title
     */
    @SerializedName("title")
    public void setTitle(Title title) {
        this.title = title;
    }

    /**
     * @return The type
     */
    @SerializedName("type")
    public String getType() {
        return type;
    }

    /**
     * @param type The type
     */
    @SerializedName("type")
    public void setType(String type) {
        this.type = type;
    }

    public Map<String, Object> asMap() {
        ImmutableMap.Builder<String, Object> builder = new ImmutableMap.Builder<>();

        populateEntry(getLinks(), builder, "_links");

        // TODO add other fields to map

        return builder.build();
    }

    private void populateEntry(Object value, ImmutableMap.Builder<String, Object> builder, String key) {
        if (value != null) { //Optional.fromNullable(value).isPresent()
            builder.put(key, value);
        }
    }
}
