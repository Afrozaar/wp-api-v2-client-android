package com.afrozaar.wp_api_v2_client_android.exception;

import com.google.gson.Gson;

import org.springframework.web.client.HttpStatusCodeException;

import java.util.Map;

public class ParsedRestException {

    //private static ObjectMapper mapper;
    private static Gson mapper;

    private final String code;
    private final String errorMessage;
    private final Object data;
    private final HttpStatusCodeException cause;

    public ParsedRestException(HttpStatusCodeException cause, String code, String errorMessage, Object data) {
        this.cause = cause;
        this.code = code;
        this.errorMessage = errorMessage;
        this.data = data;
    }

    public static ParsedRestException of(HttpStatusCodeException cause) {
        Map exceptionMap = getMapper().fromJson(cause.getResponseBodyAsString(), Map.class);
        final String errorMessage = (String) exceptionMap.get("message");
        final String code = (String) exceptionMap.get("code");
        final Object data = exceptionMap.get("data");
        return new ParsedRestException(cause, code, errorMessage, data);
    }

    public String getCode() {
        return code;
    }

    public Object getData() {
        return data;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public HttpStatusCodeException getCause() {
        return cause;
    }

    private static Gson getMapper() {
        return mapper == null ? mapper = new Gson() : mapper;
    }
}
