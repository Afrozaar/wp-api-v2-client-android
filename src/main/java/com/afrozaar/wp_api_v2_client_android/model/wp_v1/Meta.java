package com.afrozaar.wp_api_v2_client_android.model.wp_v1;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * @author Jan-Louis Crafford
 *         Created on 2016/01/13.
 */
public class Meta implements Parcelable {

    public static final String JSON_FIELD_ID = "id";
    public static final String JSON_FIELD_KEY = "key";
    public static final String JSON_FIELD_VALUE = "value";

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

    public Meta withId(long id) {
        setId(id);
        return this;
    }

    /**
     * The key for the custom field.
     */
    @SerializedName("key")
    private String mKey;

    public void setKey(String key) {
        mKey = key;
    }

    public String getKey() {
        return mKey;
    }

    public Meta withKey(String key) {
        setKey(key);
        return this;
    }

    /**
     * The value of the custom field.
     */
    @SerializedName("value")
    private String mValue;

    public void setValue(String value) {
        mValue = value;
    }

    public String getValue() {
        return mValue;
    }

    public Meta withValue(String value) {
        setValue(value);
        return this;
    }

    public Meta() {
    }

    public Meta(Parcel in) {
        mId = in.readLong();
        mKey = in.readString();
        mValue = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(mId);
        dest.writeString(mKey);
        dest.writeString(mValue);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static Parcelable.Creator<Meta> CREATOR = new Creator<Meta>() {
        @Override
        public Meta createFromParcel(Parcel source) {
            return new Meta(source);
        }

        @Override
        public Meta[] newArray(int size) {
            return new Meta[size];
        }
    };

    @Override
    public String toString() {
        return "Meta{" +
                "mId=" + mId +
                ", mKey='" + mKey + '\'' +
                ", mValue='" + mValue + '\'' +
                '}';
    }
}
