package com.afrozaar.wp_api_v2_client_android.rest;

import com.afrozaar.wp_api_v2_client_android.WordPressRestInterface;
import com.afrozaar.wp_api_v2_client_android.model.Media;
import com.afrozaar.wp_api_v2_client_android.model.Post;
import com.afrozaar.wp_api_v2_client_android.model.Taxonomy;
import com.afrozaar.wp_api_v2_client_android.model.User;
import com.afrozaar.wp_api_v2_client_android.rest.interceptor.OkHttpBasicAuthInterceptor;
import com.afrozaar.wp_api_v2_client_android.rest.interceptor.OkHttpDebugInterceptor;
import com.afrozaar.wp_api_v2_client_android.util.ContentUtil;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.RequestBody;

import java.io.File;
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

    public ClientRetrofit(String baseUrl, final String username, final String password) {
        this(baseUrl, username, password, false);
    }

    public ClientRetrofit(String baseUrl, final String username, final String password, boolean debugEnabled) {
        OkHttpClient okHttpClient = new OkHttpClient();

        // add the Basic Auth header
        okHttpClient.interceptors().add(new OkHttpBasicAuthInterceptor(username, password));

        if (debugEnabled) {
            okHttpClient.interceptors().add(new OkHttpDebugInterceptor());
        }

        // setup retrofit with custom OkHttp client and Gson parser
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        // create instance of REST interface
        mRestInterface = retrofit.create(WordPressRestInterface.class);
    }

    private <T> void doRetrofitCall(Call<T> call, final WordPressRestResponse<T> callback) {
        Callback<T> retroCallback = new Callback<T>() {
            @Override
            public void onResponse(Response<T> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure(HttpServerErrorResponse.from(response.errorBody()));
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        };
        call.enqueue(retroCallback);
    }

    // USER

    public void createUser(User user, WordPressRestResponse<User> callback) {
        doRetrofitCall(mRestInterface.createUser(User.mapFromFields(user)), callback);
    }

    public void getUserFromLogin(String login, WordPressRestResponse<User> callback) {
        doRetrofitCall(mRestInterface.getUserFromLogin(login), callback);
    }

    public void getUserMe(WordPressRestResponse<User> callback) {
        doRetrofitCall(mRestInterface.getUserMe(), callback);
    }

    // POSTS

    public void createPost(Post post, WordPressRestResponse<Post> callback) {
        // 201 CREATED on success
        doRetrofitCall(mRestInterface.createPost(Post.mapFromFields(post)), callback);
    }

    public void getPost(long postId, WordPressRestResponse<Post> callback) {
        doRetrofitCall(mRestInterface.getPost(postId), callback);
    }

    public void getPostsForAuthor(long authorId, WordPressRestResponse<List<Post>> callback) {
        doRetrofitCall(mRestInterface.getPostsForAuthor(authorId), callback);
    }

    public void getPostsForTag(String tag, WordPressRestResponse<List<Post>> callback) {
        doRetrofitCall(mRestInterface.getPostsForTags(tag), callback);
    }

    public void updatePost(Post post, WordPressRestResponse<Post> callback) {
        // 200 on success
        doRetrofitCall(mRestInterface.updatePost(post.getId(), Post.mapFromFields(post)), callback);
    }

    public void deletePost(long postId, WordPressRestResponse<Post> callback) {
        // 200 on success
        // 410 GONE on failure
        doRetrofitCall(mRestInterface.deletePost(postId), callback);
    }

    /* MEDIA */

    public void createMedia(Media media, File file, WordPressRestResponse<Media> callback) {
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

        doRetrofitCall(mRestInterface.createMedia(header, map), callback);
    }

    public void updateMedia(Media media, long mediaId, WordPressRestResponse<Media> callback) {
        doRetrofitCall(mRestInterface.updateMedia(mediaId, Media.mapFromFields(media)), callback);
    }

    public void getTags(WordPressRestResponse<List<Taxonomy>> callback) {
        doRetrofitCall(mRestInterface.getTags(), callback);
    }
}
