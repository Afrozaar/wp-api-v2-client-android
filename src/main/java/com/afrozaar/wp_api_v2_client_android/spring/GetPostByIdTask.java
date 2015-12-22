package com.afrozaar.wp_api_v2_client_android.spring;

import android.content.Context;

import com.afrozaar.wp_api_v2_client_android.model.wordpress.Post;

import org.springframework.http.HttpMethod;

/**
 * @author Jan-Louis Crafford
 *         Created on 2015/12/04.
 */
public class GetPostByIdTask extends BaseRestTask<Post> {

    public GetPostByIdTask(Context context, int postId) {
        super(context, HttpMethod.GET, Post.class);

        //setURI(mBuilder.forPost(postId).getUri());
    }

    @Override
    protected void onPostExecute(Post post) {
        super.onPostExecute(post);

        System.out.println("=-===== post");
        System.out.println(post.toString());
    }
}
