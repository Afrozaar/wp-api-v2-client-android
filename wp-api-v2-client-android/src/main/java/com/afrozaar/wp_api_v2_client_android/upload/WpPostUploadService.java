package com.afrozaar.wp_api_v2_client_android.upload;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;

import com.afrozaar.wp_api_v2_client_android.R;
import com.afrozaar.wp_api_v2_client_android.model.Media;
import com.afrozaar.wp_api_v2_client_android.model.Meta;
import com.afrozaar.wp_api_v2_client_android.model.Post;
import com.afrozaar.wp_api_v2_client_android.rest.WpClientRetrofit;
import com.afrozaar.wp_api_v2_client_android.util.ContentUtil;
import com.afrozaar.wp_api_v2_client_android.util.LogUtils;
import com.afrozaar.wp_api_v2_client_android.util.PasswordHash;
import com.afrozaar.wp_api_v2_client_android.util.WordpressPreferenceHelper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by jlo on 2016/02/28.
 */
public class WpPostUploadService extends Service {

    public static final String ACTION_UPLOAD_DONE = "action.upload.done";

    private static final int COMMAND_START_UPLOAD = 1;
    private static final int COMMAND_STOP_UPLOAD = 2;

    private static final String ARG_COMMAND = "arg.command";

    private static final String ARG_BASE_URL = "arg.base_url";
    private static final String ARG_PARENT_SECTION_ID = "arg.parent_section_id";
    private static final String ARG_HEADLINE = "arg.headline";
    private static final String ARG_CATEGORIES = "arg.categories";
    private static final String ARG_MEDIAS = "arg.medias";
    private static final String ARG_METAS = "arg.metas";

    public static void startUpload(Context context, String baseUrl, long parentSectionId, String headline,
                                   long[] categories, ArrayList<Media> mediaItems, ArrayList<Meta> metas) {
        Intent intent = new Intent(context, WpPostUploadService.class);
        intent.putExtra(ARG_COMMAND, COMMAND_START_UPLOAD);
        intent.putExtra(ARG_BASE_URL, baseUrl);
        intent.putExtra(ARG_PARENT_SECTION_ID, parentSectionId);
        intent.putExtra(ARG_HEADLINE, headline);
        intent.putExtra(ARG_CATEGORIES, categories);
        intent.putParcelableArrayListExtra(ARG_MEDIAS, mediaItems);
        intent.putParcelableArrayListExtra(ARG_METAS, metas);

        context.startService(intent);
    }

    public static void stopUpload(Context context) {
        Intent intent = new Intent(context, WpPostUploadService.class);
        intent.putExtra(ARG_COMMAND, COMMAND_STOP_UPLOAD);
        context.startService(intent);
    }

    private NotificationManager notificationManager;
    private NotificationCompat.Builder notificationBuilder;
    private int notificationId = 100;

    private UploadTask uploadTask;

    private Call<Media> activeCall;

    private String uploadLink;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        notificationBuilder = new NotificationCompat.Builder(getApplicationContext())
                .setSmallIcon(R.drawable.ic_upload)
                .setProgress(0, 0, true)
                .setContentTitle("Uploading post");

        Intent stopIntent = new Intent(this, CancelPostUploadReceiver.class);
        PendingIntent pendingIntentStop = PendingIntent.getBroadcast(this, 0, stopIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        notificationBuilder.addAction(R.drawable.ic_cancel, "Cancel", pendingIntentStop);

        notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null && intent.getExtras() != null) {
            switch (intent.getExtras().getInt(ARG_COMMAND)) {
                case COMMAND_START_UPLOAD:
                    String baseUrl = intent.getStringExtra(ARG_BASE_URL);
                    long parentSectionId = intent.getLongExtra(ARG_PARENT_SECTION_ID, -1);

                    String headline = intent.getStringExtra(ARG_HEADLINE);
                    long[] categories = intent.getLongArrayExtra(ARG_CATEGORIES);
                    ArrayList<Media> mediaItems = intent.getParcelableArrayListExtra(ARG_MEDIAS);
                    ArrayList<Meta> metas = intent.getParcelableArrayListExtra(ARG_METAS);

                    startUpload(baseUrl, parentSectionId, headline, categories, mediaItems, metas);
                    break;
                case COMMAND_STOP_UPLOAD:
                    stopUpload();
                    break;
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }

    private void startUpload(String baseUrl, long parentSectionId, String headline, long[] categories, ArrayList<Media> mediaItems, ArrayList<Meta> metas) {
        if (uploadTask != null && !uploadTask.isCancelled()) {
            LogUtils.w("Upload task already running");
            stopUpload();
        }
        uploadTask = new UploadTask(baseUrl, parentSectionId, headline, categories, mediaItems, metas);
        try {
            // boolean result = uploadTask.get(10, TimeUnit.MINUTES);
            uploadTask.execute();
        } catch (Exception e) {
            e.printStackTrace();

            Intent intent = new Intent(ACTION_UPLOAD_DONE);
            LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent);

            stopSelf();
        }
    }


    private void previewPost() {
        //Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uploadLink));
        //PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 123, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        notificationBuilder.setProgress(0, 0, false)
                .setContentTitle("Uploaded post.")
                //.setContentText(uploadLink)
                .setAutoCancel(true);

        notificationBuilder.mActions.clear();
        //notificationBuilder.addAction(R.drawable.ic_menu_info_outline, "Preview (DEBUG)", pendingIntent);
        notificationManager.notify(notificationId, notificationBuilder.build());

        Intent intentBroadcast = new Intent(ACTION_UPLOAD_DONE);
        intentBroadcast.putExtra("success", true);
        LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intentBroadcast);

        stopSelf();
    }

    private void stopUpload() {
        try {
            if (activeCall != null) {
                if (activeCall.isExecuted() && !activeCall.isCanceled()) {
                    activeCall.cancel();
                }
            }
        } catch (Exception e) {
            // ignore
        }

        if (uploadTask != null && !uploadTask.isCancelled()) {
            uploadTask.cancel(true);
        }

        notificationBuilder.setProgress(0, 0, false)
                .setContentTitle("Upload cancelled")
                .setContentText("")
                .setAutoCancel(true)
                .mActions.clear();
        notificationManager.notify(notificationId, notificationBuilder.build());

        Intent intent = new Intent(ACTION_UPLOAD_DONE);
        LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent);

        stopSelf();
    }

    private void log(String msg) {
        System.out.println("========== " + msg + " =========");

        notificationBuilder.setContentText(msg);
        notificationManager.notify(notificationId, notificationBuilder.build());
    }

    private void error(int code, String message) {
        System.out.println("========== Error : (" + code + ") " + message);
    }

    private class UploadTask extends AsyncTask<Void, Void, Boolean> {
        String baseUrl;
        long parentSectionId;

        String headline;
        long[] categories;
        ArrayList<Media> mediaItems;
        ArrayList<Meta> metas;

        public UploadTask(String baseUrl, long parentSectionId, String headline, long[] categories, ArrayList<Media> mediaItems, ArrayList<Meta> metas) {
            this.baseUrl = baseUrl;
            this.parentSectionId = parentSectionId;

            this.headline = headline;
            this.categories = categories;
            this.mediaItems = mediaItems;
            this.metas = metas;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            String username = WordpressPreferenceHelper.with(getApplicationContext()).getWordPressUsername();
            String pass = null;
            pass = PasswordHash.encrypt(username);

            WpClientRetrofit clientRetrofit = new WpClientRetrofit(baseUrl, username, pass, true);

            List<Long> catIds = new ArrayList<>();
            if (categories == null || categories.length == 0) {
                catIds.add(parentSectionId);
            } else {
                for (long aCatArray : categories) {
                    catIds.add(aCatArray);
                }
            }

            // Upload images

            List<Long> mediaIds = new ArrayList<>();
            if (mediaItems != null && mediaItems.size() > 0) {
                for (int i = 0; i < mediaItems.size(); i++) {
                    log("Uploading image " + (i + 1) + " of " + mediaItems.size());

                    File f = null;
                    try {
                        f = new File(mediaItems.get(i).getLink());
                    } catch (NullPointerException e) {
                        LogUtils.e("File is null; cannot upload!", e);
                    }

                    if (f == null) {
                        continue;
                    }

                    try {
                        activeCall = clientRetrofit.createMedia(mediaItems.get(i), f);
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
                if (metas == null) {
                    metas = new ArrayList<>();
                }
                metas.add(new Meta().withKey("az_images").withValue(mediaIds.size() + ""));
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

            if (metas != null) {
                // search for address in case
                for (Meta meta : metas) {
                    if (meta.getKey().equals("az_address")) {
                        String address = metas.get(0).getValue();
                        String shortCode = ContentUtil.getContentLocationShortcode(getApplicationContext(), address);

                        builder.append("<br/>")
                                .append("<br/>")
                                .append(shortCode);

                        break;
                    }
                }
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
                    uploadLink = postResponse.body().getLink();
                } else {
                    error(postResponse.code(), postResponse.errorBody() != null ? postResponse.errorBody().string() : postResponse.message());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Set post meta

            if (postId != -1 && metas != null && metas.size() > 0) {
                log("Finishing post...");
                for (Meta meta : metas) {
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

            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);

            if (aBoolean) {
                previewPost();
            } else {
                stopSelf();
            }
        }
    }
}
