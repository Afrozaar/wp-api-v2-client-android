package com.afrozaar.wp_api_v2_client_android.api;

import com.afrozaar.wp_api_v2_client_android.model.wordpress.PostMeta;

import java.util.List;

/**
 * Created by jay on 12/10/15.
 */
public interface PostMetas {

    PostMeta createMeta(Long postId, String key, String value);

    List<PostMeta> getPostMetas(Long postId);

    PostMeta getPostMeta(Long postId, Long metaId);

    PostMeta updatePostMetaValue(Long postId, Long metaId, String value);
    PostMeta updatePostMeta(Long postId, Long metaId, String key, String value);

    boolean deletePostMeta(Long postId, Long metaId);
    boolean deletePostMeta(Long postId, Long metaId, boolean force);
}
