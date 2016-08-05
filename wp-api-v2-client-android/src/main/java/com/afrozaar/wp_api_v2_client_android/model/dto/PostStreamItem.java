package com.afrozaar.wp_api_v2_client_android.model.dto;

/**
 * @author Jan-Louis Crafford
 *         Created on 2016/08/04.
 */
public class PostStreamItem implements Comparable<PostStreamItem> {

    public long rowId;

    // field returned with initial /post/stream call
    public long id;
    public String date;
    public String modified;
    public String title;
    public int author;
    public int featured_media;

    // extra downloaded fields
    public String authorName;
    public String authorImage;

    public String mediaUrl;

    public int imageCount;
    public int videoCount;
    public int audioCount;
    public int locationCount;

    public int adapterPosition = -1;
    public boolean downloaded = false;

    @Override
    public int compareTo(PostStreamItem another) {
        return date.compareTo(another.date);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PostStreamItem that = (PostStreamItem) o;

        return id == that.id;

    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
