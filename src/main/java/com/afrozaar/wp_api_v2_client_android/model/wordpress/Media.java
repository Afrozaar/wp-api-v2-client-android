package com.afrozaar.wp_api_v2_client_android.model.wordpress;

/**
 * Created by jay on 12/10/15.
 */
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableMap;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.springframework.core.io.Resource;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;
import java.util.Map;

public class Media {

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
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("title")
    @Expose
    private Title title;
    @SerializedName("author")
    @Expose
    private long author;
    @SerializedName("comment_status")
    @Expose
    private String commentStatus;
    @SerializedName("ping_status")
    @Expose
    private String pingStatus;
    @SerializedName("alt_text")
    @Expose
    private String altText;
    @SerializedName("caption")
    @Expose
    private String caption;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("media_type")
    @Expose
    private String mediaType;
    @SerializedName("media_details")
    @Expose
    private MediaDetails mediaDetails;
    @SerializedName("postId")
    @Expose
    private long postId;
    @SerializedName("source_url")
    @Expose
    private String sourceUrl;
    @SerializedName("_links")
    @Expose
    private Links Links;

    /**
     *
     * @return
     * The id
     */
    public long getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(long id) {
        this.id = id;
    }

    public Media withId(long id) {
        this.id = id;
        return this;
    }

    /**
     *
     * @return
     * The date
     */
    public String getDate() {
        return date;
    }

    /**
     *
     * @param date
     * The date
     */
    public void setDate(String date) {
        this.date = date;
    }

    public Media withDate(String date) {
        this.date = date;
        return this;
    }

    /**
     *
     * @return
     * The dateGmt
     */
    public String getDateGmt() {
        return dateGmt;
    }

    /**
     *
     * @param dateGmt
     * The date_gmt
     */
    public void setDateGmt(String dateGmt) {
        this.dateGmt = dateGmt;
    }

    public Media withDateGmt(String dateGmt) {
        this.dateGmt = dateGmt;
        return this;
    }

    /**
     *
     * @return
     * The guid
     */
    public Guid getGuid() {
        return guid;
    }

    /**
     *
     * @param guid
     * The guid
     */
    public void setGuid(Guid guid) {
        this.guid = guid;
    }

    public Media withGuid(Guid guid) {
        this.guid = guid;
        return this;
    }

    /**
     *
     * @return
     * The modified
     */
    public String getModified() {
        return modified;
    }

    /**
     *
     * @param modified
     * The modified
     */
    public void setModified(String modified) {
        this.modified = modified;
    }

    @Override
    public String toString() {
        return "Media{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", dateGmt='" + dateGmt + '\'' +
                ", guid=" + guid +
                ", modified='" + modified + '\'' +
                ", modifiedGmt='" + modifiedGmt + '\'' +
                ", slug='" + slug + '\'' +
                ", type='" + type + '\'' +
                ", link='" + link + '\'' +
                ", title=" + title +
                ", author=" + author +
                ", commentStatus='" + commentStatus + '\'' +
                ", pingStatus='" + pingStatus + '\'' +
                ", altText='" + altText + '\'' +
                ", caption='" + caption + '\'' +
                ", description='" + description + '\'' +
                ", mediaType='" + mediaType + '\'' +
                ", mediaDetails=" + mediaDetails +
                ", postId=" + postId +
                ", sourceUrl='" + sourceUrl + '\'' +
                ", Links=" + Links +
                '}';
    }

    public Media withModified(String modified) {
        this.modified = modified;
        return this;
    }

    /**
     *
     * @return
     * The modifiedGmt
     */
    public String getModifiedGmt() {
        return modifiedGmt;
    }

    /**
     *
     * @param modifiedGmt
     * The modified_gmt
     */
    public void setModifiedGmt(String modifiedGmt) {
        this.modifiedGmt = modifiedGmt;
    }

    public Media withModifiedGmt(String modifiedGmt) {
        this.modifiedGmt = modifiedGmt;
        return this;
    }

    /**
     *
     * @return
     * The slug
     */
    public String getSlug() {
        return slug;
    }

    /**
     *
     * @param slug
     * The slug
     */
    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Media withSlug(String slug) {
        this.slug = slug;
        return this;
    }

    /**
     *
     * @return
     * The type
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @param type
     * The type
     */
    public void setType(String type) {
        this.type = type;
    }

    public Media withType(String type) {
        this.type = type;
        return this;
    }

    /**
     *
     * @return
     * The link
     */
    public String getLink() {
        return link;
    }

    /**
     *
     * @param link
     * The link
     */
    public void setLink(String link) {
        this.link = link;
    }

    public Media withLink(String link) {
        this.link = link;
        return this;
    }

    /**
     *
     * @return
     * The title
     */
    public Title getTitle() {
        return title;
    }

    /**
     *
     * @param title
     * The title
     */
    public void setTitle(Title title) {
        this.title = title;
    }

    public Media withTitle(Title title) {
        this.title = title;
        return this;
    }

    /**
     *
     * @return
     * The author
     */
    public long getAuthor() {
        return author;
    }

    /**
     *
     * @param author
     * The author
     */
    public void setAuthor(long author) {
        this.author = author;
    }

    public Media withAuthor(long author) {
        this.author = author;
        return this;
    }

    /**
     *
     * @return
     * The commentStatus
     */
    public String getCommentStatus() {
        return commentStatus;
    }

    /**
     *
     * @param commentStatus
     * The comment_status
     */
    public void setCommentStatus(String commentStatus) {
        this.commentStatus = commentStatus;
    }

    public Media withCommentStatus(String commentStatus) {
        this.commentStatus = commentStatus;
        return this;
    }

    /**
     *
     * @return
     * The pingStatus
     */
    public String getPingStatus() {
        return pingStatus;
    }

    /**
     *
     * @param pingStatus
     * The ping_status
     */
    public void setPingStatus(String pingStatus) {
        this.pingStatus = pingStatus;
    }

    public Media withPingStatus(String pingStatus) {
        this.pingStatus = pingStatus;
        return this;
    }

    /**
     *
     * @return
     * The altText
     */
    public String getAltText() {
        return altText;
    }

    /**
     *
     * @param altText
     * The alt_text
     */
    public void setAltText(String altText) {
        this.altText = altText;
    }

    public Media withAltText(String altText) {
        this.altText = altText;
        return this;
    }

    /**
     *
     * @return
     * The caption
     */
    public String getCaption() {
        return caption;
    }

    /**
     *
     * @param caption
     * The caption
     */
    public void setCaption(String caption) {
        this.caption = caption;
    }

    public Media withCaption(String caption) {
        this.caption = caption;
        return this;
    }

    /**
     *
     * @return
     * The description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     * The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    public Media withDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     *
     * @return
     * The mediaType
     */
    public String getMediaType() {
        return mediaType;
    }

    /**
     *
     * @param mediaType
     * The media_type
     */
    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public Media withMediaType(String mediaType) {
        this.mediaType = mediaType;
        return this;
    }

    /**
     *
     * @return
     * The mediaDetails
     */
    public MediaDetails getMediaDetails() {
        return mediaDetails;
    }

    /**
     *
     * @param mediaDetails
     * The media_details
     */
    public void setMediaDetails(MediaDetails mediaDetails) {
        this.mediaDetails = mediaDetails;
    }

    public Media withMediaDetails(MediaDetails mediaDetails) {
        this.mediaDetails = mediaDetails;
        return this;
    }

    /**
     *
     * @return
     * The postId
     */
    public long getPostId() {
        return postId;
    }

    /**
     *
     * @param postId
     * The postId
     */
    public void setPostId(long postId) {
        this.postId = postId;
    }

    public Media withPostId(long postId) {
        this.postId = postId;
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

    public Media withSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
        return this;
    }

    /**
     *
     * @return
     * The Links
     */
    public Links getLinks() {
        return Links;
    }

    /**
     *
     * @param Links
     * The _links
     */
    public void setLinks(Links Links) {
        this.Links = Links;
    }

    public Media withLinks(Links Links) {
        this.Links = Links;
        return this;
    }

    public static Map<String, Object> fieldsFrom(Media media) {
        ImmutableMap.Builder<String, Object> builder = new ImmutableMap.Builder<>();

        if(media.getTitle()!=null) {
            populateEntry(media.getTitle().getRendered(), builder, "title");
        }
        populateEntry(media.getPostId(), builder, "post");
        populateEntry(media.getAltText(), builder,"alt_text");
        populateEntry(media.getCaption(), builder, "caption");
        populateEntry(media.getDescription(), builder,"description");

        /*BiConsumer<String, Object> p = (index, value) ->
                    Optional.ofNullable(value).ifPresent(v -> uploadMap.add(index, v));

            p.accept("title", media.getTitle().getRendered());
            p.accept("post", media.getPost());
            p.accept("alt_text", media.getAltText());
            p.accept("caption", media.getCaption());
            p.accept("description", media.getDescription());*/

        return builder.build();
    }

    public static MultiValueMap<String,Object> fieldsFrom(Media media, Resource res){
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        /*map.add("post", media.getPostId());
        if(media.getAltText() != null) {
            map.add("alt_text", media.getAltText());
        }
        if(media.getCaption() != null) {
            map.add("caption",media.getCaption());
        }
        if(media.getDescription() != null) {
            map.add("description",media.getDescription());
        }*/
        if (res != null){
            //map.add("data",res);
        }
        return map;
    }

    private static void populateEntry(Object value, ImmutableMap.Builder<String, Object> builder, String key) {
        if (value != null) { //Optional.fromNullable(value).isPresent()
            builder.put(key, value);
        }
    }

}
