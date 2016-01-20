package com.afrozaar.wp_api_v2_client_android.task;

import android.os.AsyncTask;

import com.afrozaar.wp_api_v2_client_android.model.Post;
import com.afrozaar.wp_api_v2_client_android.model.Taxonomy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Response;

/**
 * AsyncTask to make create Post logic a bit simpler.
 * <p/>
 * Normal callback method is kinda useless because the tags and categories (and any other taxonomies)
 * can only be added one at a time.
 *
 * @author Jan-Louis Crafford
 *         Created on 2016/01/18.
 */
public class CreatePostTask extends AsyncTask<Void, Void, String> {

    private Call<Post> mPostCall;

    private List<Call<Taxonomy>> mTagCalls = new ArrayList<>();
    private List<Call<Taxonomy>> mCategoryCalls = new ArrayList<>();

    public static CreatePostTask forPost(Call<Post> postCall) {
        return new CreatePostTask(postCall);
    }

    public CreatePostTask withTag(Call<Taxonomy> tagCall) {
        mTagCalls.add(tagCall);
        return this;
    }

    public CreatePostTask withCategory(Call<Taxonomy> categoryCall) {
        mCategoryCalls.add(categoryCall);
        return this;
    }

    public CreatePostTask(Call<Post> postCall) {
        mPostCall = postCall;
    }

    @Override
    protected String doInBackground(Void... params) {
        try {
            Response<Post> postResponse = mPostCall.execute();
            if (postResponse.isSuccess()) {
                System.out.println("============ post created succesfully");
                long postId = postResponse.body().getId();

                for (Call<Taxonomy> taxonomyCall : mTagCalls) {
                    Response<Taxonomy> response = taxonomyCall.execute();
                    if (response.isSuccess()) {
                        String name = response.body().getName();
                        System.out.println("======= tag set on post , " + postId + "=" + name);
                    }
                }

                for (Call<Taxonomy> taxonomyCall : mCategoryCalls) {
                    Response<Taxonomy> response = taxonomyCall.execute();
                    if (response.isSuccess()) {
                        String name = response.body().getName();
                        System.out.println("======= category set on post , " + postId + "=" + name);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
