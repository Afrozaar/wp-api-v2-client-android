package com.afrozaar.wp_api_v2_client_android.rest;

import com.afrozaar.wp_api_v2_client_android.WordPressRestInterface;
import com.afrozaar.wp_api_v2_client_android.model.Media;
import com.afrozaar.wp_api_v2_client_android.model.Meta;
import com.afrozaar.wp_api_v2_client_android.model.Post;
import com.afrozaar.wp_api_v2_client_android.model.Taxonomy;
import com.afrozaar.wp_api_v2_client_android.model.User;
import com.afrozaar.wp_api_v2_client_android.model.dto.PostCount;
import com.afrozaar.wp_api_v2_client_android.rest.interceptor.OkHttpBasicAuthInterceptor;
import com.afrozaar.wp_api_v2_client_android.rest.interceptor.OkHttpDebugInterceptor;
import com.afrozaar.wp_api_v2_client_android.util.ContentUtil;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Jan-Louis Crafford
 *         Created on 2016/01/12.
 */
public class WpClientRetrofit {

    private WordPressRestInterface mRestInterface;

    public WpClientRetrofit(String baseUrl, final String username, final String password) {
        this(baseUrl, username, password, false);
    }

    public WpClientRetrofit(String baseUrl, final String username, final String password, boolean debugEnabled) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        builder.connectTimeout(30, TimeUnit.SECONDS);
        builder.readTimeout(30, TimeUnit.SECONDS);
        builder.writeTimeout(30, TimeUnit.SECONDS);

        // add the Basic Auth header
        builder.addInterceptor(new OkHttpBasicAuthInterceptor(username, password));

        if (debugEnabled) {
            builder.addInterceptor(new OkHttpDebugInterceptor());
        }

        // setup retrofit with custom OkHttp client and Gson parser
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(builder.build())
                .build();

        // create instance of REST interface
        mRestInterface = retrofit.create(WordPressRestInterface.class);
    }

    private <T> void doRetrofitCall(Call<T> call, final WordPressRestResponse<T> callback) {
        Callback<T> retroCallback = new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure(HttpServerErrorResponse.from(response.errorBody()));
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                t.printStackTrace();
                callback.onFailure(HttpServerErrorResponse.from(t));
            }
        };
        call.enqueue(retroCallback);
    }

    // USER

    public void createUser(User user, WordPressRestResponse<User> callback) {
        doRetrofitCall(mRestInterface.createUser(User.mapFromFields(user)), callback);
    }

    public Call<User> getUser(long userId) {
        return mRestInterface.getUser(userId);
    }

    public void getUserFromLogin(String login, WordPressRestResponse<User> callback) {
        doRetrofitCall(mRestInterface.getUserFromLogin(login), callback);
    }

    public void getUserFromEmail(String email, WordPressRestResponse<User> callback) {
        doRetrofitCall(mRestInterface.getUserFromEmail(email), callback);
    }

    public void getUserMe(WordPressRestResponse<User> callback) {
        doRetrofitCall(mRestInterface.getUserMe(), callback);
    }

    public void updateUser(User user, WordPressRestResponse<User> callback) {
        Map<String, Object> map = User.mapFromFields(user);
        doRetrofitCall(mRestInterface.updateUser(user.getId(), map), callback);
    }

    // POSTS

    public void createPost(Post post, WordPressRestResponse<Post> callback) {
        // 201 CREATED on success
        doRetrofitCall(mRestInterface.createPost(Post.mapFromFields(post)), callback);
    }

    public Call<Post> createPost(Post post) {
        return mRestInterface.createPost(Post.mapFromFields(post));
    }

    public void getPost(long postId, WordPressRestResponse<Post> callback) {
        Map<String, String> map = new HashMap<>();
        map.put("context", "edit");
        doRetrofitCall(mRestInterface.getPost(postId, map), callback);
    }

    public Call<Post> getPost(long postId) {
        Map<String, String> map = new HashMap<>();
        map.put("context", "edit");
        return mRestInterface.getPost(postId, map);
    }

    public void getPostForEdit(long postId, WordPressRestResponse<Post> callback) {
        Map<String, String> map = new HashMap<>();
        map.put("context", "edit");
        doRetrofitCall(mRestInterface.getPost(postId, map), callback);
    }

    public void getPosts(WordPressRestResponse<List<Post>> callback) {
        doRetrofitCall(mRestInterface.getPosts(), callback);
    }

    public Call<List<Post>> getPosts() {
        return mRestInterface.getPosts();
    }

    public Call<List<Post>> getPostsForPage(int startPage) {
        Map<String, String> map = new HashMap<>();
        map.put("page", startPage + "");
        map.put("context", "edit");
        return mRestInterface.getPosts(map);
    }

    public Call<List<Post>> getPostsAfterDate(String date) {
        Map<String, String> map = new HashMap<>();
        map.put("after", date);
        map.put("context", "edit");
        return mRestInterface.getPosts(map);
    }

    public Call<List<Post>> getPostsForAuthor(long authorId, String status) {
        return mRestInterface.getPostsForAuthor(authorId, status, "edit");
    }

    public void getPostsForAuthor(long authorId, String status, WordPressRestResponse<List<Post>> callback) {
        doRetrofitCall(getPostsForAuthor(authorId, status), callback);
    }

    public void getPostsForTag(String tag, WordPressRestResponse<List<Post>> callback) {
        doRetrofitCall(mRestInterface.getPostsForTags(tag), callback);
    }

    public void updatePost(Post post, WordPressRestResponse<Post> callback) {
        // 200 on success
        doRetrofitCall(mRestInterface.updatePost(post.getId(), Post.mapFromFields(post)), callback);
    }

    public Call<Post> updatePost(Post post) {
        return mRestInterface.updatePost(post.getId(), Post.mapFromFields(post));
    }

    public void deletePost(long postId, boolean force, WordPressRestResponse<Post> callback) {
        // 200 on success
        // 410 GONE on failure
        doRetrofitCall(mRestInterface.deletePost(postId, force, "edit"), callback);
    }

    public Call<Post> deletePost(long postId, boolean force) {
        return mRestInterface.deletePost(postId, force, "edit");
    }

    /* MEDIA */

    public void createMedia(Media media, File file, WordPressRestResponse<Media> callback) {
        Map<String, RequestBody> map = ContentUtil.makeMediaItemUploadMap(media, file);
        String header = "filename=" + file.getName();

        doRetrofitCall(mRestInterface.createMedia(header, map), callback);
    }

    public Call<Media> createMedia(Media media, File file) {
        Map<String, RequestBody> map = ContentUtil.makeMediaItemUploadMap(media, file);
        String header = "filename=" + file.getName();
        return mRestInterface.createMedia(header, map);
    }

    public void getMedia(WordPressRestResponse<List<Media>> callback) {
        doRetrofitCall(mRestInterface.getMedia(), callback);
    }

    public Call<Media> getMedia(long mediaId) {
        return mRestInterface.getMedia(mediaId);
    }

    public void getMediaForPost(long postId, String mimeType, WordPressRestResponse<List<Media>> callback) {
        doRetrofitCall(mRestInterface.getMediaForPost(postId, mimeType), callback);
    }

    public Call<List<Media>> getMediaForPost(long postId, String mimeType) {
        return mRestInterface.getMediaForPost(postId, mimeType);
    }

    public void updateMedia(Media media, long mediaId, WordPressRestResponse<Media> callback) {
        doRetrofitCall(mRestInterface.updateMedia(mediaId, Media.mapFromFields(media)), callback);
    }

    public Call<Media> updateMedia(Media media, long mediaId) {
        return mRestInterface.updateMedia(mediaId, Media.mapFromFields(media));
    }

    public Call<Media> deleteMedia(long mediaId) {
        return mRestInterface.deleteMedia(mediaId);
    }

    /* TAXONOMIES */

    public void setTagForPost(long postId, long tagId, WordPressRestResponse<Taxonomy> callback) {
        doRetrofitCall(mRestInterface.setPostTag(postId, tagId), callback);
    }

    public void getTagsForPost(long postId, WordPressRestResponse<List<Taxonomy>> callback) {
        doRetrofitCall(mRestInterface.getPostTags(postId), callback);
    }

    public void getTags(WordPressRestResponse<List<Taxonomy>> callback) {
        doRetrofitCall(mRestInterface.getTags(), callback);
    }

    public void getTagsOrderedByCount(WordPressRestResponse<List<Taxonomy>> callback) {
        Map<String, String> map = new HashMap<>();
        map.put("orderby", "count");
        map.put("order", "desc");

        doRetrofitCall(mRestInterface.getTagsOrdered(map), callback);
    }


    public void setCategoryForPost(long postId, long catId, WordPressRestResponse<Taxonomy> callback) {
        doRetrofitCall(mRestInterface.setPostCategory(postId, catId), callback);
    }

    public Call<Taxonomy> setCategoryForPost(long postId, long catId) {
        return mRestInterface.setPostCategory(postId, catId);
    }

    public void getCategoriesForPost(long postId, WordPressRestResponse<List<Taxonomy>> callback) {
        doRetrofitCall(mRestInterface.getPostCategories(postId), callback);
    }

    public void getCategories(WordPressRestResponse<List<Taxonomy>> callback) {
        doRetrofitCall(mRestInterface.getCategories(), callback);
    }

    public Call<List<Taxonomy>> getCategories() {
        return mRestInterface.getCategories();
    }

    public void getCategoriesForParent(long parentId, WordPressRestResponse<List<Taxonomy>> callback) {
        Map<String, Object> map = new HashMap<>();
        map.put("parent", parentId);

        doRetrofitCall(mRestInterface.getCategories(map), callback);
    }

    public Call<List<Taxonomy>> getCategoriesForParent(long parentId) {
        Map<String, Object> map = new HashMap<>();
        map.put("parent", parentId);
        return mRestInterface.getCategories(map);
    }

    /* META */

    public void createPostMeta(long postId, Meta meta, WordPressRestResponse<Meta> callback) {
        Map<String, Object> map = new HashMap<>();
        map.put("key", meta.getKey());
        map.put("value", meta.getValue());

        doRetrofitCall(mRestInterface.createPostMeta(postId, map), callback);
    }

    public Call<Meta> createPostMeta(long postId, Meta meta) {
        Map<String, Object> map = new HashMap<>();
        map.put("key", meta.getKey());
        map.put("value", meta.getValue());

        return mRestInterface.createPostMeta(postId, map);
    }

    public Call<Meta> updatePostMeta(long postId, Meta meta) {
        Map<String, Object> map = new HashMap<>();
        map.put("key", meta.getKey());
        map.put("value", meta.getValue());

        return mRestInterface.updatePostMeta(postId, meta.getId(), map);
    }

    public Call<List<Meta>> getPostMetas(long postId) {
        return mRestInterface.getPostMeta(postId);
    }

    public Call<Meta> deletePostMeta(long postId, long metaId) {
        return mRestInterface.deletePostMeta(postId, metaId);
    }

    /* OTHER */

    public void getPostCounts(WordPressRestResponse<PostCount> callback) {
        doRetrofitCall(mRestInterface.getPostCounts(), callback);
    }

    public Call<PostCount> getPostCounts() {
        return mRestInterface.getPostCounts();
    }
}
