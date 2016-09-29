package com.afrozaar.wp_api_v2_client_android.model.dto;

import android.graphics.Bitmap;

import com.afrozaar.wp_api_v2_client_android.model.Comment;

import java.util.List;

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
    public String author_name;
    public String status;
    public int featured_media;
    public String media_url;

    public String image_count;
    public String video_count;
    public String audio_count;
    public String location_count;

    // extra downloaded fields
    public Bitmap authorImage;
    public int commentCount;

    public List<Comment> comments;

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
