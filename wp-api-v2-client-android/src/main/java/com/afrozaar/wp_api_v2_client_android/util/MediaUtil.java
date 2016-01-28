package com.afrozaar.wp_api_v2_client_android.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Utilities methods for getting a filesystem path from the DocumentProvider URIs.
 * <p/>
 * Created by jay on 11/30/15.
 */
public class MediaUtil {

    public static final String TYPE_IMAGE = "image";
    public static final String TYPE_VIDEO = "video";
    public static final String TYPE_AUDIO = "audio";

    private static final String HOST_EXTERNAL_STORAGE = "com.android.externalstorage.documents";

    public static String getRealPathFromURI(Context context, Uri uri) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                return getRealPathFromURI_API19(context, uri);
            } else {
                return getRealPathFromURI_API11to18(context, uri);
            }
        } catch (IllegalArgumentException e) {
            // path is not a uri, so we assume it's already an absolute path
        }

        return uri.toString();
    }

    @SuppressLint("NewApi")
    private static String getRealPathFromURI_API19(Context context, Uri uri) {
        String filePath = "";
        String wholeID = DocumentsContract.getDocumentId(uri);

        if (uri.getHost().equals(HOST_EXTERNAL_STORAGE)) {
            String[] parts = wholeID.split(":");

            // parts[0] # should be primary
            // parts[1] # relative path to file on storage

            StringBuilder builder = new StringBuilder();
            builder.append(Environment.getExternalStorageDirectory().getAbsolutePath())
                    .append("/")
                    .append(parts[1]);

            filePath = builder.toString();
        } else {
            // Split at colon, use second item in the array
            String id = wholeID.split(":")[1];

            String type = context.getContentResolver().getType(uri);
            System.out.println("=============== type : " + type);

            if (type == null) {
                LogUtils.e("Cannot get type of media!");
                return "";
            }

            Cursor cursor = null;
            String[] column = new String[1];

            if (type.contains("video")) {
                column[0] = MediaStore.Video.Media.DATA;

                // where id is equal to
                String sel = MediaStore.Video.Media._ID + "=?";

                cursor = context.getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                        column, sel, new String[]{id}, null);
            } else if (type.contains("image")) {
                column[0] = MediaStore.Images.Media.DATA;

                // where id is equal to
                String sel = MediaStore.Images.Media._ID + "=?";

                cursor = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        column, sel, new String[]{id}, null);
            }

            if (cursor != null) {
                int columnIndex = cursor.getColumnIndex(column[0]);

                if (cursor.moveToFirst()) {
                    filePath = cursor.getString(columnIndex);
                }
                cursor.close();
            }
        }

        return filePath;
    }

    public static Bitmap getVideoThumbnail(Context context, Uri videoUri) {

        String path = getRealPathFromURI(context, videoUri);
        return ThumbnailUtils.createVideoThumbnail(path, MediaStore.Images.Thumbnails.MINI_KIND);
    }

    @SuppressLint("NewApi")
    private static String getRealPathFromURI_API11to18(Context context, Uri contentUri) {
        String[] proj = {MediaStore.Images.Media.DATA};
        String result = null;

        CursorLoader cursorLoader = new CursorLoader(
                context,
                contentUri, proj, null, null, null);
        Cursor cursor = cursorLoader.loadInBackground();

        if (cursor != null) {
            int column_index =
                    cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            result = cursor.getString(column_index);
        }
        return result;
    }

    public static String getImageFilename() {
        String dateFormat = "yyyyMMdd_hhmmss";

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat, Locale.US);
        String date = simpleDateFormat.format(new Date(System.currentTimeMillis()));

        return "IMG_" + date + ".jpg";
    }

    public static String getMediaFilePath(Context context, String mediaKey, String type) {
        File contentStoreDir = context.getExternalFilesDir(null);
        File mediaStoreDir = null;

        switch (type) {
            case TYPE_IMAGE:
                mediaStoreDir = new File(contentStoreDir, "images");
                break;
            case TYPE_VIDEO:
                mediaStoreDir = new File(contentStoreDir, "videos");
                break;
            case TYPE_AUDIO:
                mediaStoreDir = new File(contentStoreDir, "audio");
                break;
            default:
                throw new IllegalStateException("Unknown media type: " + type);
        }

        if (!mediaStoreDir.exists()) {
            LogUtils.d("MediaStore dir not exists; creating.");
            boolean result = mediaStoreDir.mkdirs();
            LogUtils.d("dir created succesfully: " + result);
        }

        return mediaStoreDir.getPath() + File.separator + mediaKey;
    }

}
