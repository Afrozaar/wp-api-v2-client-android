package com.afrozaar.wp_api_v2_client_android.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;

/**
 * Utilities methods for getting a filesystem path from the DocumentProvider URIs.
 * <p/>
 * Created by jay on 11/30/15.
 */
public class MediaUtil {

    private static final String HOST_EXTERNAL_STORAGE = "com.android.externalstorage.documents";

    public static String getRealPathFromURI(Context context, Uri uri) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            return getRealPathFromURI_API19(context, uri);
        } else {
            return getRealPathFromURI_API11to18(context, uri);
        }
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

            String[] column = {MediaStore.Images.Media.DATA};

            // where id is equal to
            String sel = MediaStore.Images.Media._ID + "=?";

            Cursor cursor = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    column, sel, new String[]{id}, null);

            int columnIndex = cursor.getColumnIndex(column[0]);

            if (cursor.moveToFirst()) {
                filePath = cursor.getString(columnIndex);
            }
            cursor.close();
        }

        return filePath;
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
}
