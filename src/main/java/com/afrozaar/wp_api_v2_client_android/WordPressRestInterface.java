package com.afrozaar.wp_api_v2_client_android;

import com.afrozaar.wp_api_v2_client_android.model.wp_v1.Media;
import com.afrozaar.wp_api_v2_client_android.model.wp_v1.Post;
import com.afrozaar.wp_api_v2_client_android.model.wp_v1.User;
import com.afrozaar.wp_api_v2_client_android.model.wp_v2.PostMeta;
import com.squareup.okhttp.RequestBody;

import java.util.List;
import java.util.Map;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * REST API interface for WP REST API plugin.
 *
 * @author Jan-Louis Crafford
 *         Created on 2016/01/12.
 */
public interface WordPressRestInterface {

    /* POSTS */

    /**
     * Creates a new Post.
     *
     * @param postFields Map of Post fields
     * @return The created Post object
     */
    @POST("posts")
    Call<Post> createPost(@Body Map<String, Object> postFields);

    /**
     * Gets all Posts.
     *
     * @return List of Post objects
     */
    @GET("posts")
    Call<List<Post>> getPosts();

    /**
     * Gets a single Post.
     *
     * @param postId Id of the Post
     * @return Post object
     */
    @GET("posts/{id}")
    Call<Post> getPost(@Path("id") long postId);

    /**
     * Gets all Posts created by a User.
     *
     * @param authorId Id of the User
     * @return List of Post objects for the User
     */
    @GET("posts")
    Call<List<Post>> getPostsForAuthor(@Query("author") long authorId);

    /**
     * Updates an existing Post.
     *
     * @param postId     Id of the Post
     * @param postFields Map of the fields to update
     * @return The updated Post object
     */
    @POST("posts/{id}")
    Call<Post> updatePost(@Path("id") long postId, @Body Map<String, Object> postFields);

    /**
     * Deletes a Post.
     *
     * @param postId Id of the Post
     * @return Post object that was deleted
     */
    @DELETE("posts/{id}")
    Call<Post> deletePost(@Path("id") long postId);

    /* MEDIA */

    @Multipart
    @POST("media")
    Call<Media> createMedia(@Part("title") String title, @Part("post") long postId,
                            @Part("alt_text") String altText, @Part("caption") String caption,
                            @Part("description") String description, @Part("data") RequestBody file);

    @POST("media")
    Call<Media> createMedia(@Body Map<String, Object> fields);

    /**
     * Gets all Media objects.
     *
     * @return List of Media objects
     */
    @GET("media")
    Call<List<Media>> getMedia();

    /**
     * Returns a single Media item.
     *
     * @param mediaId Id of the Media item
     * @return The Media object
     */
    @GET("media/{id}")
    Call<Media> getMedia(@Path("id") long mediaId);

    /**
     * Updates a Media item.
     *
     * @param mediaId Id the Media item
     * @param fields  Fields to update
     * @return The updated Media object
     */
    @POST("media/{id}")
    Call<Media> updateMedia(@Path("id") long mediaId, @Body Map<String, Object> fields);

    /**
     * Deletes a Media item.
     *
     * @param mediaId Id of the Media item
     * @return The deleted Media object
     */
    @DELETE("media/{id}")
    Call<Media> deleteMedia(@Path("id") long mediaId);

    /* META */

    /**
     * Creates new Meta objects for a Post
     *
     * @param postId Id of the Post
     * @param fields Map containing key/value pairs
     * @return The created PostMeta object
     */
    @POST("posts/{id}/meta")
    Call<PostMeta> createPostMeta(@Path("id") long postId, @Body Map<String, Object> fields);




    /* USERS */

    /**
     * Creates a new WordPress user.
     *
     * @param fields Map of fields
     * @return The created User object
     */
    @POST("users")
    Call<User> createUser(@Body Map<String, Object> fields);

    /**
     * Gets existing User using username.
     *
     * @param username Login username of the User
     * @return The User object
     */
    @GET("users/login/{username}")
    Call<User> getUserFromLogin(@Path("username") String username);


}
