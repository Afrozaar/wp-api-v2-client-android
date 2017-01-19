package com.afrozaar.wp_api_v2_client_android.rest.interceptor;

import java.io.IOException;

import okhttp3.Authenticator;
import okhttp3.Credentials;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;

/**
 * @author Jan-Louis Crafford
 *         Created on 2016/01/20.
 */
public class OkHttpAuthenticator implements Authenticator {

    private String mUser;
    private String mPass;

    public OkHttpAuthenticator(String user, String pass) {
        mUser = user;
        mPass = pass;
    }

    @Override
    public Request authenticate(Route route, Response response) throws IOException {
        String credential = Credentials.basic(mUser, mPass);

        return response.request()
                .newBuilder()
                .header("Authorization", credential)
                .build();
    }
}
