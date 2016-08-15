package com.afrozaar.wp_api_v2_client_android.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Model used for local Media items
 *
 * @author Jan-Louis Crafford
 *         Created on 2016/03/24.
 */
public class MediaItem extends BaseModel {

    public static final String TYPE_IMAGE = "image";
    public static final String TYPE_VIDEO = "video";
    public static final String TYPE_AUDIO = "audio";

    /**
     * Id of Post on WP if uploaded
     */
    public long postId = -1;

    /**
     * Row id of Post object in database
     */
    public long postRowId = -1;

    /**
     * Id of Media item on WP if uploaded
     */
    public long mediaId = -1;

    /**
     * Row id for Media item (_id field)
     */
    public long mediaRowId = -1;

    public String mimeType;
    public String uri;
    public String caption;
    public String externalUrl;
    public int uploadState;

    public MediaItem() {
    }

    public MediaItem(Parcel in) {
        super(in);

        postId = in.readLong();
        postRowId = in.readLong();
        mediaId = in.readLong();
        mediaRowId = in.readLong();
        uri = in.readString();
        caption = in.readString();
        externalUrl = in.readString();
        uploadState = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);

        dest.writeLong(postId);
        dest.writeLong(postRowId);
        dest.writeLong(mediaId);
        dest.writeLong(mediaRowId);
        dest.writeString(uri);
        dest.writeString(caption);
        dest.writeString(externalUrl);
        dest.writeInt(uploadState);
    }

    public String getType() {
        if (mimeType.startsWith("image")) {
            return TYPE_IMAGE;
        } else if (mimeType.startsWith("video")) {
            return TYPE_VIDEO;
        } else if (mimeType.startsWith("audio")) {
            return TYPE_AUDIO;
        }

        throw new IllegalStateException("No type on media item!");
    }

    @Override
    public String toString() {
        return "MediaItem{" +
                "postId=" + postId +
                ", postRowId=" + postRowId +
                ", mediaId=" + mediaId +
                ", uri='" + uri + '\'' +
                ", caption='" + caption + '\'' +
                ", externalUrl='" + externalUrl + '\'' +
                '}';
    }

    public static final Parcelable.Creator<MediaItem> CREATOR = new Creator<MediaItem>() {
        @Override
        public MediaItem createFromParcel(Parcel in) {
            return new MediaItem(in);
        }

        @Override
        public MediaItem[] newArray(int size) {
            return new MediaItem[size];
        }
    };

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MediaItem mediaItem = (MediaItem) o;

        return mimeType.equals(mediaItem.mimeType) && uri.equals(mediaItem.uri);
    }

    @Override
    public int hashCode() {
        int result = mimeType.hashCode();
        result = 31 * result + uri.hashCode();
        return result;
    }
}
