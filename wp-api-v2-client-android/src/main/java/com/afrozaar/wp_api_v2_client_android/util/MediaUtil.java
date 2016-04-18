package com.afrozaar.wp_api_v2_client_android.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
            String rawString = uri.toString();
            System.out.println("======= getting path for uri : " + rawString);
            if (!rawString.startsWith("content")) {
                // path is not a ContentResolver uri
                return uri.toString();
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                return getRealPathFromURI_API19(context, uri);
            } else {
                return getRealPathFromURI_API11to18(context, uri);
            }
        } catch (IllegalArgumentException e) {
            // path is not a uri, so we assume it's already an absolute path
            LogUtils.w("Error while trying to get real path from uri", e);
        } catch (Exception e) {
            LogUtils.w("Something went wrong while reading Uri path", e);
        }

        return uri.toString();
    }

    @SuppressLint("NewApi")
    private static String getRealPathFromURI_API19(Context context, Uri uri) {
        String filePath = "";

        if (uri.getHost().equals(HOST_EXTERNAL_STORAGE)) {
            String wholeID = DocumentsContract.getDocumentId(uri);

            String[] parts = wholeID.split(":");

            // parts[0] # should be primary
            // parts[1] # relative path to file on storage

            StringBuilder builder = new StringBuilder();
            builder.append(Environment.getExternalStorageDirectory().getAbsolutePath())
                    .append("/")
                    .append(parts[1]);

            filePath = builder.toString();
        } else {
            String id;
            List<String> paths = uri.getPathSegments();
            if (paths.contains("media") && paths.contains("external")) {
                id = paths.get(paths.size() - 1);
            } else {
                String wholeID = DocumentsContract.getDocumentId(uri);
                // Split at colon, use second item in the array
                id = wholeID.split(":")[1];
            }

            String type = context.getContentResolver().getType(uri);
            //System.out.println("=============== type : " + type);

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

    public static File getImageFilename(Context context, boolean isPublic) throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(new Date());
        String imageFileName = "IMG_" + timeStamp + ".jpg";

        File storageDir = null;
        if (isPublic) {
            storageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), "Camera");
        } else {
            storageDir = new File(context.getExternalFilesDir(null), "images");
        }

        if (!storageDir.exists()) {
            boolean makeDir = storageDir.mkdirs();
            if (!makeDir) {
                throw new IOException("Unable to create parent dirs for image file.");
            } else {
                LogUtils.d("Created parent dir structure for file.");
            }
        }
        File image = new File(storageDir, imageFileName);

        if (image.createNewFile()) {
            LogUtils.d("Created new image file : " + image.getAbsolutePath());
        } else {
            LogUtils.d("New file not created; already exists?");
        }

        return image;
    }

    public static String getImageFilenamePath(Context context, boolean isPublic) throws IOException {
        return getImageFilename(context, isPublic).toString();
    }

    public static File getAudioFilename(Context context) throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(new Date());
        String audioFileName = "AUD_" + timeStamp + ".m4a";

        File storageDir = new File(context.getExternalFilesDir(null), "audio");
        if (!storageDir.exists()) {
            boolean makeDir = storageDir.mkdirs();
            if (!makeDir) {
                throw new IOException("Unable to create parent dirs for image file.");
            } else {
                LogUtils.d("Created parent dir structure for file.");
            }
        }
        File audioFile = new File(storageDir, audioFileName);
        if (audioFile.createNewFile()) {
            LogUtils.d("Created new audio file : " + audioFile.getAbsolutePath());
        } else {
            LogUtils.d("New file not created; already exists?");
        }

        return audioFile;
    }

    public static void addImageToMediaScanner(Context context, String currentPhotoPath) {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(currentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        context.sendBroadcast(mediaScanIntent);
    }

    public static void deleteFile(File file) {
        try {
            if (file.delete()) {
                LogUtils.d("File (" + file.getPath() + ") was deleted successfully.");
            } else {
                LogUtils.d("Couldn't delete file : " + file.getPath());
            }
        } catch (Exception e) {
            LogUtils.i("Exception while trying to delete file", e);
        }
    }

    public static void deleteFile(String filePath) {
        try {
            deleteFile(new File(filePath));
        } catch (Exception e) {
            LogUtils.i("Exception while trying to delete file", e);
        }
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
