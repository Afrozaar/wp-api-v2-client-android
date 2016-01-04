package com.afrozaar.wp_api_v2_client_android;

import android.content.Context;
import android.os.AsyncTask;

import com.afrozaar.wp_api_v2_client_android.exception.PostCreateException;
import com.afrozaar.wp_api_v2_client_android.exception.TermNotFoundException;
import com.afrozaar.wp_api_v2_client_android.exception.WpApiParsedException;
import com.afrozaar.wp_api_v2_client_android.model.wordpress.Link;
import com.afrozaar.wp_api_v2_client_android.model.wordpress.Media;
import com.afrozaar.wp_api_v2_client_android.model.wordpress.Post;
import com.afrozaar.wp_api_v2_client_android.model.wordpress.PostMeta;
import com.afrozaar.wp_api_v2_client_android.model.wordpress.PostStatus;
import com.afrozaar.wp_api_v2_client_android.model.wordpress.Taxonomy;
import com.afrozaar.wp_api_v2_client_android.model.wordpress.Term;
import com.afrozaar.wp_api_v2_client_android.model.wordpress.User;
import com.afrozaar.wp_api_v2_client_android.request.SearchRequest;
import com.afrozaar.wp_api_v2_client_android.response.PagedResponse;
import com.afrozaar.wp_api_v2_client_android.util.ClientConfig;
import com.afrozaar.wp_api_v2_client_android.util.ClientFactory;
import com.google.common.base.Function;
import com.google.common.collect.ImmutableMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;

import java.net.URI;
import java.util.List;
import java.util.Map;

/**
 * Created by jay on 12/17/15.
 */
public class AndroidWpClient {

    private static final Logger LOG = LoggerFactory.getLogger(AndroidWpClient.class);

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

    public <T> void makeCall(final Request<T> call, final RestTaskCallback callback) {
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

    public void createUser(final Map<String, Object> userFields, RestTaskCallback<User> callback){
        makeCall(new Request<User>() {
            @Override
            public User doCall() throws Exception {
                return mClient.createUser(userFields);
            }
        }, callback);
    }

    public void  getUser(final long id, RestTaskCallback<User> callback) {
        makeCall(new Request<User>() {
            @Override
            public User doCall() throws Exception {
                return mClient.getUser(id);
            }
        },callback);
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
                return mClient.createMediaItem(media,resource);
            }
        },callback);
    }

    private class RunRestTask<T> extends AsyncTask<Request<T>,Void,T>{

        private RestTaskCallback callback;
        private Exception error;

        private RunRestTask(RestTaskCallback<T> callback){
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
            if(response != null){
                callback.onTaskComplete(response);
            }else{
                callback.onTaskFailed(error);
            }
            //super.onPostExecute(t);
        }
    }



   /* @Override
    public <T> PagedResponse<T> getPagedResponse(URI uri, Class<T> typeRef) {
        return mClient.getPagedResponse(uri, typeRef);
    }*/

    /*@Override
    public boolean deletePostMeta(Long postId, Long metaId, boolean force) {
        return mClient.deletePostMeta(postId, metaId, force);
    }*/

/*


    @Override
    public SearchRequest<Post> fromPagedResponse(PagedResponse<Post> response, Function<PagedResponse<Post>, String> previousOrNext) {
        return mClient.fromPagedResponse(response, previousOrNext);
    }

    @Override
    public Term deleteTerm(String taxonomy, Term term) throws TermNotFoundException {
        return mClient.deleteTerm(taxonomy, term);
    }

    @Override
    public PostMeta updatePostMeta(Long postId, Long metaId, String key, String value) {
        return mClient.updatePostMeta(postId, metaId, key, value);
    }

    @Override
    public Term getTerm(String taxonomy, Long id) throws TermNotFoundException {
        return mClient.getTerm(taxonomy, id);
    }

    public List<Link> parseLinks(HttpHeaders headers) {
        return mClient.parseLinks(headers);
    }



    @Override
    public boolean deletePostMeta(Long postId, Long metaId) {
        return mClient.deletePostMeta(postId, metaId);
    }

    @Override
    public List<Term> deleteTerms(String taxonomy, Term... terms) {
        return mClient.deleteTerms(taxonomy, terms);
    }

    public <T> void populateEntry(Object value, ImmutableMap.Builder<String, Object> builder, String key) {
        mClient.populateEntry(value, builder, key);
    }

    @Override
    public List<Taxonomy> getTaxonomies() {
        return mClient.getTaxonomies();
    }

    @Override
    public PostMeta updatePostMetaValue(Long postId, Long metaId, String value) {
        return mClient.updatePostMetaValue(postId, metaId, value);
    }

    @Override
    public <T> PagedResponse<T> traverse(PagedResponse<T> response, Function<PagedResponse<?>, String> direction) {
        return mClient.traverse(response, direction);
    }

    @Override
    public PostMeta createMeta(Long postId, String key, String value) {
        return mClient.createMeta(postId, key, value);
    }

    @Override
    public List<Term> getTerms(String taxonomy) {
        return mClient.getTerms(taxonomy);
    }

    @Override
    public PagedResponse<Post> fetchPosts(SearchRequest<Post> search) {
        return mClient.fetchPosts(search);
    }

    @Override
    public Term updateTerm(String taxonomy, Term term) {
        return mClient.updateTerm(taxonomy, term);
    }

    @Override
    public Post deletePost(Post post) {
        return mClient.deletePost(post);
    }

    @Override
    public PostMeta getPostMeta(Long postId, Long metaId) {
        return mClient.getPostMeta(postId, metaId);
    }

    @Override
    public PagedResponse<Post> get(PagedResponse<Post> postPagedResponse, Function<PagedResponse<Post>, String> previousOrNext) {
        return mClient.get(postPagedResponse, previousOrNext);
    }

    @Override
    public Term createTerm(String taxonomy, Term term) throws WpApiParsedException {
        return mClient.createTerm(taxonomy, term);
    }

    @Override
    public List<PostMeta> getPostMetas(Long postId) {
        return mClient.getPostMetas(postId);
    }



    @Override
    public <T> PagedResponse<T> getPagedResponse(String context, Class<T> typeRef, String... expandParams) {
        return mClient.getPagedResponse(context, typeRef, expandParams);
    }

    @Override
    public Taxonomy getTaxonomy(String slug) {
        return mClient.getTaxonomy(slug);
    }*/
}
