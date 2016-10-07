package com.afrozaar.wp_api_v2_client_android.util;

import android.content.Context;
import android.text.TextUtils;

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

    // Video MIME types
    private static final String MIME_VIDEO_ALL = "video/*";
    private static final String MIME_VIDEO_MP4 = "video/mp4";
    private static final String MIME_VIDEO_WEBM = "video/webm";
    private static final String MIME_VIDEO_OGG = "video/ogg";

    // Video file extensions
    private static final String VIDEO_TYPE_MP4 = "mp4";
    private static final String VIDEO_TYPE_M4V = "m4v";
    private static final String VIDEO_TYPE_OGG = "ogv";
    private static final String VIDEO_TYPE_WEBM = "webm";

    private static final String VIDEO_CODECS_MP4 = "avc1.42E01E, mp4a.40.2";
    private static final String VIDEO_CODECS_WEBM = "vp8, vorbis";
    private static final String VIDEO_CODECS_OGG = "theora, vorbis";

    /* WordPress supported image types:
    *
    *   'jpg|jpeg|jpe' => 'image/jpeg',
	*   'gif' => 'image/gif',
	*   'png' => 'image/png',
	*   'bmp' => 'image/bmp',
	*   'tiff|tif' => 'image/tiff',
	*   'ico' => 'image/x-icon',
    *
    * */

    // Image MIME types
    private static final String MIME_IMAGE_ALL = "image/*";
    private static final String MIME_IMAGE_JPG = "image/jpg";
    private static final String MIME_IMAGE_JPEG = "image/jpeg";
    private static final String MIME_IMAGE_GIF = "image/gif";
    private static final String MIME_IMAGE_PNG = "image/png";
    private static final String MIME_IMAGE_BMP = "image/bmp";
    private static final String MIME_IMAGE_TIFF = "image/tiff";
    private static final String MIME_IMAGE_ICO = "image/x-icon";

    // Image file extensions
    private static final String IMAGE_TYPE_JPG = "jpg";
    private static final String IMAGE_TYPE_JPEG = "jpeg";
    private static final String IMAGE_TYPE_JPE = "jpe";
    private static final String IMAGE_TYPE_GIF = "gif";
    private static final String IMAGE_TYPE_PNG = "png";
    private static final String IMAGE_TYPE_BMP = "bmp";
    private static final String IMAGE_TYPE_TIFF = "tiff";
    private static final String IMAGE_TYPE_TIF = "tif";
    private static final String IMAGE_TYPE_ICO = "ico";

    // Audio MIME types
    private static final String MIME_AUDIO_ALL = "audio/*";
    private static final String MIME_AUDIO_MPEG = "audio/mpeg";
    private static final String MIME_AUDIO_MP4 = "audio/mp4";
    private static final String MIME_AUDIO_OGG = "audio/ogg";
    private static final String MIME_AUDIO_WAV = "audio/vnd.wav";
    private static final String MIME_AUDIO_MID = "audio/mid";

    // Audio file extensions
    private static final String AUDIO_TYPE_MP3 = "mp3";
    private static final String AUDIO_TYPE_MP4 = "mp4";
    private static final String AUDIO_TYPE_M4A = "m4a";
    private static final String AUDIO_TYPE_OGG = "ogg";
    private static final String AUDIO_TYPE_WAV = "wav";
    private static final String AUDIO_TYPE_MID = "mid";

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
     * Returns the MIME type for an image file based on it's extension.
     *
     * @param filepath Name of file to check.
     * @return MIME type for image
     */
    public static String getImageMimeType(String filepath) {
        if (TextUtils.isEmpty(filepath)) {
            return "";
        }

        String fileName;

        String[] urlParts = TextUtils.split(filepath, "/");
        if (urlParts.length > 1) {
            // file path is url
            fileName = urlParts[urlParts.length - 1];
        } else {
            fileName = urlParts[0];
        }

        String[] parts = TextUtils.split(fileName, "\\.");
        if (parts.length < 2) {
            LogUtils.w("Split filename has less than 2 parts=" + fileName);
            return MIME_IMAGE_ALL;
        }

        String ext = parts[1];

        if (TextUtils.equals(ext, IMAGE_TYPE_JPG) || TextUtils.equals(ext, IMAGE_TYPE_JPEG)
                || TextUtils.equals(ext, IMAGE_TYPE_JPE)) {
            return MIME_IMAGE_JPG;
        }

        if (TextUtils.equals(ext, IMAGE_TYPE_GIF)) {
            return MIME_IMAGE_GIF;
        }

        if (TextUtils.equals(ext, IMAGE_TYPE_PNG)) {
            return MIME_IMAGE_PNG;
        }

        if (TextUtils.equals(ext, IMAGE_TYPE_BMP)) {
            return MIME_IMAGE_BMP;
        }

        if (TextUtils.equals(ext, IMAGE_TYPE_TIFF) || TextUtils.equals(ext, IMAGE_TYPE_TIF)) {
            return MIME_IMAGE_TIFF;
        }

        if (TextUtils.equals(ext, IMAGE_TYPE_ICO)) {
            return MIME_IMAGE_ICO;
        }

        return MIME_IMAGE_ALL;
    }

    /**
     * Returns the MIME type for a video file based on it's extension.
     *
     * @param filepath Name of file to check.
     * @return MIME type for video
     */
    public static String getVideoMimeType(String filepath) {
        if (TextUtils.isEmpty(filepath)) {
            return "";
        }

        String fileName;

        String[] urlParts = TextUtils.split(filepath, "/");
        if (urlParts.length > 1) {
            // file path is url
            fileName = urlParts[urlParts.length - 1];
        } else {
            fileName = urlParts[0];
        }

        String[] parts = TextUtils.split(fileName, "\\.");
        if (parts.length < 2) {
            LogUtils.w("Split fileName has less than 2 parts=" + fileName);
            return MIME_VIDEO_ALL;
        }

        String ext = parts[1];

        if (TextUtils.equals(ext, VIDEO_TYPE_M4V) || TextUtils.equals(ext, VIDEO_TYPE_MP4)) {
            return MIME_VIDEO_MP4;
        }

        if (TextUtils.equals(ext, VIDEO_TYPE_OGG)) {
            return MIME_VIDEO_OGG;
        }

        if (TextUtils.equals(ext, VIDEO_TYPE_WEBM)) {
            return MIME_VIDEO_WEBM;
        }

        return MIME_VIDEO_ALL;
    }

    /**
     * Returns the MIME type for an audio file based on it's extension.
     *
     * @param filepath Name of file to check.
     * @return MIME type for audio
     */
    public static String getAudioMimeType(String filepath) {
        if (TextUtils.isEmpty(filepath)) {
            return "";
        }

        String fileName;

        String[] urlParts = TextUtils.split(filepath, "/");
        if (urlParts.length > 1) {
            // file path is url
            fileName = urlParts[urlParts.length - 1];
        } else {
            fileName = urlParts[0];
        }

        String[] parts = TextUtils.split(fileName, "\\.");
        if (parts.length < 2) {
            LogUtils.w("Split filename has less than 2 parts=" + fileName);
            return MIME_AUDIO_ALL;
        }

        String ext = parts[1];

        if (TextUtils.equals(ext, AUDIO_TYPE_MP3)) {
            return MIME_AUDIO_MPEG;
        }

        if (TextUtils.equals(ext, AUDIO_TYPE_MP4) || TextUtils.equals(ext, AUDIO_TYPE_M4A)) {
            return MIME_AUDIO_MP4;
        }

        if (TextUtils.equals(ext, AUDIO_TYPE_MID)) {
            return MIME_AUDIO_MID;
        }

        if (TextUtils.equals(ext, AUDIO_TYPE_OGG)) {
            return MIME_AUDIO_OGG;
        }

        if (TextUtils.equals(ext, AUDIO_TYPE_WAV)) {
            return MIME_AUDIO_WAV;
        }

        return MIME_AUDIO_ALL;
    }

    /**
     * Checks if the given MIME type is a valid video media type.
     *
     * @param type MIME type to check
     * @return True if a valid type
     */
    public static boolean isVideoMedia(String type) {
        return type.equals(MIME_VIDEO_MP4) || type.equals(MIME_VIDEO_OGG) || type.equals(MIME_VIDEO_WEBM);
    }

    /**
     * Checks if given MIME type is a valid image media type.
     *
     * @param type MIME type
     * @return True if type matches a valid image type.
     */
    public static boolean isImageMedia(String type) {
        return type.equals(MIME_IMAGE_BMP) || type.equals(MIME_IMAGE_GIF) || type.equals(MIME_IMAGE_ICO)
                || type.equals(MIME_IMAGE_JPG) || type.equals(MIME_IMAGE_PNG) || type.equals(MIME_IMAGE_TIFF)
                || type.equals(MIME_IMAGE_JPEG);
    }

    /**
     * Checks if the given MIME type is a valid audio type.
     *
     * @param type MIME type
     * @return True if type id valid audio.
     */
    public static boolean isAudioMedia(String type) {
        return type.equals(MIME_AUDIO_MID) || type.equals(MIME_AUDIO_MP4) || type.equals(MIME_AUDIO_MPEG)
                || type.equals(MIME_AUDIO_OGG) || type.equals(MIME_AUDIO_WAV);
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
        if (!TextUtils.isEmpty(media.getCaption())) {
            int extStart = file.getName().lastIndexOf(".");
            String ext = file.getName().substring(extStart);

            String sanitized = media.getCaption().replaceAll("[^[a-z][A-Z][0-9][.]]", "_");
            fileName = sanitized + ext;
        } else {
            fileName = file.getName();
        }

        map.put(Media.JSON_FIELD_TITLE, toRequestBody(fileName));
        if (Validate.notNull(media.getCaption())) {
            map.put(Media.JSON_FIELD_CAPTION, toRequestBody(media.getCaption()));
        }
        if (Validate.notNull(media.getAltText())) {
            map.put(Media.JSON_FIELD_ALT_TEXT, toRequestBody(media.getAltText()));
        }
        if (Validate.notNull(media.getDescription())) {
            map.put(Media.JSON_FIELD_DESCRIPTION, toRequestBody(media.getDescription()));
        }
        if (media.getPostId() != -1) {
            map.put(Media.JSON_FIELD_POST, toRequestBody(media.getPostId() + ""));
        }

        String mimeType = ContentUtil.getImageMimeType(file.getName());
        RequestBody fileBody = RequestBody.create(MediaType.parse(mimeType), file);

        map.put("file\"; filename=\"" + fileName + "\"", fileBody);

        return map;
    }

    private static RequestBody toRequestBody(String value) {
        return RequestBody.create(MediaType.parse("text/plain"), value);
    }
}
