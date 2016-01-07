package com.afrozaar.wp_api_v2_client_android.model.wp_v1;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

/**
 * @author Jan-Louis Crafford
 *         Created on 2015/12/03.
 */
public abstract class WPObject<T extends WPObject> implements Parcelable {

    /**
     * Unique identifier for the object
     */
    @SerializedName("id")
    private int mId;

    public void setId(int id) {
        mId = id;
    }

    public int getId() {
        return mId;
    }

    public abstract T withId(int id);

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

    public abstract T withTitle(WPGeneric title);

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

    public WPObject() {
    }

    public WPObject(Parcel in) {
        mId = in.readInt();
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
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mId);
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
                '}';
    }
}
