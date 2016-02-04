package com.afrozaar.wp_api_v2_client_android.upload;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;

import com.afrozaar.wp_api_v2_client_android.R;
import com.afrozaar.wp_api_v2_client_android.model.Media;
import com.afrozaar.wp_api_v2_client_android.model.Meta;
import com.afrozaar.wp_api_v2_client_android.model.Post;
import com.afrozaar.wp_api_v2_client_android.rest.ClientRetrofit;
import com.afrozaar.wp_api_v2_client_android.util.ContentUtil;
import com.afrozaar.wp_api_v2_client_android.util.MediaUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * @author Jan-Louis Crafford
 *         Created on 2016/01/29.
 */
public class UploadPostIntentService extends IntentService {

    public static final String ACTION_UPLOAD_DONE = "action.upload.done";

    private NotificationCompat.Builder notificationBuilder;
    private NotificationManager notificationManager;
    private int notifyId = 1;

    private Call<Media> activeCall;

    private String link;
    private boolean finished;
    private boolean cancelled;

    public UploadPostIntentService() {
        super("UploadPostIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        notificationBuilder = new NotificationCompat.Builder(getApplicationContext())
                .setSmallIcon(R.drawable.ic_upload)
                .setProgress(0, 0, true)
                .setContentTitle("Uploading post");

        Intent stopIntent = new Intent(this, CancelPostUploadReceiver.class);
        PendingIntent pendingIntentStop = PendingIntent.getBroadcast(this, 0, stopIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        notificationBuilder.addAction(R.drawable.ic_cancel, "Cancel", pendingIntentStop);

        notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

        String baseUrl = getApplicationContext().getString(R.string.wp_base_url);
        String username = "xml-rpc";
        String pass = "@#df$%S";

        ClientRetrofit clientRetrofit = new ClientRetrofit(baseUrl, username, pass, true);

        Bundle extras = intent.getExtras();

        String headline = extras.getString("headline");
        String content = extras.getString("content");
        long[] catArray = extras.getLongArray("catIds");
        ArrayList<WpMediaItem> mediaItems = extras.getParcelableArrayList("mediaStrings");
        ArrayList<Meta> postMetas = extras.getParcelableArrayList("metas");

        List<Long> catIds = new ArrayList<>();
        for (long aCatArray : catArray) {
            catIds.add(aCatArray);
        }

        // Upload images

        List<Long> mediaIds = new ArrayList<>();
        for (int i = 0; i < mediaItems.size(); i++) {
            log("Uploading image " + (i + 1) + " of " + mediaItems.size());
            File f = new File(MediaUtil.getRealPathFromURI(getApplicationContext(), mediaItems.get(i).mediaPath));

            Media media = new Media();
            media.withTitle(f.getName())
                    .withCaption(mediaItems.get(i).caption);

            try {
                activeCall = clientRetrofit.createMedia(media, f);
                Response<Media> mediaResponse = activeCall.execute();
                if (mediaResponse.isSuccess()) {
                    mediaIds.add(mediaResponse.body().getId());
                } else {
                    error(mediaResponse.code(), mediaResponse.errorBody() != null ? mediaResponse.errorBody().string() : mediaResponse.message());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (cancelled) {
            return;
        }

        // Create post object

        log("Creating post data...");
        StringBuilder builder = new StringBuilder();
        if (mediaIds.size() > 0) {
            //[gallery ids="75,73,67"]
            builder.append("[gallery ids=\"");

            for (int i = 0; i < mediaIds.size(); i++) {
                builder.append(mediaIds.get(i));
                if (i < mediaIds.size() - 1) {
                    builder.append(",");
                }
            }

            builder.append("\"]")
                    .append("<br/>")
                    .append("<br/>");
        }

        builder.append(content);

        if (postMetas != null && postMetas.size() > 0) {
            String address = postMetas.get(0).getValue();
            String shortCode = ContentUtil.getContentLocationShortcode(getApplicationContext(), address);

            builder.append("<br/>")
                    .append("<br/>")
                    .append(shortCode);
        }

        Post post = new Post();
        post.withTitle(headline)
                .withContent(builder.toString())
                .withStatus("publish")
                .withCategories(catIds);

        if (mediaIds.size() == 0) {
            post.setFormat("standard");
        } else {
            int firstImage = mediaIds.get(0).intValue();
            post.withFormat("gallery")
                    .withFeaturedImage(firstImage);
        }

        long postId = -1;
        try {
            Response<Post> postResponse = clientRetrofit.createPost(post).execute();
            if (postResponse.isSuccess()) {
                postId = postResponse.body().getId();
                link = postResponse.body().getLink();
            } else {
                error(postResponse.code(), postResponse.errorBody() != null ? postResponse.errorBody().string() : postResponse.message());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Set post meta

        if (postId != -1 && postMetas != null && postMetas.size() > 0) {
            for (Meta meta : postMetas) {
                try {
                    Response<Meta> metaResponse = clientRetrofit.createPostMeta(postId, meta).execute();
                    if (metaResponse.isSuccess()) {
                        //log("uploaded post meta data");
                    } else {
                        error(metaResponse.code(), metaResponse.errorBody() != null ? metaResponse.errorBody().string() : metaResponse.message());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        finished = true;
    }

    @Override
    public void onDestroy() {
        if (activeCall != null) {
            if (activeCall.isExecuted() && !activeCall.isCanceled()) {
                activeCall.cancel();
            }
        }
        if (!finished) {
            cancelled = true;

            notificationBuilder.setProgress(0, 0, false)
                    .setContentTitle("Upload cancelled")
                    .setContentText("")
                    .setAutoCancel(true)
                    .mActions.clear();
            notificationManager.notify(notifyId, notificationBuilder.build());
        } else {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
            PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 123, intent, PendingIntent.FLAG_UPDATE_CURRENT);

            notificationBuilder.setProgress(0, 0, false)
                    .setContentTitle("Uploaded post")
                    .setContentText(link)
                    .setAutoCancel(true);

            notificationBuilder.mActions.clear();
            notificationBuilder.addAction(R.drawable.ic_info, "Preview", pendingIntent);
            notificationManager.notify(notifyId, notificationBuilder.build());
        }

        Intent intent = new Intent(ACTION_UPLOAD_DONE);
        intent.putExtra("success", finished);
        LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent);

        super.onDestroy();
    }

    private void log(String msg) {
        System.out.println("========== " + msg + " =========");

        notificationBuilder.setContentText(msg);
        notificationManager.notify(notifyId, notificationBuilder.build());
    }

    private void error(int code, String message) {
        System.out.println("========== Error : (" + code + ") " + message);
    }
}
