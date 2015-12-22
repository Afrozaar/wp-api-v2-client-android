package com.afrozaar.wp_api_v2_client_android.exception;

import org.springframework.web.client.HttpClientErrorException;

public class TermNotFoundException extends WpApiParsedException {

    public TermNotFoundException(HttpClientErrorException cause) {
        super(ParsedRestException.of(cause));
    }
}
