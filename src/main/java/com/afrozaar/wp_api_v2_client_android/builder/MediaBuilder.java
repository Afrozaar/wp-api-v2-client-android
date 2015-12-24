package com.afrozaar.wp_api_v2_client_android.builder;

import com.afrozaar.wp_api_v2_client_android.model.wordpress.Guid;
import com.afrozaar.wp_api_v2_client_android.model.wordpress.Media;
import com.afrozaar.wp_api_v2_client_android.model.wordpress.MediaDetails;
import com.afrozaar.wp_api_v2_client_android.model.wordpress.Title;

/**
 * Created by jay on 12/10/15.
 */
public class MediaBuilder {
    private String altText;
    private Long id;
    private MediaDetails mediaDetails;
    private int author;
    private Guid guid;
    private String date;
    private String modified;
    private String modifiedGmt;
    private String slug;
    private String type;
    private String link;
    private Title title;
    private String commentStatus;
    private String pingStatus;
    private String caption;
    private String description;
    private String mediaType;
    private Long post;
    private String sourceUrl;

    private MediaBuilder() {
    }

    public static MediaBuilder aMedia() {
        return new MediaBuilder();
    }

    public MediaBuilder withAltText(String altText) {
        this.altText = altText;
        return this;
    }

    public MediaBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public MediaBuilder withMediaDetails(MediaDetails mediaDetails) {
        this.mediaDetails = mediaDetails;
        return this;
    }

    public MediaBuilder withAuthor(int author) {
        this.author = author;
        return this;
    }

    public MediaBuilder withGuid(Guid guid) {
        this.guid = guid;
        return this;
    }

    public MediaBuilder withDate(String date) {
        this.date = date;
        return this;
    }

    public MediaBuilder withModified(String modified) {
        this.modified = modified;
        return this;
    }

    public MediaBuilder withModifiedGmt(String modifiedGmt) {
        this.modifiedGmt = modifiedGmt;
        return this;
    }

    public MediaBuilder withSlug(String slug) {
        this.slug = slug;
        return this;
    }

    public MediaBuilder withType(String type) {
        this.type = type;
        return this;
    }

    public MediaBuilder withLink(String link) {
        this.link = link;
        return this;
    }

    public MediaBuilder withTitle(Title title) {
        this.title = title;
        return this;
    }

    public MediaBuilder withCommentStatus(String commentStatus) {
        this.commentStatus = commentStatus;
        return this;
    }

    public MediaBuilder withPingStatus(String pingStatus) {
        this.pingStatus = pingStatus;
        return this;
    }

    public MediaBuilder withCaption(String caption) {
        this.caption = caption;
        return this;
    }

    public MediaBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public MediaBuilder withMediaType(String mediaType) {
        this.mediaType = mediaType;
        return this;
    }

    public MediaBuilder withPost(Long post) {
        this.post = post;
        return this;
    }

    public MediaBuilder withSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
        return this;
    }

    public MediaBuilder but() {
        return aMedia().withAltText(altText).withId(id).withMediaDetails(mediaDetails).withAuthor(author).withGuid(guid).withDate(date).withModified(modified).withModifiedGmt(modifiedGmt).withSlug(slug).withType(type).withLink(link).withTitle(title).withCommentStatus(commentStatus).withPingStatus(pingStatus).withCaption(caption).withDescription(description).withMediaType(mediaType).withPost(post).withSourceUrl(sourceUrl);
    }

    public Media build() {
        Media media = new Media();
        media.setAltText(altText);
        media.setId(id);
        media.setMediaDetails(mediaDetails);
        media.setAuthor(author);
        media.setGuid(guid);
        media.setDate(date);
        media.setModified(modified);
        media.setModifiedGmt(modifiedGmt);
        media.setSlug(slug);
        media.setType(type);
        media.setLink(link);
        media.setTitle(title);
        media.setCommentStatus(commentStatus);
        media.setPingStatus(pingStatus);
        media.setCaption(caption);
        media.setDescription(description);
        media.setMediaType(mediaType);
        media.setPostId(post);
        media.setSourceUrl(sourceUrl);
        return media;
    }
}
