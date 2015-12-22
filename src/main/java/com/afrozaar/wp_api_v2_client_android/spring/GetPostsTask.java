package com.afrozaar.wp_api_v2_client_android.spring;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.afrozaar.wp_api_v2_client_android.model.wordpress.Post;

import org.springframework.http.HttpMethod;

import java.net.URI;

/**
 * @author Jan-Louis Crafford
 *         Created on 2015/12/03.
 */
public class GetPostsTask extends BaseRestTask<Post[]> {


    public GetPostsTask(Context context) {
        super(context, HttpMethod.GET, Post[].class);

        //URI uri = mBuilder.
       // System.out.println("===== URI  : " + uri.toString());

        //setURI(uri);
    }

    @Override
    protected void onPostExecute(Post[] posts) {
        super.onPostExecute(posts);

        System.out.println("=========== GOT ME SOME POSTS!");
        for (Post post : posts) {
            System.out.println(post.toString());
        }
    }

}
