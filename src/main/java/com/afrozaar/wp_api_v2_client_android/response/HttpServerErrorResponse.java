package com.afrozaar.wp_api_v2_client_android.response;

import com.google.gson.annotations.SerializedName;

/**
 * @author Jan-Louis Crafford
 *         Created on 2016/01/08.
 */
public class HttpServerErrorResponse {

    public static final String CODE_EXISTING_USER_LOGIN = "existing_user_login";

    @SerializedName("code")
    private String mCode;

    @SerializedName("message")
    private String mMessage;

    @SerializedName("data")
    private String mData;

    public HttpServerErrorResponse() {
    }

    public String getCode() {
        return mCode;
    }

    public String getMessage() {
        return mMessage;
    }

}
