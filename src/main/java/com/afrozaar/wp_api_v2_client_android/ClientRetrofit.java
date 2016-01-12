package com.afrozaar.wp_api_v2_client_android;

import android.util.Base64;

import com.afrozaar.wp_api_v2_client_android.model.wp_v1.Post;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

import java.io.IOException;
import java.util.List;

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
                Request request = chain.request().newBuilder().addHeader("Authorization", "Basic " + new String(encodedAuth)).build();
                System.out.println("================= URL " + request.url().toString());
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
}
