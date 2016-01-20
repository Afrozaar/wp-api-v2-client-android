package com.afrozaar.wp_api_v2_client_android.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.afrozaar.wp_api_v2_client_android.util.Validate;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Map;

/**
 * @author Jan-Louis Crafford
 *         Created on 2015/12/03.
 */
public abstract class WPObject<T extends WPObject> implements Parcelable {

    public static final String JSON_FIELD_ID = "id";
    public static final String JSON_FIELD_DATE = "date";
    public static final String JSON_FIELD_DATE_GMT = "date_gmt";
    public static final String JSON_FIELD_GUID = "guid";
    public static final String JSON_FIELD_MODIFIED = "modified";
    public static final String JSON_FIELD_MODIFIED_GMT = "modified_gmt";
    public static final String JSON_FIELD_SLUG = "slug";
    public static final String JSON_FIELD_TYPE = "type";
    public static final String JSON_FIELD_LINK = "link";
    public static final String JSON_FIELD_TITLE = "title";
    public static final String JSON_FIELD_AUTHOR = "author";
    public static final String JSON_FIELD_COMMENT_STATUS = "comment_status";
    public static final String JSON_FIELD_PING_STATUS = "ping_status";
    public static final String JSON_FIELD_LINKS = "_links";

    /**
     * Unique identifier for the object
     */
    @SerializedName("id")
    private long mId;

    public void setId(long id) {
        mId = id;
    }

    public long getId() {
        return mId;
    }

    public abstract T withId(long id);

    /**
     * The date the object was published.
     */
    @SerializedName("date")
    private String mDate;

    public void setDate(String date) {
        mDate = date;
    }

    public String getDate() {
        return mDate;
    }

    public abstract T withDate(String date);

    /**
     * The date the object was published, as GMT.
     */
    @SerializedName("date_gmt")
    private String mDateGMT;

    public void setDateGMT(String dateGMT) {
        mDateGMT = dateGMT;
    }

    public String getDateGMT() {
        return mDateGMT;
    }

    public abstract T withDateGMT(String dateGMT);

    /**
     * The globally unique identifier for the object.
     */
    @SerializedName("guid")
    private WPGeneric mGuid;

    public void setGuid(WPGeneric guid) {
        mGuid = guid;
    }

    public WPGeneric getGuid() {
        return mGuid;
    }

    public abstract T withGuid(WPGeneric guid);

    /**
     * The date the object was last modified.
     */
    @SerializedName("modified")
    private String mModified;

    public void setModified(String modified) {
        mModified = modified;
    }

    public String getModified() {
        return mModified;
    }

    public abstract T withModified(String modified);

    /**
     * The date the object was last modified, as GMT.
     */
    @SerializedName("modified_gmt")
    private String mModifiedGMT;

    public void setModifiedGMT(String modifiedGMT) {
        mModifiedGMT = modifiedGMT;
    }

    public String getModifiedGMT() {
        return mModifiedGMT;
    }

    public abstract T withModifiedGMT(String modifiedGMT);

    /**
     * An alphanumeric identifier for the object unique to its type.
     */
    @SerializedName("slug")
    private String mSlug;

    public void setSlug(String slug) {
        mSlug = slug;
    }

    public String getSlug() {
        return mSlug;
    }

    public abstract T withSlug(String slug);

    /**
     * Type of Post for the object.
     */
    @SerializedName("type")
    private String mType;

    public void setType(String type) {
        mType = type;
    }

    public String getType() {
        return mType;
    }

    public abstract T withType(String type);

    /**
     * URL to the object.
     */
    @SerializedName("link")
    private String mLink;

    public void setLink(String link) {
        mLink = link;
    }

    public String getLink() {
        return mLink;
    }

    public abstract T withLink(String link);

    /**
     * The title for the object.
     */
    @SerializedName("title")
    private WPGeneric mTitle;

    public void setTitle(WPGeneric title) {
        mTitle = title;
    }

    public WPGeneric getTitle() {
        return mTitle;
    }

    public abstract T withTitle(String title);

    /**
     * The ID for the author of the object.
     */
    @SerializedName("author")
    private int mAuthor;

    public void setAuthor(int author) {
        mAuthor = author;
    }

    public int getAuthor() {
        return mAuthor;
    }

    public abstract T withAuthor(int author);

    /**
     * Whether or not comments are open on the object.
     */
    @JsonAdapter(StatusDeserializer.class)
    @SerializedName("comment_status")
    private WPStatus mCommentStatus;

    public void setCommentStatus(WPStatus commentStatus) {
        mCommentStatus = commentStatus;
    }

    public WPStatus getCommentStatus() {
        return mCommentStatus;
    }

    public abstract T withCommentStatus(WPStatus commentStatus);

    /**
     * Whether or not the object can be pinged.
     */
    @JsonAdapter(StatusDeserializer.class)
    @SerializedName("ping_status")
    private WPStatus mPingStatus;

    public void setPingStatus(WPStatus pingStatus) {
        mPingStatus = pingStatus;
    }

    public WPStatus getPingStatus() {
        return mPingStatus;
    }

    public abstract T withPingStatus(WPStatus pingStatus);

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

    public abstract T withLinks(ArrayList<Link> links);

    public abstract T withLink(Link link);

    public WPObject() {
    }

    public WPObject(Parcel in) {
        mId = in.readLong();
        mDate = in.readString();
        mDateGMT = in.readString();
        mGuid = in.readParcelable(WPGeneric.class.getClassLoader());
        mModified = in.readString();
        mModifiedGMT = in.readString();
        mSlug = in.readString();
        mType = in.readString();
        mLink = in.readString();
        mTitle = in.readParcelable(WPGeneric.class.getClassLoader());
        mAuthor = in.readInt();
        mCommentStatus = in.readParcelable(WPStatus.class.getClassLoader());
        mPingStatus = in.readParcelable(WPStatus.class.getClassLoader());
        in.readTypedList(mLinks, Link.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(mId);
        dest.writeString(mDate);
        dest.writeString(mDateGMT);
        dest.writeParcelable(mGuid, flags);
        dest.writeString(mModified);
        dest.writeString(mModifiedGMT);
        dest.writeString(mSlug);
        dest.writeString(mType);
        dest.writeString(mLink);
        dest.writeParcelable(mTitle, flags);
        dest.writeInt(mAuthor);
        dest.writeParcelable(mCommentStatus, flags);
        dest.writeParcelable(mPingStatus, flags);
        dest.writeTypedList(mLinks);
    }

    public static <T extends WPObject> Map<String, Object> mapFromFields(WPObject<T> wpObject, Map<String, Object> builder) {
        Validate.validateMapEntry(JSON_FIELD_ID, wpObject.getId(), builder);
        Validate.validateMapEntry(JSON_FIELD_DATE, wpObject.getDate(), builder);
        Validate.validateMapEntry(JSON_FIELD_DATE_GMT, wpObject.getDateGMT(), builder);
        if (wpObject.getGuid() != null) {
            Validate.validateMapEntry(JSON_FIELD_GUID, wpObject.getGuid().getRendered(), builder);
        }
        Validate.validateMapEntry(JSON_FIELD_MODIFIED, wpObject.getModified(), builder);
        Validate.validateMapEntry(JSON_FIELD_MODIFIED_GMT, wpObject.getModifiedGMT(), builder);
        Validate.validateMapEntry(JSON_FIELD_SLUG, wpObject.getSlug(), builder);
        Validate.validateMapEntry(JSON_FIELD_TYPE, wpObject.getType(), builder);
        Validate.validateMapEntry(JSON_FIELD_LINK, wpObject.getLink(), builder);
        if (wpObject.getTitle() != null) {
            Validate.validateMapEntry(JSON_FIELD_TITLE, wpObject.getTitle().getRendered(), builder);
        }
        Validate.validateMapEntry(JSON_FIELD_AUTHOR, wpObject.getAuthor(), builder);
        if (wpObject.getCommentStatus() != null) {
            Validate.validateMapEntry(JSON_FIELD_COMMENT_STATUS, wpObject.getCommentStatus().getStatus(), builder);
        }
        if (wpObject.getPingStatus() != null) {
            Validate.validateMapEntry(JSON_FIELD_PING_STATUS, wpObject.getPingStatus().getStatus(), builder);
        }

        return builder;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public String toString() {
        return "WPObject{" +
                "mId=" + mId +
                "mDate='" + mDate + '\'' +
                ", mDateGMT='" + mDateGMT + '\'' +
                ", mGuid=" + mGuid +
                ", mModified='" + mModified + '\'' +
                ", mModifiedGMT='" + mModifiedGMT + '\'' +
                ", mSlug='" + mSlug + '\'' +
                ", mType='" + mType + '\'' +
                ", mLink='" + mLink + '\'' +
                ", mTitle=" + mTitle +
                ", mAuthor=" + mAuthor +
                ", mCommentStatus=" + mCommentStatus +
                ", mPingStatus=" + mPingStatus +
                ", mLinks=" + mLinks +
                '}';
    }
}
