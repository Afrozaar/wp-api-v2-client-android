package com.afrozaar.wp_api_v2_client_android.rest;

import android.text.TextUtils;

import com.afrozaar.wp_api_v2_client_android.util.LogUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.util.List;

import okhttp3.ResponseBody;

/**
 * @author Jan-Louis Crafford
 *         Created on 2016/01/08.
 */
public class HttpServerErrorResponse {

    public static final String CODE_EXISTING_USER_LOGIN = "existing_user_login";
    public static final String CODE_EXISTING_USER_EMAIL = "existing_user_email";

    public static final String CODE_USER_EMAIL = "user_email";
    public static final String CODE_USER_EMAIL_MESSAGE = "Sorry, that email address is already used!";

    public static final String CODE_USER_NAME = "user_name";
    public static final String CODE_USER_NAME_MESSAGE = "Sorry, that username already exists!";

    public static final String CODE_FORBIDDEN = "rest_forbidden";
    public static final String CODE_NOT_LOGGED_IN = "rest_not_logged_in";  // 401

    private String code;
    private String message;
    private WPRestErrorData data;
    private List<WPRestError> additional_errors;

    // TODO maybe replace code & error with details from first additional_error
    // to make checking it easier?

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
        response.message = throwable.getMessage();
        return response;
    }

    private HttpServerErrorResponse() {
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public boolean errorUserNameExists() {
        return hasError(CODE_USER_NAME);
    }

    public boolean errorUserEmailExists() {
        return hasError(CODE_USER_EMAIL);
    }

    private boolean hasError(String errorCode) {
        if (additional_errors != null) {
            for (WPRestError error : additional_errors) {
                if (TextUtils.equals(error.code, errorCode)) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public String toString() {
        return "HttpServerErrorResponse{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                ", additional_errors=" + additional_errors +
                '}';
    }

    private class WPRestError {

        public String code;
        public String message;

        public WPRestErrorData data;

        @Override
        public String toString() {
            return "WPRestError{" +
                    "code='" + code + '\'' +
                    ", message='" + message + '\'' +
                    ", data=" + data +
                    '}';
        }
    }

    private class WPRestErrorData {

        public int status;

        @Override
        public String toString() {
            return "WPRestErrorData{" +
                    "status=" + status +
                    '}';
        }
    }
}
