package com.afrozaar.wp_api_v2_client_android.response;

import com.afrozaar.wp_api_v2_client_android.Strings;
import com.afrozaar.wp_api_v2_client_android.request.Request;
import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.collect.FluentIterable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;

import java.net.URI;
import java.util.List;

/**
 * Created by jay on 12/10/15.
 */
public class PagedResponse<T> {

    final Class<T> clazz;

    public static final Function<PagedResponse<?>, String> NEXT = new Function<PagedResponse<?>, String>() {
        @Override
        public String apply(PagedResponse<?> input) {
            return input.getNext().get();
        }
    };

    public static final Function<PagedResponse<?>, String> PREV = new Function<PagedResponse<?>, String>() {
        @Override
        public String apply(PagedResponse<?> input) {
            return input.getPrevious().get();
        }
    };

    private static final Logger LOG = LoggerFactory.getLogger(PagedResponse.class);
    // captures the state of a response from wordpress
    final String self;
    final String next;
    final String previous;
    final int pages;
    final List<T> list;

    public PagedResponse(Class<T> clazz, String self, String next, String previous, int pages, List<T> list) {
        this.clazz = clazz;
        this.self = self;
        this.next = next;
        this.previous = previous;
        this.pages = pages;
        this.list = list;
    }

    public boolean hasNext() {
        return (next != null);
    }

    public Optional<String> getNext() {
        return Optional.of(next);
    }

    public boolean hasPrevious() {
        return (previous != null);
    }

    public Optional<String> getPrevious() {
        return Optional.of(previous);
    }

    public String getSelf() {
        return self;
    }

    public List<T> getList() {
        return list;
    }

    public void debug() {
        LOG.trace("response.self      = {}", this.self);
        LOG.trace("response.prev      = {}", this.previous);
        LOG.trace("response.next      = {}", this.next);
        LOG.trace("response.pages     = {}", this.pages);
        LOG.trace("response.list.size = {}", this.list.size());
    }

    public URI getUri(Function<PagedResponse<?>, String> direction) {
        return Request.fromLink(direction.apply(this));
    }

    public Class<T> getClazz() {
        return clazz;
    }

    public static class Builder<BT> {
        private String next;
        // captures the state of a response from wordpress
        private String self;
        private String previous;
        private List<BT> posts;
        private int pages;
        private Class<BT> t1;

        private Builder(Class<BT> t1) {
            this.t1 = t1;
        }

        public static <BT> Builder<BT> aPagedResponse(Class<BT> t) {
            return new Builder<>(t);
        }

        public Builder<BT> withNext(Optional<String> next) {
            if (next.isPresent()) {
                this.next = next.get();
            }
            return this;
        }

        public Builder<BT> withSelf(String self) {
            this.self = self;
            return this;
        }

        public Builder<BT> withPrevious(Optional<String> previous) {
            if (previous.isPresent()) {
                this.previous = previous.get();
            }
            return this;
        }

        public Builder<BT> withPosts(List<BT> posts) {
            this.posts = posts;
            return this;
        }

        public PagedResponse<BT> build() {
            return new PagedResponse<>(t1, self, next, previous, pages, posts);
        }

        public Builder<BT> withPages(int pages) {
            this.pages = pages;
            return this;
        }

        public Builder<BT> withPages(HttpHeaders headers) {

            Optional<String> objectOptional = FluentIterable.from(headers.get(Strings.HEADER_TOTAL_PAGES)).first();
            if (objectOptional.isPresent()) {
                Builder.this.withPages(Integer.valueOf(pages));
            }
            return this;
        }
    }
}
