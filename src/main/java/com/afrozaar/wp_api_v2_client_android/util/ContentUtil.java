package com.afrozaar.wp_api_v2_client_android.util;

import android.content.Context;

import com.afrozaar.wp_api_v2_client_android.R;

/**
 * @author Jan-Louis Crafford
 *         Created on 2016/01/08.
 */
public class ContentUtil {

    private static final String VIDEO_TYPE_MP4 = "video/mp4";
    private static final String VIDEO_TYPE_WEBM = "video/webm";
    private static final String VIDEO_TYPE_OGG = "video/ogg";

    private static final String VIDEO_CODECS_MP4 = "avc1.42E01E, mp4a.40.2";
    private static final String VIDEO_CODECS_WEBM = "vp8, vorbis";
    private static final String VIDEO_CODECS_OGG = "theora, vorbis";

    /**
     * +
     * Returns a formatted image link for use in Post body
     *
     * @param context  Application context
     * @param imageUrl Uri for the image
     * @param altText  Alternate text
     * @return Formatted HTML url string
     */
    public static String getContentImageLinkUri(Context context, String imageUrl, String altText) {
        return context.getString(R.string.content_image_uri, imageUrl, altText == null ? "" : altText);
    }

    public static String getContentVideoLinkUri(Context context, String videoUrl) {
        return context.getString(R.string.content_video_uri, videoUrl);
    }

}
