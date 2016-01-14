package com.afrozaar.wp_api_v2_client_android;

import com.afrozaar.wp_api_v2_client_android.model.wp_v1.Taxonomy;
import com.afrozaar.wp_api_v2_client_android.model.wp_v1.Media;
import com.afrozaar.wp_api_v2_client_android.model.wp_v1.Meta;
import com.afrozaar.wp_api_v2_client_android.model.wp_v1.Post;
import com.afrozaar.wp_api_v2_client_android.model.wp_v1.User;
import com.afrozaar.wp_api_v2_client_android.util.ContentUtil;
import com.squareup.okhttp.RequestBody;

import java.io.File;
import java.util.List;
import java.util.Map;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.PartMap;
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

    /**
     * Creates new Meta objects for a Post
     *
     * @param postId Id of the Post
     * @param fields Map containing key/value pairs
     * @return The created PostMeta object
     */
    @POST("posts/{id}/meta")
    Call<Meta> createPostMeta(@Path("id") long postId, @Body Map<String, Object> fields);

    @GET("posts/{id}/meta")
    Call<List<Meta>> getPostMeta(@Path("id") long postId);


    @GET("posts/{postId}/meta/{metaId}")
    Call<Meta> getPostMeta(@Path("postId") long postId, @Path("metaId") long metaId);

    @POST("posts/{postId}/meta/{metaId}")
    Call<Meta> updatePostMeta(@Path("postId") long postId, @Path("metaId") long metaId, Meta meta);

    @DELETE("posts/{postId}/meta/{metaId}")
    Call<Meta> deletePostMeta(@Path("postId") long postId, @Path("metaId") long metaId);


    @GET("posts/{postId}/revisions")
    Call<List<Post>> getPostRevisions(@Path("postId") long postId);

    @GET("posts/{postId}/revisions/{revId}")
    Call<Post> getPostRevision(@Path("postId") long postId, @Path("revId") long revId);

    @DELETE("posts/{postId}/revisions/{revId}")
    Call<Post> deltePostRevision(@Path("postId") long postId, @Path("revId") long revId);


    @GET("posts/{postId}/categories")
    Call<List<Taxonomy>> getPostCategories(@Path("postId") long postId);

    @GET("posts/{postId}/categories/{catId}")
    Call<Taxonomy> getPostCategory(@Path("postId") long postId, @Path("catId") long catId);

    @DELETE("posts/{postId}/categories/{catId}")
    Call<Taxonomy> deletePostCategory(@Path("postId") long postId, @Path("catId") long catId);


    @GET("posts/{postId}/tags")
    Call<List<Taxonomy>> getPostTags(@Path("postId") long postId);

    @GET("posts/{postId}/tags/{tagId}")
    Call<Taxonomy> getPostTag(@Path("postId") long postId, @Path("tagId") long catId);

    @DELETE("posts/{postId}/tags/{tagId}")
    Call<Taxonomy> deletePostTag(@Path("postId") long postId, @Path("tagId") long catId);





    /* MEDIA */

    /**
     * Upload a new Media item into WordPress.
     *
     * @param header Content-Disposition header containing filename, eg "filename=file.jpg"
     * @param params Map containing all fields to upload
     * @return Media item created
     * @see ContentUtil#makeMediaItemUploadMap(Media, File)
     */
    @Multipart
    @POST("media")
    Call<Media> createMedia(@Header("Content-Disposition") String header, @PartMap Map<String, RequestBody> params);

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
     * Returns all Media items attached to a Post.
     *
     * @param postId Id of the Post
     * @param type   MIME type of Media
     * @return List of Media objects
     */
    @GET("posts/{id}/media/{type}")
    Call<List<Media>> getMediaForPost(@Path("id") long postId, @Path("type") String type);

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



    /* OTHER */

    @GET("posts")
    Call<List<Post>> getPostsForTags(@Query("filter[tag]") String tag);
}
