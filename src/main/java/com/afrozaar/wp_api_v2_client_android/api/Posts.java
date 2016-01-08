package com.afrozaar.wp_api_v2_client_android.api;


import com.afrozaar.wp_api_v2_client_android.exception.PostCreateException;
import com.afrozaar.wp_api_v2_client_android.model.wp_v1.Post;
import com.afrozaar.wp_api_v2_client_android.model.wp_v2.PostStatus;

import java.util.Map;

public interface Posts {

    /**
     * @param post   {@code Map<String, Object>}
     * @param status
     * @return Created {@link Post}
     * @throws PostCreateException
     * @see <a href="http://wp-api.org/#posts_create-a-post">Create a Post - v1 documentation</a>
     * <pre>
     *     status - Post status of the post:
     *     * draft,
     *     * publish,
     *     * pending,
     *     * future,
     *     * private
     *      or any custom registered status.
     *     If providing a status of future, you must specify a date in order for the post to be published as expected.
     *     Default is draft. (string) optional
     * </pre>
     */
    Post createPost(Map<String, Object> post, PostStatus status) throws PostCreateException;

    Post createPost(Post post, PostStatus status) throws PostCreateException;

    Post getPost(Long id);

    /**
     * <pre>
     * PUT /post/{id}
     * </pre>
     */
    Post updatePost(Post post);

    Post updatePostField(Long postId, String field, Object value);

    Post deletePost(Post post);
}
