package com.afrozaar.wp_api_v2_client_android.exception;

import org.springframework.web.client.HttpStatusCodeException;

public class PageNotFoundException extends WpApiParsedException {

    public PageNotFoundException(HttpStatusCodeException cause) {
        super(ParsedRestException.of(cause));
    }
}
