package com.afrozaar.wp_api_v2_client_android;

import android.content.Context;
import android.util.Base64;

import com.afrozaar.wp_api_v2_client_android.model.wp_v1.Media;
import com.afrozaar.wp_api_v2_client_android.model.wp_v1.Post;
import com.afrozaar.wp_api_v2_client_android.util.AmazonHelper;
import com.afrozaar.wp_api_v2_client_android.util.ContentUtil;
import com.afrozaar.wp_api_v2_client_android.util.LogUtils;
import com.google.common.collect.ImmutableMap;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * @author Jan-Louis Crafford
 *         Created on 2016/01/12.
 */
public class ClientRetrofit {

    private WordPressRestInterface mRestInterface;
    private AmazonHelper mAmazonHelper;
    private Context mContext;

    public ClientRetrofit(Context context, String baseUrl, final String username, final String password) {
        mContext = context;
        mAmazonHelper = AmazonHelper.with(context);
        LogUtils.d("------- AmazonHelper PoolId :" + mAmazonHelper.getCognitoIdentityPoolId());
        LogUtils.d("------- AmazonHelper Region :" + mAmazonHelper.getIdentityPoolRegion());
        OkHttpClient okHttpClient = new OkHttpClient();
        Interceptor interceptor = new Interceptor() {
            @Override
            public com.squareup.okhttp.Response intercept(Chain chain) throws IOException {
                final byte[] encodedAuth = Base64.encode((username + ":" + password).getBytes(), Base64.NO_WRAP);
                Request request = chain.request().newBuilder()
                        .addHeader("Authorization", "Basic " + new String(encodedAuth))
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

        Map<String, RequestBody> map = ContentUtil.makeMediaItemUploadMap(media, file);
        String header = "filename=" + file.getName();

        Call<Media> call = mRestInterface.createMedia(header, map);
        call.enqueue(callback);
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

    public void getTags() {
        Call<List<Post>> call = mRestInterface.getPostsForTags("awe");
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Response<List<Post>> response, Retrofit retrofit) {
                System.out.println("=============== RESPONSE " + response.code());
                if (response.body() != null) {
                    System.out.println("======= BODY SIZE : " + response.body().size());
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }
}
