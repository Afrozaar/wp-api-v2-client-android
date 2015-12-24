package com.afrozaar.wp_api_v2_client_android.request;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ObjectUtils;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.Arrays;

/**
 * Created by jay on 12/22/15.
 */
public class RequestEntity<T> extends HttpEntity<T> {
    private final HttpMethod method;
    private final URI url;

    public RequestEntity(HttpMethod method, URI url) {
        this((T)null, (MultiValueMap)null, method, url);
    }

    public RequestEntity(T body, HttpMethod method, URI url) {
        this(body, (MultiValueMap)null, method, url);
    }

    public RequestEntity(MultiValueMap<String, String> headers, HttpMethod method, URI url) {
        this((T)null, headers, method, url);
    }

    public RequestEntity(T body, MultiValueMap<String, String> headers, HttpMethod method, URI url) {
        super(body, headers);
        Assert.notNull(method, "\'method\' is required");
        Assert.notNull(url, "\'url\' is required");
        this.method = method;
        this.url = url;
    }

    public HttpMethod getMethod() {
        return this.method;
    }

    public URI getUrl() {
        return this.url;
    }

    public boolean equals(Object other) {
        if(this == other) {
            return true;
        } else if(!super.equals(other)) {
            return false;
        } else {
            RequestEntity otherEntity = (RequestEntity)other;
            return ObjectUtils.nullSafeEquals(this.method, otherEntity.method) && ObjectUtils.nullSafeEquals(this.url, otherEntity.url);
        }
    }

    public int hashCode() {
        int hashCode = super.hashCode();
        hashCode = 29 * hashCode + ObjectUtils.nullSafeHashCode(this.method);
        hashCode = 29 * hashCode + ObjectUtils.nullSafeHashCode(this.url);
        return hashCode;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder("<");
        builder.append(this.method);
        builder.append(' ');
        builder.append(this.url);
        builder.append(',');
        Object body = this.getBody();
        HttpHeaders headers = this.getHeaders();
        if(body != null) {
            builder.append(body);
            if(headers != null) {
                builder.append(',');
            }
        }

        if(headers != null) {
            builder.append(headers);
        }

        builder.append('>');
        return builder.toString();
    }

    public static RequestEntity.BodyBuilder method(HttpMethod method, URI url) {
        return new RequestEntity.DefaultBodyBuilder(method, url);
    }

    public static RequestEntity.HeadersBuilder<?> get(URI url) {
        return method(HttpMethod.GET, url);
    }

    public static RequestEntity.HeadersBuilder<?> head(URI url) {
        return method(HttpMethod.HEAD, url);
    }

    public static RequestEntity.BodyBuilder post(URI url) {
        return method(HttpMethod.POST, url);
    }

    public static RequestEntity.BodyBuilder put(URI url) {
        return method(HttpMethod.PUT, url);
    }

    public static RequestEntity.BodyBuilder patch(URI url) {
        return method(HttpMethod.PATCH, url);
    }

    public static RequestEntity.HeadersBuilder<?> delete(URI url) {
        return method(HttpMethod.DELETE, url);
    }

    public static RequestEntity.HeadersBuilder<?> options(URI url) {
        return method(HttpMethod.OPTIONS, url);
    }

    private static class DefaultBodyBuilder implements RequestEntity.BodyBuilder {
        private final HttpMethod method;
        private final URI url;
        private final HttpHeaders headers = new HttpHeaders();

        public DefaultBodyBuilder(HttpMethod method, URI url) {
            this.method = method;
            this.url = url;
        }

        public RequestEntity.BodyBuilder header(String headerName, String... headerValues) {
            String[] var3 = headerValues;
            int var4 = headerValues.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                String headerValue = var3[var5];
                this.headers.add(headerName, headerValue);
            }

            return this;
        }

        public RequestEntity.BodyBuilder accept(MediaType... acceptableMediaTypes) {
            this.headers.setAccept(Arrays.asList(acceptableMediaTypes));
            return this;
        }

        public RequestEntity.BodyBuilder acceptCharset(Charset... acceptableCharsets) {
            this.headers.setAcceptCharset(Arrays.asList(acceptableCharsets));
            return this;
        }

        public RequestEntity.BodyBuilder contentLength(long contentLength) {
            this.headers.setContentLength(contentLength);
            return this;
        }

        public RequestEntity.BodyBuilder contentType(MediaType contentType) {
            this.headers.setContentType(contentType);
            return this;
        }

        public RequestEntity.BodyBuilder ifModifiedSince(long ifModifiedSince) {
            this.headers.setIfModifiedSince(ifModifiedSince);
            return this;
        }

        public RequestEntity.BodyBuilder ifNoneMatch(String... ifNoneMatches) {
            this.headers.setIfNoneMatch(Arrays.asList(ifNoneMatches));
            return this;
        }

        public RequestEntity<Void> build() {
            return new RequestEntity(this.headers, this.method, this.url);
        }

        public <T> RequestEntity<T> body(T body) {
            return new RequestEntity(body, this.headers, this.method, this.url);
        }
    }

    public interface BodyBuilder extends RequestEntity.HeadersBuilder<RequestEntity.BodyBuilder> {
        RequestEntity.BodyBuilder contentLength(long var1);

        RequestEntity.BodyBuilder contentType(MediaType var1);

        <T> RequestEntity<T> body(T var1);
    }

    public interface HeadersBuilder<B extends RequestEntity.HeadersBuilder<B>> {
        B header(String var1, String... var2);

        B accept(MediaType... var1);

        B acceptCharset(Charset... var1);

        B ifModifiedSince(long var1);

        B ifNoneMatch(String... var1);

        RequestEntity<Void> build();
    }
}
