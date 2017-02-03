package com.afrozaar.wp_api_v2_client_android.rest;

import android.text.TextUtils;

import com.afrozaar.wp_api_v2_client_android.WordPressRestInterface;
import com.afrozaar.wp_api_v2_client_android.model.Comment;
import com.afrozaar.wp_api_v2_client_android.model.Media;
import com.afrozaar.wp_api_v2_client_android.model.Meta;
import com.afrozaar.wp_api_v2_client_android.model.Post;
import com.afrozaar.wp_api_v2_client_android.model.Taxonomy;
import com.afrozaar.wp_api_v2_client_android.model.User;
import com.afrozaar.wp_api_v2_client_android.model.dto.PostStreamItem;
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

    private WordPressRestInterface restInterface;

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
        restInterface = retrofit.create(WordPressRestInterface.class);
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
        doRetrofitCall(restInterface.createUser(User.mapFromFields(user)), callback);
    }

    public Call<User> getUser(long userId) {
        return restInterface.getUser(userId);
    }

    public void getUserFromLogin(String login, WordPressRestResponse<User> callback) {
        doRetrofitCall(restInterface.getUserFromLogin(login), callback);
    }

    public Call<User> getUserFromLogin(String login) {
        return restInterface.getUserFromLogin(login);
    }

    public void getUserFromEmail(String email, WordPressRestResponse<User> callback) {
        doRetrofitCall(restInterface.getUserFromEmail(email), callback);
    }

    public Call<User> getUserFromEmail(String email) {
        return restInterface.getUserFromEmail(email);
    }

    public void getUserMe(WordPressRestResponse<User> callback) {
        doRetrofitCall(restInterface.getUserMe(), callback);
    }

    public void updateUser(User user, WordPressRestResponse<User> callback) {
        Map<String, Object> map = User.mapFromFields(user);
        doRetrofitCall(restInterface.updateUser(user.getId(), map), callback);
    }

    public Call<User> updateUser(User user) {
        Map<String, Object> map = User.mapFromFields(user);
        return restInterface.updateUser(user.getId(), map);
    }

    // POSTS

    public void createPost(Post post, WordPressRestResponse<Post> callback) {
        // 201 CREATED on success
        doRetrofitCall(restInterface.createPost(Post.mapFromFields(post)), callback);
    }

    public Call<Post> createPost(Post post) {
        return restInterface.createPost(Post.mapFromFields(post));
    }

    public void getPost(long postId, WordPressRestResponse<Post> callback) {
        Map<String, String> map = new HashMap<>();
        map.put("context", "edit");
        doRetrofitCall(restInterface.getPost(postId, map), callback);
    }

    public Call<Post> getPost(long postId) {
        Map<String, String> map = new HashMap<>();
        map.put("context", "edit");
        return restInterface.getPost(postId, map);
    }

    public void getPostForEdit(long postId, WordPressRestResponse<Post> callback) {
        Map<String, String> map = new HashMap<>();
        map.put("context", "edit");
        doRetrofitCall(restInterface.getPost(postId, map), callback);
    }

    public void getPosts(WordPressRestResponse<List<Post>> callback) {
        doRetrofitCall(restInterface.getPosts(), callback);
    }

    public Call<List<Post>> getPosts() {
        return restInterface.getPosts();
    }

    public Call<List<Post>> getPostsForPage(int startPage) {
        Map<String, String> map = new HashMap<>();
        map.put("page", startPage + "");
        map.put("context", "edit");
        return restInterface.getPosts(map);
    }

    public Call<List<Post>> getPostsForPage(int startPage, int pageSize) {
        Map<String, String> map = new HashMap<>();
        map.put("page", startPage + "");
        map.put("per_page", pageSize + "");
        map.put("context", "edit");
        return restInterface.getPosts(map);
    }

    public Call<List<Post>> getPostsAfterDate(String date) {
        Map<String, String> map = new HashMap<>();
        map.put("after", date);
        map.put("context", "edit");
        return restInterface.getPosts(map);
    }

    public Call<List<Post>> getPostsForAuthor(long authorId, String status) {
        return restInterface.getPostsForAuthor(authorId, status, "edit");
    }

    public void getPostsForAuthor(long authorId, String status, WordPressRestResponse<List<Post>> callback) {
        doRetrofitCall(getPostsForAuthor(authorId, status), callback);
    }

    public void getPostsForTag(String tag, WordPressRestResponse<List<Post>> callback) {
        doRetrofitCall(restInterface.getPostsForTags(tag), callback);
    }

    public void updatePost(Post post, WordPressRestResponse<Post> callback) {
        // 200 on success
        doRetrofitCall(restInterface.updatePost(post.getId(), Post.mapFromFields(post)), callback);
    }

    public Call<Post> updatePost(Post post) {
        return restInterface.updatePost(post.getId(), Post.mapFromFields(post));
    }

    public void deletePost(long postId, boolean force, WordPressRestResponse<Post> callback) {
        // 200 on success
        // 410 GONE on failure
        if (force) {
            doRetrofitCall(restInterface.deletePostForce(postId), callback);
        } else {
            doRetrofitCall(restInterface.deletePost(postId), callback);
        }
    }

    public Call<Post> deletePost(long postId, boolean force) {
        if (force) {
            return restInterface.deletePostForce(postId);
        } else {
            return restInterface.deletePost(postId);
        }
    }

    /**
     * Returns a list of basic Post info for use in stream
     * Only posts with a creation date after given date value will be returned
     *
     * @param date Date filter in format YYYY-mm-ddThh:mm:ss
     */
    public void getPostStream(String date, WordPressRestResponse<List<PostStreamItem>> callback) {
        if (TextUtils.isEmpty(date)) {
            doRetrofitCall(restInterface.getPostStream(), callback);
        } else {
            doRetrofitCall(restInterface.getPostStreamAfterDate(date), callback);
        }
    }

    public Call<List<PostStreamItem>> getPostStream(String date) {
        if (TextUtils.isEmpty(date)) {
            return restInterface.getPostStream();
        } else {
            return restInterface.getPostStreamAfterDate(date);
        }
    }

    public Call<List<PostStreamItem>> getPostFeedForPage(int startingPage) {
        return restInterface.getPostFeedForPage(startingPage);
    }

    /* MEDIA */

    public void createMedia(Media media, File file, WordPressRestResponse<Media> callback) {
        Map<String, RequestBody> map = ContentUtil.makeMediaItemUploadMap(media, file);
        String header = "filename=" + file.getName();

        doRetrofitCall(restInterface.createMedia(header, map), callback);
    }

    public Call<Media> createMedia(Media media, File file) {
        Map<String, RequestBody> map = ContentUtil.makeMediaItemUploadMap(media, file);
        String header;
        if (!TextUtils.isEmpty(media.getCaption())) {
            int extStart = file.getName().lastIndexOf(".");
            String ext = file.getName().substring(extStart);

            String sanitized = media.getCaption().replaceAll("[^[a-z][A-Z][0-9][.]]", "_");
            header = "filename=" + sanitized + ext;
        } else {
            header = "filename=" + file.getName();
        }
        return restInterface.createMedia(header, map);
    }

    public void getMedia(WordPressRestResponse<List<Media>> callback) {
        doRetrofitCall(restInterface.getMedia(), callback);
    }

    public Call<Media> getMedia(long mediaId) {
        return restInterface.getMedia(mediaId);
    }

    public void getMediaForPost(long postId, String mimeType, WordPressRestResponse<List<Media>> callback) {
        doRetrofitCall(restInterface.getMediaForPost(postId, mimeType), callback);
    }

    public Call<List<Media>> getMediaForPost(long postId, String mimeType) {
        return restInterface.getMediaForPost(postId, mimeType);
    }

    public Call<List<Media>> getMediaForSlug(String slug) {
        Map<String, Object> map = new HashMap<>();
        map.put("slug", slug);
        return restInterface.getMediaForSlug(map);
    }

    public void updateMedia(Media media, long mediaId, WordPressRestResponse<Media> callback) {
        doRetrofitCall(restInterface.updateMedia(mediaId, Media.mapFromFields(media)), callback);
    }

    public Call<Media> updateMedia(Media media, long mediaId) {
        return restInterface.updateMedia(mediaId, Media.mapFromFields(media));
    }

    public Call<Media> deleteMedia(long mediaId) {
        return restInterface.deleteMedia(mediaId);
    }

    /* TAXONOMIES */

    public void setTagForPost(long postId, long tagId, WordPressRestResponse<Taxonomy> callback) {
        doRetrofitCall(restInterface.setPostTag(postId, tagId), callback);
    }

    public void getTagsForPost(long postId, WordPressRestResponse<List<Taxonomy>> callback) {
        doRetrofitCall(restInterface.getPostTags(postId), callback);
    }

    public void getTags(WordPressRestResponse<List<Taxonomy>> callback) {
        doRetrofitCall(restInterface.getTags(), callback);
    }

    public void getTagsOrderedByCount(WordPressRestResponse<List<Taxonomy>> callback) {
        Map<String, String> map = new HashMap<>();
        map.put("orderby", "count");
        map.put("order", "desc");

        doRetrofitCall(restInterface.getTagsOrdered(map), callback);
    }

    public Call<List<Taxonomy>> getTagForSlug(String slug) {
        Map<String, String> map = new HashMap<>();
        map.put("search", slug);
        return restInterface.getTagForSlug(map);
    }

    public void setCategoryForPost(long postId, long catId, WordPressRestResponse<Taxonomy> callback) {
        doRetrofitCall(restInterface.setPostCategory(postId, catId), callback);
    }

    public Call<Taxonomy> setCategoryForPost(long postId, long catId) {
        return restInterface.setPostCategory(postId, catId);
    }

    public void getCategoriesForPost(long postId, WordPressRestResponse<List<Taxonomy>> callback) {
        doRetrofitCall(restInterface.getPostCategories(postId), callback);
    }

    public void getCategories(WordPressRestResponse<List<Taxonomy>> callback) {
        doRetrofitCall(restInterface.getCategories(), callback);
    }

    public Call<List<Taxonomy>> getCategoryForSlug(String slug) {
        Map<String, String> map = new HashMap<>();
        map.put("search", slug);
        return restInterface.getCategoryForSlug(map);
    }

    public Call<List<Taxonomy>> getCategories() {
        Map<String, Object> map = new HashMap<>();
        map.put("per_page", 100);
        return restInterface.getCategories(map);
    }

    public void getCategoriesForParent(long parentId, WordPressRestResponse<List<Taxonomy>> callback) {
        Map<String, Object> map = new HashMap<>();
        map.put("parent", parentId);

        doRetrofitCall(restInterface.getCategories(map), callback);
    }

    public Call<List<Taxonomy>> getCategoriesForParent(long parentId) {
        Map<String, Object> map = new HashMap<>();
        map.put("parent", parentId);
        return restInterface.getCategories(map);
    }

    /* META */

    public void createPostMeta(long postId, Meta meta, WordPressRestResponse<Meta> callback) {
        Map<String, Object> map = new HashMap<>();
        map.put("key", meta.getKey());
        map.put("value", meta.getValue());

        doRetrofitCall(restInterface.createPostMeta(postId, map), callback);
    }

    public Call<Meta> createPostMeta(long postId, Meta meta) {
        Map<String, Object> map = new HashMap<>();
        map.put("key", meta.getKey());
        map.put("value", meta.getValue());

        return restInterface.createPostMeta(postId, map);
    }

    public Call<Meta> updatePostMeta(long postId, Meta meta) {
        Map<String, Object> map = new HashMap<>();
        map.put("key", meta.getKey());
        map.put("value", meta.getValue());

        return restInterface.updatePostMeta(postId, meta.getId(), map);
    }

    public Call<List<Meta>> getPostMetas(long postId) {
        return restInterface.getPostMeta(postId);
    }

    public Call<Meta> deletePostMeta(long postId, long metaId) {
        return restInterface.deletePostMeta(postId, metaId);
    }

    /* COMMENTS */

    public void createComment(Comment comment, WordPressRestResponse<Comment> callback) {
        doRetrofitCall(restInterface.createComment(Comment.mapFromFields(comment)), callback);
    }

    public Call<Comment> createComment(Comment comment) {
        return restInterface.createComment(Comment.mapFromFields(comment));
    }

    public void getComments(WordPressRestResponse<List<Comment>> callback) {
        Map<String, Object> map = new HashMap<>();
        map.put("per_page", 100);
        doRetrofitCall(restInterface.getComments(map), callback);
    }

    public Call<List<Comment>> getComments() {
        Map<String, Object> map = new HashMap<>();
        map.put("per_page", 100);
        return restInterface.getComments(map);
    }

    public void getComment(long commentId, WordPressRestResponse<Comment> callback) {
        doRetrofitCall(restInterface.getComment(commentId), callback);
    }

    public Call<Comment> getComment(long commentId) {
        return restInterface.getComment(commentId);
    }

    public void getCommentsForPost(long postId, WordPressRestResponse<List<Comment>> callback) {
        Map<String, Object> map = new HashMap<>();
        map.put("post", postId);
        doRetrofitCall(restInterface.getCommentsForPost(map), callback);
    }

    public Call<List<Comment>> getCommentsForPost(long postId) {
        Map<String, Object> map = new HashMap<>();
        map.put("post", postId);
        return restInterface.getCommentsForPost(map);
    }

    public void updateComment(Comment comment, WordPressRestResponse<Comment> callback) {
        doRetrofitCall(restInterface.updateComment(comment.getId(), Comment.mapFromFields(comment)), callback);
    }

    public Call<Comment> updateComment(Comment comment) {
        return restInterface.updateComment(comment.getId(), Comment.mapFromFields(comment));
    }

    public void deleteComment(long id, WordPressRestResponse<Comment> callback) {
        doRetrofitCall(restInterface.deleteComment(id), callback);
    }

    public Call<Comment> deleteComment(long id) {
        return restInterface.deleteComment(id);
    }

}
