package com.afrozaar.wp_api_v2_client_android.model.wp_v1;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Jan-Louis Crafford
 *         Created on 2016/01/07.
 */
public class User implements Parcelable {

    /**
     * Avatar URLs for the object.
     */
    @JsonAdapter(AvatarUrlsDeserializer.class)
    @SerializedName("avatar_urls")
    private Map<String, String> mAvatarUrls = new HashMap<>();

    public void setAvatarUrls(Map<String, String> map) {
        mAvatarUrls = map;
    }

    public void addAvatarUrl(String key, String value) {
        mAvatarUrls.put(key, value);
    }

    public Map<String, String> getAvatarUrls() {
        return mAvatarUrls;
    }

    public User withAvatarUrls(Map<String, String> map) {
        setAvatarUrls(map);
        return this;
    }

    public User withAvatarUrl(String key, String value) {
        addAvatarUrl(key, value);
        return this;
    }


    /**
     * All capabilities assigned to the user.
     */
    //@SerializedName("capabilities")
    //private Object mCapabilities;


    /**
     * Description of the object.
     */
    @SerializedName("description")
    private String mDescription;

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getDescription() {
        return mDescription;
    }

    public User withDescription(String description) {
        setDescription(description);
        return this;
    }

    /**
     * The email address for the object.
     */
    @SerializedName("email")
    private String mEmail;

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getEmail() {
        return mEmail;
    }

    public User withEmail(String email) {
        setEmail(email);
        return this;
    }


    /**
     * Any extra capabilities assigned to the user.
     */
    //@SerializedName("extra_capabilities")
    //private Object mExtraCapabilities;


    /**
     * First name for the object.
     */
    @SerializedName("first_name")
    private String mFirstName;

    public void setFirstName(String firstName) {
        mFirstName = firstName;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public User withFirstName(String firstName) {
        setFirstName(firstName);
        return this;
    }

    /**
     * Unique identifier for the object.
     */
    @SerializedName("id")
    private int mId;

    public void setId(int id) {
        mId = id;
    }

    public int getId() {
        return mId;
    }

    public User withId(int id) {
        setId(id);
        return this;
    }

    /**
     * Last name for the object.
     */
    @SerializedName("last_name")
    private String mLastName;

    public void setLastName(String lastName) {
        mLastName = lastName;
    }

    public String getLastName() {
        return mLastName;
    }

    public User withLastName(String lastName) {
        setLastName(lastName);
        return this;
    }

    /**
     * Author URL to the object.
     */
    @SerializedName("link")
    private String mLink;

    public void setLink(String link) {
        mLink = link;
    }

    public String getLink() {
        return mLink;
    }

    public User withLink(String link) {
        setLink(link);
        return this;
    }

    /**
     * Display name for the object.
     */
    @SerializedName("name")
    private String mName;

    public void setName(String name) {
        mName = name;
    }

    public String getName() {
        return mName;
    }

    public User withName(String name) {
        setName(name);
        return this;
    }

    /**
     * The nickname for the object.
     */
    @SerializedName("nickname")
    private String mNickName;

    public void setNickName(String nickName) {
        mNickName = nickName;
    }

    public String getNickName() {
        return mNickName;
    }

    public User withNickName(String nickName) {
        setNickName(nickName);
        return this;
    }

    /**
     * Registration date for the user.
     */
    @SerializedName("registered_date")
    private String mRegisteredDate;

    public void setRegisteredDate(String date) {
        mRegisteredDate = date;
    }

    public String getRegisteredDate() {
        return mRegisteredDate;
    }

    public User withRegisteredDate(String date) {
        setRegisteredDate(date);
        return this;
    }

    /**
     * Roles assigned to the user.
     */
    @SerializedName("roles")
    private List<String> mRoles = new ArrayList<>();

    public void setRoles(List<String> roles) {
        mRoles = roles;
    }

    public List<String> getRoles() {
        return mRoles;
    }

    public User withRoles(List<String> roles) {
        setRoles(roles);
        return this;
    }

    /**
     * Role assigned to the user.
     */
    @SerializedName("role")
    private String mRole;

    public void setRole(String role) {
        mRole = role;
    }

    public String getRole() {
        return mRole;
    }

    public User withRole(String role) {
        setRole(role);
        return this;
    }

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

    public User withSlug(String slug) {
        setSlug(slug);
        return this;
    }

    /**
     * URL of the object.
     */
    @SerializedName("url")
    private String mUrl;

    public void setUrl(String url) {
        mUrl = url;
    }

    public String getUrl() {
        return mUrl;
    }

    public User withUrl(String url) {
        setUrl(url);
        return this;
    }

    /**
     * Login name for the user.
     */
    @SerializedName("username")
    private String mUserName;

    public void setUserName(String userName) {
        mUserName = userName;
    }

    public String getUserName() {
        return mUserName;
    }

    public User withUserName(String userName) {
        setUserName(userName);
        return this;
    }

    /**
     * Login password for the user.
     */
    @SerializedName("password")
    private String mPassword;

    public void setPassword(String password) {
        mPassword = password;
    }

    public String getPassword() {
        return mPassword;
    }

    public User withPassword(String password) {
        setPassword(password);
        return this;
    }

    /**
     * Links for this post; author, attachments, history, etc.
     */
    @JsonAdapter(LinksDeserializer.class)
    @SerializedName("_links")
    private ArrayList<Link> mLinks = new ArrayList<>();

    public void setLinks(ArrayList<Link> links) {
        mLinks = links;
    }

    public ArrayList<Link> getLinks() {
        return mLinks;
    }

    public User withLinks(ArrayList<Link> links) {
        setLinks(links);
        return this;
    }

    public User() {
    }

    public User(Parcel in) {
        in.readMap(mAvatarUrls, String.class.getClassLoader());
        //in.readParcelable(mCapabilities);
        mDescription = in.readString();
        mEmail = in.readString();
        //in.readParcelable(mExtraCapabilities);
        mFirstName = in.readString();
        mId = in.readInt();
        mLastName = in.readString();
        mLink = in.readString();
        mName = in.readString();
        mNickName = in.readString();
        mRegisteredDate = in.readString();
        in.readStringList(mRoles);
        mRole = in.readString();
        mSlug = in.readString();
        mUrl = in.readString();
        mUserName = in.readString();
        mPassword = in.readString();
        in.readTypedList(mLinks, Link.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeMap(mAvatarUrls);
        //dest.writeParcelable(mCapabilities, flags);
        dest.writeString(mDescription);
        dest.writeString(mEmail);
        //dest.writeParcelable(mExtraCapabilities, flags);
        dest.writeString(mFirstName);
        dest.writeInt(mId);
        dest.writeString(mLastName);
        dest.writeString(mLink);
        dest.writeString(mName);
        dest.writeString(mNickName);
        dest.writeString(mRegisteredDate);
        dest.writeStringList(mRoles);
        dest.writeString(mRole);
        dest.writeString(mSlug);
        dest.writeString(mUrl);
        dest.writeString(mUserName);
        dest.writeString(mPassword);
        dest.writeTypedList(mLinks);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
