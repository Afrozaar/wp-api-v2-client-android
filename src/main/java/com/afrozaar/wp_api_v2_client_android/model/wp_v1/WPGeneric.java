package com.afrozaar.wp_api_v2_client_android.model.wp_v1;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Generic class for holding "rendered" fields returned by the JSON API
 *
 * @author Jan-Louis Crafford
 *         Created on 2015/12/03.
 */
public class WPGeneric implements Parcelable {

    @SerializedName("rendered")
    private String mRendered;

    public String getRendered() {
        return mRendered;
    }

    public void setRendered(String rendered) {
        this.mRendered = rendered;
    }

    public WPGeneric withRendered(String rendered) {
        mRendered = rendered;
        return this;
    }

    public WPGeneric() {
    }

    public WPGeneric(Parcel in) {
        mRendered = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mRendered);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<WPGeneric> CREATOR = new Creator<WPGeneric>() {
        @Override
        public WPGeneric createFromParcel(Parcel source) {
            return new WPGeneric(source);
        }

        @Override
        public WPGeneric[] newArray(int size) {
            return new WPGeneric[size];
        }
    };

    @Override
    public String toString() {
        return "WPGeneric{" +
                "mRendered='" + mRendered + '\'' +
                '}';
    }
}
