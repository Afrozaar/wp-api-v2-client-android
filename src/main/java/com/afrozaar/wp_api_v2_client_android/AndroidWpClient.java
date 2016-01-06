package com.afrozaar.wp_api_v2_client_android;

import android.os.AsyncTask;

import com.afrozaar.wp_api_v2_client_android.exception.PostCreateException;
import com.afrozaar.wp_api_v2_client_android.exception.WpApiParsedException;
import com.afrozaar.wp_api_v2_client_android.model.wordpress.Media;
import com.afrozaar.wp_api_v2_client_android.model.wordpress.Post;
import com.afrozaar.wp_api_v2_client_android.model.wordpress.PostStatus;
import com.afrozaar.wp_api_v2_client_android.model.wordpress.User;
import com.afrozaar.wp_api_v2_client_android.request.SearchRequest;
import com.afrozaar.wp_api_v2_client_android.response.PagedResponse;

import org.springframework.core.io.Resource;

import java.util.List;
import java.util.Map;

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

    public <T> void makeCall(final Request<T> call, final RestTaskCallback<T> callback) {
        RunRestTask<T> task = new RunRestTask<T>(callback);
        task.execute(call);
    }

    public void updatePost(final Post post, RestTaskCallback<Post> callback) {
        makeCall(new Request<Post>() {
            @Override
            public Post doCall() throws Exception {
                return mClient.updatePost(post);
            }
        }, callback);
    }


    public void createPost(final Map<String, Object> postFields, final PostStatus status, RestTaskCallback<Post> callback) throws PostCreateException {
        makeCall(new Request<Post>() {
            @Override
            public Post doCall() throws Exception {
                return mClient.createPost(postFields, status);
            }

        }, callback);
    }

    public void createPost(final Post post, final PostStatus status, RestTaskCallback<Post> callback) throws PostCreateException {
        makeCall(new Request<Post>() {
            @Override
            public Post doCall() throws Exception {
                return mClient.createPost(post, status);
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

    public void getPostsForAuthor(final long id, final RestTaskCallback<List<Post>> callback) {
        makeCall(new Request<List<Post>>() {
            @Override
            public List<Post> doCall() throws Exception {
                SearchRequest<Post> searchRequest = SearchRequest.Builder.<Post>aSearchRequest().withParam("author", id + "").build();
                PagedResponse<Post> response = mClient.fetchPosts(searchRequest);
                return response.getList();
            }
        }, callback);
    }

    public void createUser(final User user, RestTaskCallback<User> callback) {
        makeCall(new Request<User>() {
            @Override
            public User doCall() throws Exception {
                return mClient.createUser(user);
            }
        }, callback);
    }

    public void createUser(final Map<String, Object> userFields, RestTaskCallback<User> callback) {
        makeCall(new Request<User>() {
            @Override
            public User doCall() throws Exception {
                return mClient.createUser(userFields);
            }
        }, callback);
    }

    public void getUser(final long id, RestTaskCallback<User> callback) {
        makeCall(new Request<User>() {
            @Override
            public User doCall() throws Exception {
                return mClient.getUser(id);
            }
        }, callback);
    }

    public void getUsers(RestTaskCallback<List<User>> callback) {
        makeCall(new Request<List<User>>() {
            @Override
            public List<User> doCall() throws Exception {
                return mClient.getUsers();
            }
        }, callback);
    }

    public void getMedia(final Integer id, RestTaskCallback<Media> callback) {
        makeCall(new Request<Media>() {
            @Override
            public Media doCall() throws Exception {
                return mClient.getMedia(id);
            }
        }, callback);
    }

    public void getMedia(RestTaskCallback<List<Media>> callback) {
        makeCall(new Request<List<Media>>() {
            @Override
            public List<Media> doCall() throws Exception {
                return mClient.getMedia();
            }
        }, callback);
    }

    public void createMediaItem(final Media media, final Resource resource, RestTaskCallback<Media> callback) throws WpApiParsedException {

        makeCall(new Request<Media>() {
            @Override
            public Media doCall() throws Exception {
                return mClient.createMediaItem(media, resource);
            }
        }, callback);
    }

    private class RunRestTask<T> extends AsyncTask<Request<T>, Void, T> {

        private RestTaskCallback callback;
        private Exception error;

        private RunRestTask(RestTaskCallback<T> callback) {
            this.callback = callback;
        }

        @Override
        protected T doInBackground(Request<T>... params) {
            Request<T> task = params[0];
            try {
                T t = task.doCall();
                return t;
            } catch (Exception e) {
                e.printStackTrace();
                error = e;
            }
            return null;
        }

        @Override
        protected void onPostExecute(T response) {
            if (response != null) {
                callback.onTaskComplete(response);
            } else {
                callback.onTaskFailed(error);
            }
            //super.onPostExecute(t);
        }
    }
}
