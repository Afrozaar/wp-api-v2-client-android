package com.afrozaar.wp_api_v2_client_android;

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
import com.afrozaar.wp_api_v2_client_android.request.Request;
import com.afrozaar.wp_api_v2_client_android.request.SearchRequest;
import com.afrozaar.wp_api_v2_client_android.response.PagedResponse;
import com.afrozaar.wp_api_v2_client_android.util.AuthUtil;
import com.afrozaar.wp_api_v2_client_android.util.LogUtils;
import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by jay on 12/9/15.
 */
public class Client implements Wordpress {

    private static final Logger LOG = LoggerFactory.getLogger(Client.class);

    private final Predicate<Link> next = new Predicate<Link>() {
        @Override
        public boolean apply(Link input) {
            return Strings.NEXT.equals(input.getRel());
        }
    };
    private final Predicate<Link> previous = new Predicate<Link>() {
        @Override
        public boolean apply(Link input) {
            return Strings.PREV.equals(input.getRel());
        }
    };

    public final String baseUrl;
    final private String username;
    final private String password;
    final private boolean debug;
    private RestTemplate restTemplate = new RestTemplate();

    public Client(String baseUrl, String username, String password, boolean debug) {
        this.baseUrl = baseUrl;
        this.username = username;
        this.password = password;
        this.debug = debug;
        restTemplate.setMessageConverters(Lists.newArrayList(new GsonHttpMessageConverter(), new FormHttpMessageConverter(), new ByteArrayHttpMessageConverter()));
    }

    @Override
    public Post createPost(final Map<String, Object> postFields, PostStatus status) throws PostCreateException {
        final ImmutableMap<String, Object> post = new ImmutableMap.Builder<String, Object>()
                .putAll(postFields)
                .put("status", status.value)
                .build();

        try {
            LogUtils.d("****************************" + post.toString());
            return doExchange1(Request.POSTS, HttpMethod.POST, Post.class, forExpand(), null, post).getBody();
        } catch (HttpClientErrorException e) {
            throw new PostCreateException(e);
        }
    }


    @Override
    public Post createPost(Post post, PostStatus status) throws PostCreateException {
        return createPost(Post.fieldsFrom(post), status);
    }

    @Override
    public User createUser(User user) {
        return createUser(User.fieldsFrom(user));
    }

    public User createUser(final Map<String, Object> userFields) {
        final ImmutableMap<String, Object> user = new ImmutableMap.Builder<String, Object>()
                .putAll(userFields)
                .build();
        return doExchange1(Request.USERS, HttpMethod.POST, User.class, forExpand(), null, user).getBody();
    }

    @Override
    public User getUser(long id) {

        return doExchange1(Request.USER, HttpMethod.GET, User.class, forExpand(id), null, null).getBody();
    }

    @Override
    public Post getPost(long id) {
        final ResponseEntity<Post> exchange = doExchange1(Request.POST, HttpMethod.GET, Post.class, forExpand(id), null, null);

        return exchange.getBody();
    }

    @Override
    public Post updatePost(Post post) {

        // update post is not as straight forward :(
        // the fields need to be checked for being empty (not null) and should not be included

        final ResponseEntity<Post> exchange = doExchange1(Request.POST, HttpMethod.PUT, Post.class, forExpand(post.getId()), ImmutableMap.<String, Object>of(), Post.fieldsFrom(post));
        return exchange.getBody();
    }

    @Override
    public Post deletePost(Post post) {
        final ResponseEntity<Post> exchange = doExchange1(Request.POST, HttpMethod.DELETE, Post.class, forExpand(post.getId()), null, null);// Deletion of a post returns the post's data before removing it.
        Preconditions.checkArgument(exchange.getStatusCode().is2xxSuccessful());
        return exchange.getBody();
    }

    @Override
    public Media createMediaItem(Media media, Resource resource) throws WpApiParsedException {
        try {
            /*final ImmutableMap<String, Object> map = ImmutableMap.<String, Object>builder()
                    .putAll(Media.fieldsFrom(media))
                    //.put("file",resource)
                    .build();*/
//            final MultiValueMap<String, Object> uploadMap = Media.fieldsFrom(media, resource);

            return doExchange1(Request.MEDIAS, HttpMethod.POST, Media.class, forExpand(), null, toByteArray(resource)).getBody();
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            throw WpApiParsedException.of(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private byte[] toByteArray(Resource resource) throws IOException {

        InputStream is = resource.getInputStream();
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();

        int nRead;
        byte[] data = new byte[16384];

        while ((nRead = is.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }

        buffer.flush();
        return buffer.toByteArray();
    }

    @Override
    public List<Media> getMedia() {
        List<Media> collected = new ArrayList<>();
        PagedResponse<Media> pagedResponse = this.getPagedResponse(Request.MEDIAS, Media.class);
        collected.addAll(pagedResponse.getList());
        while (pagedResponse.hasNext()) {
            pagedResponse = this.traverse(pagedResponse, PagedResponse.NEXT);
            collected.addAll(pagedResponse.getList());
        }

        return collected;
    }

    @Override
    public Media getMedia(Integer id) {
        final ResponseEntity<Media> exchange = doExchange1(Request.MEDIA, HttpMethod.GET, Media.class, forExpand(id), null, null);

        return exchange.getBody();
    }

    @Override
    public PagedResponse<Post> fetchPosts(SearchRequest<Post> search) {
        final URI uri = search.forHost(baseUrl, CONTEXT).build().toUri();
        final ResponseEntity<Post[]> exchange = doExchange(HttpMethod.GET, uri, Post[].class, null);

        final HttpHeaders headers = exchange.getHeaders();
        final List<Link> links = parseLinks(headers);
        final List<Post> posts = Arrays.asList(exchange.getBody());

        LOG.trace("{} returned {} posts.", uri, posts.size());

        return PagedResponse.Builder.aPagedResponse(Post.class)
                .withPages(headers)
                .withPosts(posts)
                .withSelf(uri.toASCIIString())
                .withNext(link(links, next))
                .withPrevious(link(links, previous))
                .build();
    }

    @Override
    public PostMeta createMeta(Long postId, String key, String value) {
        final ImmutableMap<String, String> body = ImmutableMap.of("key", key, "value", value);
        final ResponseEntity<PostMeta> exchange = doExchange1(Request.METAS, HttpMethod.POST, PostMeta.class, forExpand(postId), null, body);
        return exchange.getBody();
    }

    @Override
    public List<PostMeta> getPostMetas(Long postId) {
        final ResponseEntity<PostMeta[]> exchange = doExchange1(Request.METAS, HttpMethod.GET, PostMeta[].class, forExpand(postId), null, null);
        return Arrays.asList(exchange.getBody());
    }

    @Override
    public PostMeta getPostMeta(Long postId, Long metaId) {
        final ResponseEntity<PostMeta> exchange = doExchange1(Request.META, HttpMethod.GET, PostMeta.class, forExpand(postId, metaId), null, null);
        return exchange.getBody();
    }

    @Override
    public PostMeta updatePostMetaValue(Long postId, Long metaId, String value) {
        return updatePostMeta(postId, metaId, null, value);
    }

    @Override
    public PostMeta updatePostMeta(Long postId, Long metaId, String key, String value) {
        ImmutableMap.Builder<String, Object> builder = new ImmutableMap.Builder<>();
        /*populateEntry(() -> key, builder, "key");
        populateEntry(() -> value, builder, "value");*/

        final ResponseEntity<PostMeta> exchange = doExchange1(Request.META, HttpMethod.POST, PostMeta.class, forExpand(postId, metaId), null, builder.build());

        return exchange.getBody();
    }

    @Override
    public boolean deletePostMeta(Long postId, Long metaId) {
        final ResponseEntity<Map> exchange = doExchange1(Request.META, HttpMethod.DELETE, Map.class, forExpand(postId, metaId), null, null);
        Preconditions.checkArgument(exchange.getStatusCode().is2xxSuccessful(), String.format("Expected success on post meta delete request: /posts/%s/meta/%s", postId, metaId));

        return exchange.getStatusCode().is2xxSuccessful();
    }

    @Override
    public boolean deletePostMeta(Long postId, Long metaId, boolean force) {
        final ResponseEntity<Map> exchange = doExchange1(Request.META, HttpMethod.DELETE, Map.class, forExpand(postId, metaId), ImmutableMap.<String, Object>of("force", force), null);
        Preconditions.checkArgument(exchange.getStatusCode().is2xxSuccessful(), String.format("Expected success on post meta delete request: /posts/%s/meta/%s", postId, metaId));

        return exchange.getStatusCode().is2xxSuccessful();
    }

    @Override
    public List<Taxonomy> getTaxonomies() {
        final ResponseEntity<Taxonomy[]> exchange = doExchange1(Request.TAXONOMIES, HttpMethod.GET, Taxonomy[].class, forExpand(), null, null);
        return Arrays.asList(exchange.getBody());
    }

    @Override
    public Taxonomy getTaxonomy(String slug) {
        return doExchange1(Request.TAXONOMY, HttpMethod.GET, Taxonomy.class, forExpand(slug), null, null).getBody();
    }

    @Override
    public Term createTerm(String taxonomy, Term term) throws WpApiParsedException {
        try {
            return doExchange1(Request.TERMS, HttpMethod.POST, Term.class, forExpand(taxonomy), null, term.asMap()).getBody();
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            final WpApiParsedException exception = WpApiParsedException.of(e);
            LOG.error("Could not create {} term '{}'. {} ", taxonomy, term.getName(), exception.getMessage(), exception);
            throw exception;
        }
    }

    @Override
    public List<Term> getTerms(String taxonomy) {
        List<Term> collected = new ArrayList<>();
        PagedResponse<Term> pagedResponse = this.getPagedResponse(Request.TERMS, Term.class, taxonomy);
        collected.addAll(pagedResponse.getList());
        while (pagedResponse.hasNext()) {
            pagedResponse = this.traverse(pagedResponse, PagedResponse.NEXT);
            collected.addAll(pagedResponse.getList());
        }
        return collected;
    }

    @Override
    public Term getTerm(String taxonomy, Long id) throws TermNotFoundException {
        try {
            return doExchange1(Request.TERM, HttpMethod.GET, Term.class, forExpand(taxonomy, id), null, null).getBody();
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode().is4xxClientError() && e.getStatusCode().value() == 404) {
                throw new TermNotFoundException(e);
            } else {
                throw e;
            }
        }
    }

    @Override
    public Term updateTerm(String taxonomy, Term term) {
        return doExchange1(Request.TERM, HttpMethod.POST, Term.class, forExpand(taxonomy, term.getId()), null, term.asMap()).getBody();
    }

    @Override
    public Term deleteTerm(String taxonomy, Term term) throws TermNotFoundException {
        try {
            return doExchange1(Request.TERM, HttpMethod.DELETE, Term.class, forExpand(taxonomy, term.getId()), null, null).getBody();
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode().is4xxClientError() && e.getStatusCode().value() == 404) {
                throw new TermNotFoundException(e);
            } else {
                throw e;
            }
        }
    }

    @Override
    public List<Term> deleteTerms(String taxonomy, Term... terms) {
        List<Term> deletedTerms = new ArrayList<>(terms.length);

        for (Term term : terms) {
            try {
                deletedTerms.add(deleteTerm(taxonomy, term));
            } catch (TermNotFoundException e) {
                LOG.error("Error ", e);
            }
        }

        return deletedTerms;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> PagedResponse<T> getPagedResponse(String context, Class<T> typeRef, String... expandParams) {
        final URI uri = Request.of(context).usingClient(this).buildAndExpand(expandParams).toUri();
        return getPagedResponse(uri, typeRef);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> PagedResponse<T> getPagedResponse(final URI uri, Class<T> typeRef) {
        try {
            final ResponseEntity<T[]> exchange = doExchange0(HttpMethod.GET, uri, (Class<T[]>) Class.forName("[L" + typeRef.getName() + ";"), null);
            final HttpHeaders headers = exchange.getHeaders();
            final List<Link> links = parseLinks(headers);

            final List<T> body = Arrays.asList((T[]) exchange.getBody()); // Ugly... but the only way to get the generic stuff working

            return PagedResponse.Builder.aPagedResponse(typeRef)
                    .withPages(headers)
                    .withPosts(body)
                    .withSelf(uri.toASCIIString())
                    .withNext(link(links, next))
                    .withPrevious(link(links, previous))
                    .build();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> PagedResponse<T> traverse(PagedResponse<T> response, Function<PagedResponse<?>, String> direction) {
        final URI uri = response.getUri(direction);
        return getPagedResponse(uri, response.getClazz());
    }

    public List<Link> parseLinks(HttpHeaders headers) {
        //Link -> [<http://johan-wp/wp-json/wp/v2/posts?page=2>; rel="next"]

        Optional<List<String>> linkHeader = Optional.fromNullable(headers.get(Strings.HEADER_LINK));
        if (linkHeader.isPresent()) {
            final String rawResponse = linkHeader.get().get(0);
            final String[] links = rawResponse.split(", ");
            List<Link> list = new ArrayList();
            for (String link : links) {
                String[] linkData = link.split("; ");
                final String href = linkData[0].replace("<", "").replace(">", "");
                final String rel = linkData[1].substring(4).replace("\"", "");
                list.add(Link.of(href, rel));
            }
            return list;
            /*return Arrays.stream(links)
                    .map(link -> { // <http://johan-wp/wp-json/wp/v2/posts?page=2>; rel="next"
                String[] linkData = link.split("; ");
                final String href = linkData[0].replace("<", "").replace(">", "");
                final String rel = linkData[1].substring(4).replace("\"", "");
                return Link.of(href, rel);
            }).collect(Collectors.toList());*/
        } else {
            return Collections.emptyList();
        }

    }

    @Override
    public PagedResponse<Post> get(PagedResponse<Post> postPagedResponse, Function<PagedResponse<Post>, String> previousOrNext) {
        return fetchPosts(fromPagedResponse(postPagedResponse, previousOrNext));
    }

    @Override
    public SearchRequest<Post> fromPagedResponse(PagedResponse<Post> response, Function<PagedResponse<Post>, String> previousOrNext) {
        return Request.fromLink(previousOrNext.apply(response), CONTEXT);
    }


    private <T> ResponseEntity<T> doExchange(HttpMethod method, URI uri, Class<T> typeRef, T body) {
        return doExchange0(method, uri, typeRef, body);
    }

    private <T, B> ResponseEntity<T> doExchange0(HttpMethod method, URI uri, Class<T> typeRef, B body) {
        HttpEntity httpEntity = AuthUtil.addBasicAuthToBody(username, password, body);
        final ResponseEntity<T> exchange = restTemplate.exchange(uri, method, httpEntity, typeRef);
        return exchange;
    }

    private <T, B> ResponseEntity<T> doExchange0(HttpMethod method, UriComponents uriComponents, Class<T> typeRef, B body) {
        return doExchange0(method, uriComponents.toUri(), typeRef, body);
    }

    private <T, B> ResponseEntity<T> doExchange1(String context, HttpMethod method, Class<T> typeRef, Object[] buildAndExpand, Map<String, Object> queryParams, B body) {
        final UriComponentsBuilder builder = Request.of(context).usingClient(this);
        if (queryParams != null) {
            for (Map.Entry<String, Object> temp : queryParams.entrySet()) {
                builder.queryParam(temp.getKey(), temp.getValue());
            }
        }
        return doExchange0(method, builder.buildAndExpand(buildAndExpand), typeRef, body);
    }

    private Optional<String> link(List<Link> links, Predicate<? super Link> linkPredicate) {

        Function<? super Link, String> function = new Function<Link, String>() {
            @Override
            public String apply(Link input) {
                return input.getHref();
            }
        };

        Optional<Link> objectOptional = FluentIterable.from(links).filter(linkPredicate).first();
        return objectOptional.transform(function);

        /*return links.stream()
                .filter(linkPredicate)
                .map(Link::getHref)
                .findFirst();*/
    }

    /*private void debugRequest(RequestEntity<?> entity) {
        if (debug) {
            LOG.debug("Request Entity: {}", entity);
        }
    }*/

    private void debugHeaders(HttpHeaders headers) {
        if (debug) {
            LOG.debug("Response Headers:");
            for (Map.Entry<String, List<String>> temp : headers.entrySet()) {
                LOG.debug("{} -> {}", temp.getKey(), temp.getValue());
            }
        }
    }

    private Object[] forExpand(Object... values) {
        return values;
    }

}
