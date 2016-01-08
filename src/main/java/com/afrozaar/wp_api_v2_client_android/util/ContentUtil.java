package com.afrozaar.wp_api_v2_client_android.util;

import android.content.Context;

import com.afrozaar.wp_api_v2_client_android.R;

/**
 * @author Jan-Louis Crafford
 *         Created on 2016/01/08.
 */
public class ContentUtil {

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
}
