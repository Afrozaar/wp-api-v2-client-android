package com.afrozaar.wp_api_v2_client_android.util;

import android.util.Base64;

import com.google.common.collect.Lists;

import org.springframework.http.HttpAuthentication;
import org.springframework.http.HttpBasicAuthentication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

/**
 * Created by jay on 12/10/15.
 */
public class AuthUtil {

    //public static final String WP_ADMIN = "admin";  //WP ADMIN DETAILS : NEEDED FOR CREATE NEW USER
    //public static final String WP_PASSWORD = "atI*pe$nqQ&N6pPcd&";

    public static final String WP_ADMIN = "android";
    public static final String WP_PASSWORD = "T8YsQw@6Mz)Gd(vGGImUU3z6";

    public static <B> HttpEntity<B> addBasicAuthToBody(String user, String pass, B body) {
        if (body instanceof byte[]) {
            return new HttpEntity<B>(body, getHttpHeaders(user, pass, true));
        } else {
            return new HttpEntity<B>(body, getHttpHeaders(user, pass, false));
        }
    }

    public static HttpHeaders getHttpHeaders(String user, String pass, boolean isMedia) {
        HttpAuthentication authHeader = new HttpBasicAuthentication(user, pass);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAuthorization(authHeader);
        //httpHeaders.setContentDispositionFormData("file","testfilename.png");
        if (isMedia) {
            httpHeaders.set("Content-Disposition", "filename=testfile123.jpg");
            httpHeaders.setContentType(MediaType.IMAGE_JPEG);
        }
        httpHeaders.setAccept(Lists.newArrayList(MediaType.APPLICATION_JSON));
        return httpHeaders;
    }

    public static Two<String, String> authTuple(String username, String password) {
        final byte[] encodedAuth = Base64.encode((username + ":" + password).getBytes(), Base64.DEFAULT);
        return Two.of("Authorization", "Basic " + new String(encodedAuth));
    }

}
