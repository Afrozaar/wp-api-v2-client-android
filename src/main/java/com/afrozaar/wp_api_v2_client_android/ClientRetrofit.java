package com.afrozaar.wp_api_v2_client_android;

import android.util.Base64;

import com.afrozaar.wp_api_v2_client_android.model.wp_v1.Media;
import com.afrozaar.wp_api_v2_client_android.model.wp_v1.Post;
import com.afrozaar.wp_api_v2_client_android.util.ContentUtil;
import com.google.common.collect.ImmutableMap;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * @author Jan-Louis Crafford
 *         Created on 2016/01/12.
 */
public class ClientRetrofit {

    public static final String WP_ADMIN = "android";
    public static final String WP_PASSWORD = "T8YsQw@6Mz)Gd(vGGImUU3z6";

    private WordPressRestInterface mRestInterface;

    public ClientRetrofit(String baseUrl, String username, String password) {

        OkHttpClient okHttpClient = new OkHttpClient();
        Interceptor interceptor = new Interceptor() {
            @Override
            public com.squareup.okhttp.Response intercept(Chain chain) throws IOException {
                final byte[] encodedAuth = Base64.encode((WP_ADMIN + ":" + WP_PASSWORD).getBytes(), Base64.NO_WRAP);
                Request request = chain.request().newBuilder()
                        .addHeader("Authorization", "Basic " + new String(encodedAuth))
                        //.addHeader("Content-Disposition", "form-data; filename=\"IMG_20160107_012732.jpg\"")
                        .build();
                System.out.println("================= URL " + request.url().toString());
                for (String head : request.headers().names()) {
                    System.out.println("======== Header : " + head + " == " + request.header(head));
                }

                return chain.proceed(request);
            }
        };
        okHttpClient.interceptors().add(interceptor);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        mRestInterface = retrofit.create(WordPressRestInterface.class);
    }

    public void createPost(Post post, Callback<Post> callback) {
        // 201 CREATED on success
        Call<Post> call = mRestInterface.createPost(Post.mapFromFields(post));
        call.enqueue(callback);
    }

    public void updatePost(Post post, Callback<Post> callback) {
        // 200 on success
        Call<Post> call = mRestInterface.updatePost(post.getId(), Post.mapFromFields(post));
        call.enqueue(callback);
    }

    public void deletePost(long postId, Callback<Post> callback) {
        // 200 on success
        // 410 GONE on failure
        Call<Post> call = mRestInterface.deletePost(postId);
        call.enqueue(callback);
    }

    public void getPost(long postId, Callback<Post> callback) {
        Call<Post> call = mRestInterface.getPost(postId);
        call.enqueue(callback);
    }

    public void getPostsForAuthor(long authorId, Callback<List<Post>> callback) {
        Call<List<Post>> call = mRestInterface.getPostsForAuthor(authorId);
        call.enqueue(callback);
        /*call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Response<List<Post>> response, Retrofit retrofit) {
                System.out.println("================= RESPONSE : " + response.code() + " == " + response.message());
                //System.out.println("===================== " + response.errorBody().string());
                //System.out.println("=================== body : " + response.bo);
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println("=================== ERRROR : " + t.getMessage());
            }
        });*/
    }

    public void createMedia(Media media, File file, Callback<Media> callback) {
        /*
        try {
            InputStream in = new FileInputStream(file);
            byte[] buf;
            buf = new byte[in.available()];
            while (in.read(buf) != -1) ;

            RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), buf);
            String header = "filename=" + file.getName();

            Call<Media> call = mRestInterface.createMedia(media.getTitle().getRendered(), media.getPostId(),
                    media.getAltText(), media.getCaption(), media.getDescription(), requestBody);

            //Call<Media> call = mRestInterface.createMediaTest(header, requestBody);
            call.enqueue(callback);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        */

        String header = "filename=" + file.getName();

        Map<String, RequestBody> map = new HashMap<>();
        map.put("title", toRequestBody(media.getTitle().getRendered()));
        map.put("caption", toRequestBody(media.getCaption()));
        map.put("alt_text", toRequestBody(media.getAltText()));
        map.put("description", toRequestBody(media.getDescription()));

        String ext = ContentUtil.getImageMimeType(file.getName());

        RequestBody fileBody = RequestBody.create(MediaType.parse(ext), file);
        map.put("file\"; filename=\"" + file.getName() + "\"", fileBody);

        Call<Media> call = mRestInterface.createMedia(header, map);
        call.enqueue(callback);
    }

    public RequestBody toRequestBody(String value) {
        return RequestBody.create(MediaType.parse("text/plain"), value);
    }

    public void updateMedia(Media media, long mediaId, Callback<Media> callback) {
        ImmutableMap.Builder<String, Object> builder = new ImmutableMap.Builder<>();
        builder.put("title", media.getTitle().getRendered())
                .put("caption", media.getCaption())
                .put("alt_text", media.getAltText())
                .put("description", media.getDescription());

        Call<Media> call = mRestInterface.updateMedia(mediaId, builder.build());
        call.enqueue(callback);
    }
}
