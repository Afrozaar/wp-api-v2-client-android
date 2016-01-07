package com.afrozaar.wp_api_v2_client_android;

import android.os.AsyncTask;

import com.afrozaar.wp_api_v2_client_android.exception.PostCreateException;
import com.afrozaar.wp_api_v2_client_android.model.wp_v2.Media;
import com.afrozaar.wp_api_v2_client_android.model.wp_v2.Post;
import com.afrozaar.wp_api_v2_client_android.model.wp_v2.PostStatus;
import com.afrozaar.wp_api_v2_client_android.model.wp_v2.User;
import com.afrozaar.wp_api_v2_client_android.request.SearchRequest;
import com.afrozaar.wp_api_v2_client_android.response.PagedResponse;

import org.springframework.core.io.Resource;

import java.util.List;

/**
 * Created by jay on 12/17/15.
 */
public class AndroidWpClient {

    public interface RestTaskCallback<T> {
        void onTaskComplete(T response);

        void onTaskFailed(Exception e);
    }

    public interface Request<T> {
        T doCall() throws Exception;
    }

    private Client mClient;

    public AndroidWpClient(String baseUrl, String username, String password, boolean debug) {
        mClient = new Client(baseUrl, username, password, debug);
    }

    /* POSTS */

    public void createPost(final Post post, final PostStatus status, RestTaskCallback<Post> callback) throws PostCreateException {
        makeCall(new Request<Post>() {
            @Override
            public Post doCall() throws Exception {
                return mClient.createPost(post, status);
            }
        }, callback);
    }

    public void updatePost(final Post post, RestTaskCallback<Post> callback) {
        makeCall(new Request<Post>() {
            @Override
            public Post doCall() throws Exception {
                return mClient.updatePost(post);
            }
        }, callback);
    }

    public void getPost(final long id, RestTaskCallback<Post> callback) {
        makeCall(new Request<Post>() {
            @Override
            public Post doCall() throws Exception {
                return mClient.getPost(id);
            }
        }, callback);
    }

    public void deletePost(final long id, RestTaskCallback<Post> callback) {
        makeCall(new Request<Post>() {
            @Override
            public Post doCall() throws Exception {
                return mClient.deletePost(new Post().withId(id));
            }
        }, callback);
    }

    public void getPostsForAuthor(final long authorId, final RestTaskCallback<List<Post>> callback) {
        makeCall(new Request<List<Post>>() {
            @Override
            public List<Post> doCall() throws Exception {
                SearchRequest<Post> searchRequest = SearchRequest.Builder.aSearchRequest(Post.class).withParam("author", authorId + "").build();
                PagedResponse<Post> response = mClient.getPagedResponse(searchRequest.usingClient(mClient).build().toUri(), Post.class);

                return response.getList();
            }
        }, callback);
    }

    /* USERS */

    public void createUser(final User user, RestTaskCallback<User> callback) {
        makeCall(new Request<User>() {
            @Override
            public User doCall() throws Exception {
                return mClient.createUser(user);
            }
        }, callback);
    }

    public void updateUser(final User user, RestTaskCallback<User> callback) {
        makeCall(new Request<User>() {
            @Override
            public User doCall() throws Exception {
                return mClient.updateUser(user);
            }
        }, callback);
    }

    /* MEDIA */

    public void createMedia(final Media media, final Resource resource, RestTaskCallback<Media> callback) {
        makeCall(new Request<Media>() {
            @Override
            public Media doCall() throws Exception {
                return mClient.createMedia(media, resource);
            }
        }, callback);
    }

    public void getMedia(final long mediaId, RestTaskCallback<Media> callback) {
        makeCall(new Request<Media>() {
            @Override
            public Media doCall() throws Exception {
                return mClient.getMedia(mediaId);
            }
        }, callback);
    }

    public void updateMedia(final Media media, RestTaskCallback<Media> callback) {
        makeCall(new Request<Media>() {
            @Override
            public Media doCall() throws Exception {
                return mClient.updateMedia(media);
            }
        }, callback);
    }

    public void deleteMedia(final long mediaId, RestTaskCallback<Boolean> callback) {
        makeCall(new Request<Boolean>() {
            @Override
            public Boolean doCall() throws Exception {
                return mClient.deleteMedia(mediaId);
            }
        }, callback);
    }

    private <T> void makeCall(final Request<T> call, final RestTaskCallback<T> callback) {
        RunRestTask<T> task = new RunRestTask<T>(callback);
        task.execute(call);
    }

    private class RunRestTask<T> extends AsyncTask<Request<T>, Void, T> {

        private RestTaskCallback<T> callback;
        private Exception error;

        private RunRestTask(RestTaskCallback<T> callback) {
            this.callback = callback;
        }

        @Override
        protected T doInBackground(Request<T>... params) {
            Request<T> task = params[0];
            try {
                return task.doCall();
            } catch (Exception e) {
                e.printStackTrace();
                error = e;
            }
            return null;
        }

        @Override
        protected void onPostExecute(T response) {
            super.onPostExecute(response);

            if (response != null) {
                callback.onTaskComplete(response);
            } else {
                callback.onTaskFailed(error);
            }
        }
    }
}
