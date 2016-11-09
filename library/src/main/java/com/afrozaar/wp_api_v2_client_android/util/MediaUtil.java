package com.afrozaar.wp_api_v2_client_android.util;

import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;

import java.io.File;
import java.io.IOException;
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
    private static final String HOST_GOOGLE_DRIVE = "com.google.android.apps.docs.storage";
    private static final String HOST_DOWNLOADS = "com.android.providers.downloads.documents";
    private static final String HOST_GOOGLE_PHOTOS = "com.google.android.apps.photos.contentprovider";
    private static final String HOST_MEDIA_DOCUMENTS = "com.android.providers.media.documents";

    /**
     * Get a file path from a Uri. This will get the the path for Storage Access
     * Framework Documents, as well as the _data field for the MediaStore and
     * other file-based ContentProviders.
     *
     * @param context The context.
     * @param uri     The Uri to query.
     * @author paulburke
     */
    @SuppressLint("NewApi")
    public static String getPath(final Context context, final Uri uri) {

        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }

                // TODO handle non-primary volumes
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{
                        split[1]
                };

                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {
            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }

    /**
     * Get the value of the data column for this Uri. This is useful for
     * MediaStore Uris, and other file-based ContentProviders.
     *
     * @param context       The context.
     * @param uri           The Uri to query.
     * @param selection     (Optional) Filter used in the query.
     * @param selectionArgs (Optional) Selection arguments used in the query.
     * @return The value of the _data column, which is typically a file path.
     */
    public static String getDataColumn(Context context, Uri uri, String selection,
                                       String[] selectionArgs) {
        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {column};

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }


    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    public static boolean isExternalStorageDocument(Uri uri) {
        return HOST_EXTERNAL_STORAGE.equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public static boolean isDownloadsDocument(Uri uri) {
        return HOST_DOWNLOADS.equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public static boolean isMediaDocument(Uri uri) {
        return HOST_MEDIA_DOCUMENTS.equals(uri.getAuthority());
    }

    public static boolean isDriveUri(Uri uri) {
        return uri.getAuthority().equals(HOST_GOOGLE_DRIVE);
    }

    public static boolean isGooglePhotosUri(Uri uri) {
        return uri.getAuthority().equals(HOST_GOOGLE_PHOTOS);
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
                // create .nomedia file
                File noMedia = new File(storageDir, ".nomedia");
                noMedia.createNewFile();
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

    public static File getVideoFilename(Context context, boolean isPublic) throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(new Date());
        String videoFileName = "VID_" + timeStamp + ".mp4";

        File storageDir = null;
        if (isPublic) {
            storageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), "Camera");
        } else {
            storageDir = new File(context.getExternalFilesDir(null), "videos");
        }

        if (!storageDir.exists()) {
            boolean makeDir = storageDir.mkdirs();
            if (!makeDir) {
                throw new IOException("Unable to create parent dirs for video file.");
            } else {
                LogUtils.d("Created parent dir structure for file.");
                // create .nomedia file
                File noMedia = new File(storageDir, ".nomedia");
                noMedia.createNewFile();
            }
        }
        File video = new File(storageDir, videoFileName);

        if (video.createNewFile()) {
            LogUtils.d("Created new video file : " + video.getAbsolutePath());
        } else {
            LogUtils.d("New file not created; already exists?");
        }

        return video;
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
                // create .nomedia file
                File noMedia = new File(storageDir, ".nomedia");
                noMedia.createNewFile();
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
