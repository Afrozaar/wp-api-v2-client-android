package com.afrozaar.wp_api_v2_client_android.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Jan-Louis Crafford
 *         Created on 2015/12/03.
 */
public class Link implements Parcelable {

    private String mTitle;

    private String mHref;

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public String getHref() {
        return mHref;
    }

    public void setHref(String href) {
        this.mHref = href;
    }

    public Link() {
    }

    public Link(Parcel in) {
        mTitle = in.readString();
        mHref = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mTitle);
        dest.writeString(mHref);
    }

    public static Parcelable.Creator<Link> CREATOR = new Creator<Link>() {
        @Override
        public Link createFromParcel(Parcel source) {
            return new Link(source);
        }

        @Override
        public Link[] newArray(int size) {
            return new Link[size];
        }
    };

    @Override
    public String toString() {
        return "Link{" +
                "mTitle='" + mTitle + '\'' +
                ", mHref='" + mHref + '\'' +
                '}';
    }
}
