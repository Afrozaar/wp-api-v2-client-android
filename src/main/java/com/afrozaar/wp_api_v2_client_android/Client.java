package com.afrozaar.wp_api_v2_client_android;

import com.afrozaar.wp_api_v2_client_android.api.Taxonomies;
import com.afrozaar.wp_api_v2_client_android.exception.PageNotFoundException;
import com.afrozaar.wp_api_v2_client_android.exception.PostCreateException;
import com.afrozaar.wp_api_v2_client_android.exception.TermNotFoundException;
import com.afrozaar.wp_api_v2_client_android.exception.WpApiParsedException;
import com.afrozaar.wp_api_v2_client_android.model.wordpress.Link;
import com.afrozaar.wp_api_v2_client_android.model.wordpress.Media;
import com.afrozaar.wp_api_v2_client_android.model.wordpress.Page;
import com.afrozaar.wp_api_v2_client_android.model.wordpress.Post;
import com.afrozaar.wp_api_v2_client_android.model.wordpress.PostMeta;
import com.afrozaar.wp_api_v2_client_android.model.wordpress.PostStatus;
import com.afrozaar.wp_api_v2_client_android.model.wordpress.Taxonomy;
import com.afrozaar.wp_api_v2_client_android.model.wordpress.Term;
import com.afrozaar.wp_api_v2_client_android.model.wordpress.User;
import com.afrozaar.wp_api_v2_client_android.request.Request;
import com.afrozaar.wp_api_v2_client_android.request.RequestEntity;
import com.afrozaar.wp_api_v2_client_android.request.SearchRequest;
import com.afrozaar.wp_api_v2_client_android.response.PagedResponse;
import com.afrozaar.wp_api_v2_client_android.util.AuthUtil;
import com.afrozaar.wp_api_v2_client_android.util.LogUtils;
import com.afrozaar.wp_api_v2_client_android.util.Two;
import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableMap;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author Jan-Louis Crafford
 *         Created on 2016/01/05.
 */
public class Client implements Wordpress {

    private RestTemplate restTemplate = new RestTemplate();
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

    public Client(String baseUrl, String username, String password, boolean debug) {
        this.baseUrl = baseUrl;
        this.username = username;
        this.password = password;
        this.debug = debug;
    }

    private <T> ResponseEntity<T> doExchange(HttpMethod method, URI uri, Class<T> typeRef, T body) {
        return doExchange0(method, uri, typeRef, body);
    }

    private <T, B> ResponseEntity<T> doExchange0(HttpMethod method, URI uri, Class<T> typeRef, B body) {
        final Two<String, String> authTuple = AuthUtil.authTuple(username, password);
        final RequestEntity<B> entity = RequestEntity.method(method, uri).header(authTuple.a, authTuple.b).body(body);
        final ResponseEntity<T> exchange = restTemplate.exchange(uri, method, entity, typeRef);
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

    private Object[] forExpand(Object... values) {
        return values;
    }


    @Override
    public <T> PagedResponse<T> search(SearchRequest<T> search) {
        final URI uri = search.usingClient(this).build().toUri();
        return getPagedResponse(uri, search.getClazz());
    }

    @Override
    public <T> PagedResponse<T> traverse(PagedResponse<T> response, Function<PagedResponse<?>, String> direction) {
        final URI uri = response.getUri(direction);
        return getPagedResponse(uri, response.getClazz());
    }

    @Override
    public <T> PagedResponse<T> getPagedResponse(URI uri, Class<T> typeRef) {
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

    @Override
    public <T> PagedResponse<T> getPagedResponse(String context, Class<T> typeRef, String... expandParams) {
        final URI uri = Request.of(context).usingClient(this).buildAndExpand(expandParams).toUri();
        return getPagedResponse(uri, typeRef);
    }

    public List<Link> parseLinks(HttpHeaders headers) {
        //Link -> [<http://johan-wp/wp-json/wp/v2/posts?page=2>; rel="next"]

        Optional<List<String>> linkHeader = Optional.fromNullable(headers.get(Strings.HEADER_LINK));
        if (linkHeader.isPresent()) {
            final String rawResponse = linkHeader.get().get(0);
            final String[] links = rawResponse.split(", ");
            List<Link> list = new ArrayList<>();
            for (String link : links) {
                String[] linkData = link.split("; ");
                final String href = linkData[0].replace("<", "").replace(">", "");
                final String rel = linkData[1].substring(4).replace("\"", "");
                list.add(Link.of(href, rel));
            }
            return list;
        } else {
            return Collections.emptyList();
        }
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
    }

    /* POSTS */

    @Override
    public Post createPost(Map<String, Object> postFields, PostStatus status) throws PostCreateException {
        final ImmutableMap<String, Object> post = new ImmutableMap.Builder<String, Object>()
                .putAll(postFields)
                .put("status", status.value)
                .build();
        try {
            return doExchange1(Request.POST, HttpMethod.POST, Post.class, forExpand(), null, post).getBody();
        } catch (HttpClientErrorException e) {
            throw new PostCreateException(e);
        }
    }

    @Override
    public Post createPost(Post post, PostStatus status) throws PostCreateException {
        return createPost(Post.fieldsFrom(post), status);
    }

    @Override
    public Post getPost(Long id) {
        final ResponseEntity<Post> exchange = doExchange1(Request.POST, HttpMethod.POST, Post.class, forExpand(id), null, null);
        return exchange.getBody();
    }

    @Override
    public Post updatePost(Post post) {
        final ResponseEntity<Post> exchange = doExchange1(Request.POST, HttpMethod.POST, Post.class, forExpand(post.getId()), ImmutableMap.<String, Object>of(), Post.fieldsFrom(post));
        return exchange.getBody();
    }

    @Override
    public Post updatePostField(Long postId, String field, Object value) {
        return doExchange1(Request.POST, HttpMethod.PUT, Post.class, forExpand(postId), null, ImmutableMap.of(field, value)).getBody();
    }

    @Override
    public Post deletePost(Post post) {
        final ResponseEntity<Post> exchange = doExchange1(Request.POST, HttpMethod.DELETE, Post.class, forExpand(post.getId()), null, null);
        Preconditions.checkArgument(exchange.getStatusCode().is2xxSuccessful());
        return exchange.getBody();
    }

    /* MEDIA */

    @Override
    public Post setPostFeaturedImage(Long postId, Media media) {
        Preconditions.checkArgument("image".equals(media.getMediaType()), "Can not set non-image media type as featured image.");
        return updatePostField(postId, Post.JSON_FIELD_FEATURED_IMAGE, media.getId());
    }

    @Override
    public Media createMedia(Media media, Resource resource) throws WpApiParsedException {
        return doExchange1(Request.MEDIAS, HttpMethod.POST, Media.class, forExpand(), null, Media.fieldsFrom(media)).getBody();
    }

    @Override
    public List<Media> getMedia() {
        List<Media> collected = new ArrayList<>();
        PagedResponse<Media> pagedResponse = getPagedResponse(Request.MEDIAS, Media.class);
        collected.addAll(pagedResponse.getList());
        while (pagedResponse.hasNext()) {
            pagedResponse = traverse(pagedResponse, PagedResponse.NEXT);
            collected.addAll(pagedResponse.getList());
        }
        return collected;
    }

    @Override
    public Media getMedia(Long id) {
        return doExchange1(Request.MEDIA, HttpMethod.GET, Media.class, forExpand(id), null, null).getBody();
    }

    @Override
    public Media updateMedia(Media media) {
        return doExchange1(Request.MEDIA, HttpMethod.POST, Media.class, forExpand(media.getId()), null, Media.fieldsFrom(media)).getBody();
    }

    @Override
    public boolean deleteMedia(Media media) {
        final ResponseEntity<Media> exchange = doExchange1(Request.MEDIA, HttpMethod.DELETE, Media.class, forExpand(media.getId()), null, null);
        return exchange.getStatusCode().is2xxSuccessful();
    }

    @Override
    public boolean deleteMedia(Media media, boolean force) {
        final ResponseEntity<Media> exchange = doExchange1(Request.MEDIA, HttpMethod.DELETE, Media.class, forExpand(media.getId()), ImmutableMap.<String, Object>of("force", force), null);
        return exchange.getStatusCode().is2xxSuccessful();
    }

    /* META */

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
        builder.put(PostMeta.JSON_FIELD_KEY, key)
                .put(PostMeta.JSON_FIELD_VALUE, value);

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

    /* TAXONOMIES */

    @Override
    public List<Taxonomy> getTaxonomies() {
        // TODO implement this
        return null;
    }

    @Override
    public Taxonomy getTaxonomy(String slug) {
        return doExchange1(Request.TAXONOMY, HttpMethod.GET, Taxonomy.class, forExpand(slug), null, null).getBody();
    }

    /* TERMS */

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
                e.printStackTrace();
            }
        }

        return deletedTerms;
    }

    private List<Term> getAllTermsForEndpoint(final String endpoint) {
        List<Term> collected = new ArrayList<>();
        PagedResponse<Term> pagedResponse = this.getPagedResponse(endpoint, Term.class);
        collected.addAll(pagedResponse.getList());
        while (pagedResponse.hasNext()) {
            pagedResponse = this.traverse(pagedResponse, PagedResponse.NEXT);
            collected.addAll(pagedResponse.getList());
        }
        return collected;
    }

    /* TAGS */

    @Override
    public Term createTag(Term tagTerm) throws WpApiParsedException {
        try {
            return doExchange1(Request.TAGS, HttpMethod.POST, Term.class, forExpand(), tagTerm.asMap(), null).getBody();
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            final WpApiParsedException exception = WpApiParsedException.of(e);
            LogUtils.e("Could not create tag " + tagTerm.getName(), exception);
            throw exception;
        }
    }

    @Override
    public List<Term> getTags() {
        return getAllTermsForEndpoint(Request.TAGS);
    }

    @Override
    public Term getTag(Long id) throws TermNotFoundException {
        try {
            return doExchange1(Request.TAG, HttpMethod.GET, Term.class, forExpand(id), null, null).getBody();
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode().is4xxClientError() && e.getStatusCode().value() == 404) {
                throw new TermNotFoundException(e);
            } else {
                throw e;
            }
        }
    }

    @Override
    public Term deleteTag(Term tagTerm) throws TermNotFoundException {
        // TODO implement deleteTAg
        return null;
    }

    @Override
    public Term createPostTag(Post post, Term tag) throws WpApiParsedException {
        final Term termToUse = tag.getId() != 0 ? tag : createTag(tag);
        return doExchange1(Request.POST_TERM, HttpMethod.POST, Term.class, forExpand(post.getId(), Taxonomies.TAGS, termToUse.getId()), null, termToUse.asMap()).getBody();
    }

    @Override
    public List<Term> getPostTags(Post post) {
        return Arrays.asList(doExchange1(Request.POST_TERMS, HttpMethod.GET, Term[].class, forExpand(post.getId(), Taxonomies.TAGS), null, null).getBody());
    }

    @Override
    public Term deletePostTag(Post post, Term tagTerm, boolean force) throws TermNotFoundException {
        try {
            return doExchange1(Request.POST_TERM, HttpMethod.DELETE, Term.class, forExpand(post.getId(), Taxonomies.TAGS, tagTerm.getId()), ImmutableMap.<String, Object>of("force", force), null).getBody();
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode().is4xxClientError() && e.getStatusCode().value() == 404) {
                throw new TermNotFoundException(e);
            } else {
                throw e;
            }
        }
    }

    @Override
    public Term getPostTag(Post post, Term tagTerm) throws TermNotFoundException {
        try {
            return doExchange1(Request.POST_TERM, HttpMethod.GET, Term.class, forExpand(post.getId(), TAGS, tagTerm.getId()), null, null).getBody();
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode().is4xxClientError() && e.getStatusCode().value() == 404) {
                throw new TermNotFoundException(e);
            } else {
                throw e;
            }
        }
    }

    @Override
    public Term updateTag(Term tag) {
        return doExchange1(Request.TAG, HttpMethod.POST, Term.class, forExpand(tag.getId()), null, tag.asMap()).getBody();
    }

    /* CATEGORIES */

    @Override
    public Term getCategory(Long id) {
        return doExchange1(Request.CATEGORY, HttpMethod.GET, Term.class, forExpand(id), null, null).getBody();
    }

    @Override
    public List<Term> getCategories() {
        return getAllTermsForEndpoint(Request.CATEGORIES);
    }

    @Override
    public Term createCategory(Term categoryTerm) {
        return doExchange1(Request.CATEGORIES, HttpMethod.POST, Term.class, forExpand(), null, categoryTerm.asMap()).getBody();
    }

    @Override
    public Term deleteCategory(Term categoryTerm) throws TermNotFoundException {
        try {
            return doExchange1(Request.CATEGORY, HttpMethod.DELETE, Term.class, forExpand(categoryTerm.getId()), null, null).getBody();
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode().is4xxClientError() && e.getStatusCode().value() == 404) {
                throw new TermNotFoundException(e);
            } else {
                throw e;
            }
        }
    }

    @Override
    public List<Term> deleteCategories(Term... terms) {
        List<Term> deletedTerms = new ArrayList<>(terms.length);

        for (Term term : terms) {
            try {
                deletedTerms.add(deleteCategory(term));
            } catch (TermNotFoundException e) {
                LogUtils.e("Error ", e);
            }
        }

        return deletedTerms;
    }

    /* PAGE */

    @Override
    public Page createPage(Page page, PostStatus status) {
        final Map<String, Object> map = page.asMap();
        final ImmutableMap<String, Object> pageFields = new ImmutableMap.Builder<String, Object>()
                .putAll(map)
                .put("status", status.value)
                .build();

        return doExchange1(Request.PAGES, HttpMethod.POST, Page.class, forExpand(), null, pageFields).getBody();
    }

    @Override
    public Page getPage(Long pageId) throws PageNotFoundException {
        try {
            return getPage(pageId, "view");
        } catch (HttpClientErrorException e) {
            throw new PageNotFoundException(e);
        }
    }

    @Override
    public Page getPage(Long pageId, String context) {
        return doExchange1(Request.PAGE, HttpMethod.GET, Page.class, forExpand(pageId), ImmutableMap.<String, Object>of("context", context), null).getBody();
    }

    @Override
    public Page updatePage(Page page) {
        return doExchange1(Request.PAGE, HttpMethod.POST, Page.class, forExpand(page.getId()), null, page.asMap()).getBody();
    }

    @Override
    public Page deletePage(Page page) {
        return doExchange1(Request.PAGE, HttpMethod.DELETE, Page.class, forExpand(page.getId()), null, null).getBody();
    }

    @Override
    public Page deletePage(Page page, boolean force) {
        return doExchange1(Request.PAGE, HttpMethod.DELETE, Page.class, forExpand(page.getId()), ImmutableMap.<String, Object>of("force", force), null).getBody();
    }

    /* USERS */

    @Override
    public List<User> getUsers() {
        List<User> collected = new ArrayList<>();
        PagedResponse<User> usersResponse = this.getPagedResponse(Request.USERS, User.class);
        collected.addAll(usersResponse.getList());
        while (usersResponse.hasNext()) {
            usersResponse = traverse(usersResponse, PagedResponse.NEXT);
            collected.addAll(usersResponse.getList());
        }
        return collected;
    }

    @Override
    public User createUser(User user, String username, String password) {
        user.withUsername(username)
                .withPassword(password);
        return doExchange1(Request.USERS, HttpMethod.POST, User.class, forExpand(), null, User.fieldsFrom(user)).getBody();
    }

    @Override
    public User getUser(long userId) {
        return doExchange1(Request.USER, HttpMethod.GET, User.class, forExpand(userId), null, null).getBody();
    }

    @Override
    public User getUser(long userId, String context) {
        return doExchange1(Request.USER, HttpMethod.GET, User.class, forExpand(userId), ImmutableMap.<String, Object>of("context", context), null).getBody();
    }

    @Override
    public User deleteUser(User user) {
        return doExchange1(Request.USER, HttpMethod.DELETE, User.class, forExpand(user.getId()), ImmutableMap.<String, Object>of("force", true), null).getBody();
    }

    @Override
    public User updateUser(User user) {
        throw new UnsupportedOperationException("Not Yet Implemented");
    }
}
