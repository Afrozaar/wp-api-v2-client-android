package com.afrozaar.wp_api_v2_client_android.model.wp_v1;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Jan-Louis Crafford
 *         Created on 2016/01/14.
 */
public class Comment implements Parcelable {

    /**
     * Unique identifier for the object.
     */
    @SerializedName("id")
    private long mId;

    public void setId(long id) {
        mId = id;
    }

    public long getId() {
        return mId;
    }

    public Comment withId(long id) {
        setId(id);
        return this;
    }

    /**
     * The id of the user object, if author was a user.
     */
    @SerializedName("author")
    private int mAuthor;

    public void setAuthor(int author) {
        mAuthor = author;
    }

    public int getAuthor() {
        return mAuthor;
    }

    public Comment withAuthor(int author) {
        setAuthor(author);
        return this;
    }

    /**
     * Avatar URLs for the object author.
     */
    @SerializedName("author_avatar_urls")
    private Map<String, String> mAuthorAvatarUrls = new HashMap<>();

    public void setAuthorAvatarUrls(Map<String, String> map) {
        mAuthorAvatarUrls = map;
    }

    public void addAuthorAvatarUrl(String key, String value) {
        mAuthorAvatarUrls.put(key, value);
    }

    public Map<String, String> getAuthorAvatarUrls() {
        return mAuthorAvatarUrls;
    }

    public String getAuthorAvatarUrl(String key) {
        return mAuthorAvatarUrls.get(key);
    }

    public Comment withAuthorAvatarUrls(Map<String, String> map) {
        setAuthorAvatarUrls(map);
        return this;
    }

    public Comment withAuthorAvatarUrl(String key, String value) {
        addAuthorAvatarUrl(key, value);
        return this;
    }

    /**
     * Email address for the object author.
     */
    @SerializedName("author_email")
    private String mAuthorEmail;

    public void setAuthorEmail(String email) {
        mAuthorEmail = email;
    }

    public String getAuthorEmail() {
        return mAuthorEmail;
    }

    public Comment withAuthorEmail(String email) {
        setAuthorEmail(email);
        return this;
    }

    /**
     * IP address for the object author.
     */
    @SerializedName("author_ip")
    private String mAuthorIp;

    public void setAuthorIp(String authorIp) {
        mAuthorIp = authorIp;
    }

    public String getAuthorIp() {
        return mAuthorIp;
    }

    public Comment withAuthorIp(String authorIp) {
        setAuthorIp(authorIp);
        return this;
    }

    /**
     * Display name for the object author.
     */
    @SerializedName("author_name")
    private String mAuthorName;

    public void setAuthorName(String authorName) {
        mAuthorName = authorName;
    }

    public String getAuthorName() {
        return mAuthorName;
    }

    public Comment withAuthorName(String authorName) {
        setAuthorName(authorName);
        return this;
    }

    /**
     * URL for the object author.
     */
    @SerializedName("author_url")
    private String mAuthorUrl;

    public void setAuthorUrl(String authorUrl) {
        mAuthorUrl = authorUrl;
    }

    public String getAuthorUrl() {
        return mAuthorUrl;
    }

    public Comment withAuthorUrl(String authorUrl) {
        setAuthorUrl(authorUrl);
        return this;
    }

    /**
     * User agent for the object author.
     */
    @SerializedName("author_user_agent")
    private String mAuthorUserAgent;

    public void setAuthorUserAgent(String authorUserAgent) {
        mAuthorUserAgent = authorUserAgent;
    }

    public String getAuthorUserAgent() {
        return mAuthorUserAgent;
    }

    public Comment withAuthorUserAgent(String authorUserAgent) {
        setAuthorUserAgent(authorUserAgent);
        return this;
    }

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

    public Comment withContent(WPGeneric content) {
        setContent(content);
        return this;
    }

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

    public Comment withDate(String date) {
        setDate(date);
        return this;
    }

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

    public Comment withDateGMT(String dateGMT) {
        setDateGMT(dateGMT);
        return this;
    }

    /**
     * Karma for the object.
     */
    @SerializedName("karma")
    private int mKarma;

    public void setKarma(int karma) {
        mKarma = karma;
    }

    public int getKarma() {
        return mKarma;
    }

    public Comment withKarma(int karma) {
        setKarma(karma);
        return this;
    }

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

    public Comment withLink(String link) {
        setLink(link);
        return this;
    }

    /**
     * The id for the parent of the object.
     */
    @SerializedName("parent")
    private int mParent;

    public void setParent(int parent) {
        mParent = parent;
    }

    public int getParent() {
        return mParent;
    }

    public Comment withParent(int parent) {
        setParent(parent);
        return this;
    }

    /**
     * The id of the associated post object.
     */
    @SerializedName("post")
    private long mPost;

    public void setPost(long post) {
        mPost = post;
    }

    public long getPost() {
        return mPost;
    }

    public Comment withPost(long post) {
        setPost(post);
        return this;
    }

    /**
     * State of the object.
     */
    @SerializedName("status")
    private String mStatus;

    public void setStatus(String status) {
        mStatus = status;
    }

    public String getStatus() {
        return mStatus;
    }

    public Comment withStatus(String status) {
        setStatus(status);
        return this;
    }

    /**
     * Type of Comment for the object.
     */
    @SerializedName("type")
    private String mType;

    public void setType(String type) {
        mType = type;
    }

    public String getType() {
        return mType;
    }

    public Comment withType(String type) {
        setType(type);
        return this;
    }

    public Comment() {
    }

    public Comment(Parcel in) {
        mId = in.readLong();
        mAuthor = in.readInt();
        in.readMap(mAuthorAvatarUrls, String.class.getClassLoader());
        mAuthorEmail = in.readString();
        mAuthorIp = in.readString();
        mAuthorName = in.readString();
        mAuthorUrl = in.readString();
        mAuthorUserAgent = in.readString();
        mContent = in.readParcelable(WPGeneric.class.getClassLoader());
        mDate = in.readString();
        mDateGMT = in.readString();
        mKarma = in.readInt();
        mLink = in.readString();
        mParent = in.readInt();
        mPost = in.readInt();
        mStatus = in.readString();
        mType = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(mId);
        dest.writeInt(mAuthor);
        dest.writeMap(mAuthorAvatarUrls);
        dest.writeString(mAuthorEmail);
        dest.writeString(mAuthorIp);
        dest.writeString(mAuthorName);
        dest.writeString(mAuthorUrl);
        dest.writeString(mAuthorUserAgent);
        dest.writeParcelable(mContent, flags);
        dest.writeString(mDate);
        dest.writeString(mDateGMT);
        dest.writeInt(mKarma);
        dest.writeString(mLink);
        dest.writeInt(mParent);
        dest.writeLong(mPost);
        dest.writeString(mStatus);
        dest.writeString(mType);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<Comment> CREATOR = new Creator<Comment>() {
        @Override
        public Comment createFromParcel(Parcel source) {
            return new Comment(source);
        }

        @Override
        public Comment[] newArray(int size) {
            return new Comment[size];
        }
    };

    @Override
    public String toString() {
        return "Comment{" +
                "mId=" + mId +
                ", mAuthor=" + mAuthor +
                ", mAuthorAvatarUrls=" + mAuthorAvatarUrls +
                ", mAuthorEmail='" + mAuthorEmail + '\'' +
                ", mAuthorIp='" + mAuthorIp + '\'' +
                ", mAuthorName='" + mAuthorName + '\'' +
                ", mAuthorUrl='" + mAuthorUrl + '\'' +
                ", mAuthorUserAgent='" + mAuthorUserAgent + '\'' +
                ", mContent=" + mContent +
                ", mDate='" + mDate + '\'' +
                ", mDateGMT='" + mDateGMT + '\'' +
                ", mKarma=" + mKarma +
                ", mLink='" + mLink + '\'' +
                ", mParent=" + mParent +
                ", mPost=" + mPost +
                ", mStatus='" + mStatus + '\'' +
                ", mType='" + mType + '\'' +
                '}';
    }
}
