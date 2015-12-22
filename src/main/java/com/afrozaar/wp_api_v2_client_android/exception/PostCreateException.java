package com.afrozaar.wp_api_v2_client_android.exception;

import org.springframework.web.client.HttpClientErrorException;

public class PostCreateException extends WpApiParsedException {

    public PostCreateException(HttpClientErrorException cause) {
        super(ParsedRestException.of(cause));
    }
}
