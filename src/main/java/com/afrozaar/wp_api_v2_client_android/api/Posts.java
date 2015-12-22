package com.afrozaar.wp_api_v2_client_android.api;

import com.afrozaar.wp_api_v2_client_android.exception.PostCreateException;
import com.afrozaar.wp_api_v2_client_android.model.wordpress.Post;
import com.afrozaar.wp_api_v2_client_android.model.wordpress.PostStatus;
import com.afrozaar.wp_api_v2_client_android.request.SearchRequest;
import com.afrozaar.wp_api_v2_client_android.response.PagedResponse;
import com.google.common.base.Function;

import java.util.Map;

/**
 * Created by jay on 12/10/15.
 */
public interface Posts {

    Function<PagedResponse<Post>, String> next = new Function<PagedResponse<Post>, String>() {
        @Override
        public String apply(PagedResponse<Post> input) {
            return input.getNext().get();
        }
    };

    Function<PagedResponse<Post>, String> prev = new Function<PagedResponse<Post>, String>() {
        @Override
        public String apply(PagedResponse<Post> input) {
            return input.getPrevious().get();
        }
    };

    /**
     * <pre>
     * GET /posts
     * GET /posts?page=1
     * GET /posts?page=2&meta_key=foo&meta_value=bar
     * </pre>
     */
    PagedResponse<Post> fetchPosts(SearchRequest<Post> search);

    /**
     * @param post {@code Map<String, Object>}
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

    Post getPost(long id);

    /**
     * <pre>
     * PUT /post/{id}
     * </pre>
     */
    Post updatePost(Post post);

    Post deletePost(Post post);

    SearchRequest<Post> fromPagedResponse(PagedResponse<Post> response, Function<PagedResponse<Post>, String> uri);

    PagedResponse<Post> get(PagedResponse<Post> postPagedResponse, Function<PagedResponse<Post>, String> previousOrNext);


}
