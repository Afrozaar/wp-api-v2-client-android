package com.afrozaar.wp_api_v2_client_android.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * @author Jan-Louis Crafford
 *         Created on 2016/01/07.
 */
public class MediaDetails implements Parcelable {

    @SerializedName("width")
    private int mWidth;

    @SerializedName("height")
    private int mHeight;

    @SerializedName("file")
    private String mFile;

    // TODO add 'sizes' and 'image_meta' data

    public MediaDetails() {
    }

    public MediaDetails(Parcel in) {
        mWidth = in.readInt();
        mHeight = in.readInt();
        mFile = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mWidth);
        dest.writeInt(mHeight);
        dest.writeString(mFile);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<MediaDetails> CREATOR = new Creator<MediaDetails>() {
        @Override
        public MediaDetails createFromParcel(Parcel source) {
            return new MediaDetails(source);
        }

        @Override
        public MediaDetails[] newArray(int size) {
            return new MediaDetails[size];
        }
    };

}
