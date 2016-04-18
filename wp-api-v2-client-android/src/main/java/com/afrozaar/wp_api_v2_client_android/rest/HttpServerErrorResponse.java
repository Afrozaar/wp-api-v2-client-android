package com.afrozaar.wp_api_v2_client_android.rest;

import com.afrozaar.wp_api_v2_client_android.util.LogUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.annotations.SerializedName;

import okhttp3.ResponseBody;

/**
 * @author Jan-Louis Crafford
 *         Created on 2016/01/08.
 */
public class HttpServerErrorResponse {

    public static final String CODE_EXISTING_USER_LOGIN = "existing_user_login";
    public static final String CODE_EXISTING_USER_EMAIL = "existing_user_email";

    public static final String CODE_FORBIDDEN = "rest_forbidden";
    public static final String CODE_NOT_LOGGED_IN = "rest_not_logged_in";  // 401

    @SerializedName("code")
    private String mCode;

    @SerializedName("message")
    private String mMessage;

    //@SerializedName("data")
    //private String mData;

    public static HttpServerErrorResponse from(ResponseBody body) {
        HttpServerErrorResponse response = null;
        try {
            response = new Gson().fromJson(body.charStream(), HttpServerErrorResponse.class);
        } catch (NullPointerException e) {
            LogUtils.w("Response body was null", e);
        } catch (JsonSyntaxException e) {
            LogUtils.w("Unable to parse JSON", e);
        }

        return response;
    }

    public static HttpServerErrorResponse from(Throwable throwable) {
        HttpServerErrorResponse response = new HttpServerErrorResponse();
        response.mMessage = throwable.getMessage();
        return response;
    }

    public HttpServerErrorResponse() {
    }

    public String getCode() {
        return mCode;
    }

    public String getMessage() {
        return mMessage;
    }

    @Override
    public String toString() {
        return "HttpServerErrorResponse{" +
                "mCode='" + mCode + '\'' +
                ", mMessage='" + mMessage + '\'' +
                //", mData='" + mData + '\'' +
                '}';
    }
}
