package com.afrozaar.wp_api_v2_client_android.util;

import android.content.Context;
import android.text.TextUtils;

import com.afrozaar.athena.utils.MimeUtil;
import com.afrozaar.wp_api_v2_client_android.R;
import com.afrozaar.wp_api_v2_client_android.model.Media;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * @author Jan-Louis Crafford
 *         Created on 2016/01/08.
 */
public class ContentUtil {

    public static String getContentVideoShortcode(Context context, String host, String filePathOnBucket) {
        return context.getString(R.string.content_video_shortcode, host, filePathOnBucket);
    }

    public static String getContentLocationShortcode(Context context, String address) {
        return context.getString(R.string.content_location, address);
    }

    public static String getContentAudioLink(Context context, String host, String filePathOnBucket) {
        return context.getString(R.string.content_audio_uri, host, filePathOnBucket);
    }

    /**
     * Helper method to construct Map used to upload Media item to WordPress.
     *
     * @param media Media item details
     * @param file  File to upload
     * @return Map containing all relevant Media info needed for upload
     */
    public static Map<String, RequestBody> makeMediaItemUploadMap(Media media, File file) {
        Map<String, RequestBody> map = new HashMap<>();

        String fileName;
        if (media.getCaption() != null && !TextUtils.isEmpty(media.getCaption().getRaw())) {
            int extStart = file.getName().lastIndexOf(".");
            String ext = file.getName().substring(extStart);

            String sanitized = media.getCaption().getRaw().replaceAll("[^[a-z][A-Z][0-9][.]]", "_");
            fileName = sanitized + ext;
        } else {
            fileName = file.getName();
        }

        map.put(Media.JSON_FIELD_TITLE, toRequestBody(fileName));
        if (media.getCaption() != null && !TextUtils.isEmpty(media.getCaption().getRaw())) {
            map.put(Media.JSON_FIELD_CAPTION, toRequestBody(media.getCaption().getRaw()));
        }
        if (Validate.notNull(media.getAltText())) {
            map.put(Media.JSON_FIELD_ALT_TEXT, toRequestBody(media.getAltText()));
        }
        if (media.getDescription() != null && !TextUtils.isEmpty(media.getDescription().getRaw())) {
            map.put(Media.JSON_FIELD_DESCRIPTION, toRequestBody(media.getDescription().getRaw()));
        }
        if (media.getPostId() != -1) {
            map.put(Media.JSON_FIELD_POST, toRequestBody(media.getPostId() + ""));
        }

        String mimeType = MimeUtil.getImageMimeType(file.getName());
        RequestBody fileBody = RequestBody.create(MediaType.parse(mimeType), file);

        map.put("file\"; filename=\"" + fileName + "\"", fileBody);

        return map;
    }

    private static RequestBody toRequestBody(String value) {
        return RequestBody.create(MediaType.parse("text/plain"), value);
    }
}
