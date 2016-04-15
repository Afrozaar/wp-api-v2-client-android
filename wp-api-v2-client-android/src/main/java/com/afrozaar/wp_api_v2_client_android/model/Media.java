package com.afrozaar.wp_api_v2_client_android.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.afrozaar.wp_api_v2_client_android.util.Validate;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Jan-Louis Crafford
 *         Created on 2016/01/07.
 */
public class Media extends WPObject<Media> {

    public static final String JSON_FIELD_ALT_TEXT = "alt_text";
    public static final String JSON_FIELD_CAPTION = "caption";
    public static final String JSON_FIELD_DESCRIPTION = "description";
    public static final String JSON_FIELD_MEDIA_TYPE = "media_type";
    public static final String JSON_FIELD_POST = "post";
    public static final String JSON_FIELD_SOURCE_URL = "source_url";
    public static final String JSON_FIELD_MEDIA_DETAILS = "media_details";

    /**
     * Alternative text to display when attachment is not displayed.
     */
    @SerializedName("alt_text")
    private String altText;

    public void setAltText(String altText) {
        this.altText = altText;
    }

    public String getAltText() {
        return altText;
    }

    public Media withAltText(String altText) {
        setAltText(altText);
        return this;
    }

    /**
     * The caption for the attachment.
     */
    @SerializedName("caption")
    private String caption;

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getCaption() {
        return caption;
    }

    public Media withCaption(String caption) {
        setCaption(caption);
        return this;
    }

    /**
     * The description for the attachment.
     */
    @SerializedName("description")
    private String description;

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public Media withDescription(String description) {
        setDescription(description);
        return this;
    }

    /**
     * Type of attachment.
     */
    @SerializedName("media_type")
    private String mediaType;

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getMediaType() {
        return mediaType;
    }

    public Media withMediaType(String mediaType) {
        setMediaType(mediaType);
        return this;
    }

    /**
     * The ID for the associated post of the attachment.
     */
    @SerializedName("post")
    private long postId = -1;

    public void setPost(long postId) {
        this.postId = postId;
    }

    public long getPostId() {
        return postId;
    }

    public Media withPostId(long postId) {
        setPost(postId);
        return this;
    }

    /**
     * URL to the original attachment file.
     */
    @SerializedName("source_url")
    private String sourceUrl;

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public Media withSourceUrl(String sourceUrl) {
        setSourceUrl(sourceUrl);
        return this;
    }

    /**
     * Details about the attachment file, specific to its type.
     */
    @SerializedName("media_details")
    private MediaDetails mediaDetails;

    public void setMediaDetails(MediaDetails mediaDetails) {
        this.mediaDetails = mediaDetails;
    }

    public MediaDetails getMediaDetails() {
        return mediaDetails;
    }

    public Media withMediaDetails(MediaDetails mediaDetails) {
        setMediaDetails(mediaDetails);
        return this;
    }

    @Override
    public Media withId(long id) {
        setId(id);
        return this;
    }

    @Override
    public Media withDate(String date) {
        setDate(date);
        return this;
    }

    @Override
    public Media withDateGMT(String dateGMT) {
        setDateGMT(dateGMT);
        return this;
    }

    @Override
    public Media withGuid(WPGeneric guid) {
        setGuid(guid);
        return this;
    }

    @Override
    public Media withModified(String modified) {
        setModified(modified);
        return this;
    }

    @Override
    public Media withModifiedGMT(String modifiedGMT) {
        setModifiedGMT(modifiedGMT);
        return this;
    }

    @Override
    public Media withSlug(String slug) {
        setSlug(slug);
        return this;
    }

    @Override
    public Media withType(String type) {
        setType(type);
        return this;
    }

    @Override
    public Media withLink(String link) {
        setLink(link);
        return this;
    }

    @Override
    public Media withTitle(String title) {
        WPGeneric generic = new WPGeneric();
        generic.setRendered(title);
        setTitle(generic);
        return this;
    }

    @Override
    public Media withAuthor(int author) {
        setAuthor(author);
        return this;
    }

    @Override
    public Media withCommentStatus(WPStatus commentStatus) {
        setCommentStatus(commentStatus);
        return this;
    }

    @Override
    public Media withPingStatus(WPStatus pingStatus) {
        setPingStatus(pingStatus);
        return this;
    }

    @Override
    public Media withLinks(ArrayList<Link> links) {
        return this;
    }

    @Override
    public Media withLink(Link link) {
        return this;
    }

    public Media() {
    }

    public Media(Parcel in) {
        super(in);
        altText = in.readString();
        caption = in.readString();
        description = in.readString();
        mediaType = in.readString();
        postId = in.readLong();
        sourceUrl = in.readString();
        mediaDetails = in.readParcelable(MediaDetails.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);

        dest.writeString(altText);
        dest.writeString(caption);
        dest.writeString(description);
        dest.writeString(mediaType);
        dest.writeLong(postId);
        dest.writeString(sourceUrl);
        dest.writeParcelable(mediaDetails, flags);
    }

    public static Map<String, Object> mapFromFields(Media media) {
        Map<String, Object> builder = new HashMap<>();

        WPObject.mapFromFields(media, builder);

        Validate.validateMapEntry(JSON_FIELD_ALT_TEXT, media.getAltText(), builder);
        Validate.validateMapEntry(JSON_FIELD_CAPTION, media.getCaption(), builder);
        Validate.validateMapEntry(JSON_FIELD_DESCRIPTION, media.getDescription(), builder);
        Validate.validateMapEntry(JSON_FIELD_MEDIA_TYPE, media.getMediaType(), builder);
        Validate.validateMapEntry(JSON_FIELD_POST, media.getPostId(), builder);
        Validate.validateMapEntry(JSON_FIELD_SOURCE_URL, media.getSourceUrl(), builder);
        Validate.validateMapEntry(JSON_FIELD_MEDIA_DETAILS, media.getMediaDetails(), builder);

        return builder;
    }

    @Override
    public int describeContents() {
        return super.describeContents();
    }

    public static final Parcelable.Creator<Media> CREATOR = new Creator<Media>() {
        @Override
        public Media createFromParcel(Parcel source) {
            return new Media(source);
        }

        @Override
        public Media[] newArray(int size) {
            return new Media[size];
        }
    };
}
