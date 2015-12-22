package com.afrozaar.wp_api_v2_client_android.util;

import org.springframework.http.HttpAuthentication;
import org.springframework.http.HttpBasicAuthentication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

/**
 * Created by jay on 12/10/15.
 */
public class AuthUtil {

    public static <B> HttpEntity<B> getBasicAuth(String user, String pass, B body) {
        HttpAuthentication authHeader = new HttpBasicAuthentication(user, pass);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAuthorization(authHeader);
        //httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(body,httpHeaders);
    }
}
