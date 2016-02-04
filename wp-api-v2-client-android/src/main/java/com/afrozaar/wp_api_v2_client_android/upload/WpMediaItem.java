package com.afrozaar.wp_api_v2_client_android.upload;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Jan-Louis Crafford
 *         Created on 2016/02/03.
 */
public class WpMediaItem implements Parcelable {

    public Uri mediaPath;

    public String caption;

    public WpMediaItem() {
    }

    public WpMediaItem(Parcel in) {
        mediaPath = in.readParcelable(Uri.class.getClassLoader());
        caption = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(mediaPath, flags);
        dest.writeString(caption);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<WpMediaItem> CREATOR = new Creator<WpMediaItem>() {
        @Override
        public WpMediaItem createFromParcel(Parcel in) {
            return new WpMediaItem(in);
        }

        @Override
        public WpMediaItem[] newArray(int size) {
            return new WpMediaItem[size];
        }
    };

    @Override
    public boolean equals(Object o) {
        return o instanceof WpMediaItem && mediaPath.equals(((WpMediaItem) o).mediaPath);
    }
}
