package com.afrozaar.wp_api_v2_client_android.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Jan-Louis Crafford
 *         Created on 2015/12/03.
 */
public class WPStatus implements Parcelable {

    public static final int CLOSED = 0;
    public static final int OPEN = 1;

    private int mStatus;

    public WPStatus() {
    }

    public WPStatus(Parcel in) {
        mStatus = in.readInt();
    }

    public void setStatus(int status) {
        mStatus = status;
    }

    public boolean isClosed() {
        return mStatus == CLOSED;
    }

    public boolean isOpen() {
        return mStatus == OPEN;
    }

    public String getStatus() {
        return isOpen() ? "open" : "closed";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mStatus);
    }

    public static Parcelable.Creator<WPStatus> CREATOR = new Creator<WPStatus>() {
        @Override
        public WPStatus createFromParcel(Parcel source) {
            return new WPStatus(source);
        }

        @Override
        public WPStatus[] newArray(int size) {
            return new WPStatus[size];
        }
    };

    @Override
    public String toString() {
        return "WPStatus{" +
                "mStatus=" + (mStatus == CLOSED ? "closed" : "open") +
                '}';
    }
}
