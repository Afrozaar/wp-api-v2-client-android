package com.afrozaar.wp_api_v2_client_android.request;

import com.afrozaar.wp_api_v2_client_android.model.wp_v2.Post;
import com.google.common.collect.ImmutableMap;

import java.util.List;
import java.util.Map;

/**
 * Created by jay on 12/10/15.
 */
public class UpdatePostRequest extends Request {

    public UpdatePostRequest(String uri, Map<String, List<String>> params) {
        super(uri, params);
    }

    public static UpdatePostRequest forPost(Post post) {
        return new UpdatePostRequest(Request.POST, ImmutableMap.<String,List<String>>of());
    }
}
