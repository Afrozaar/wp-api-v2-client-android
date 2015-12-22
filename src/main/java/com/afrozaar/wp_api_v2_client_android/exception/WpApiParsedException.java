package com.afrozaar.wp_api_v2_client_android.exception;

import com.google.gson.Gson;

import org.springframework.web.client.HttpStatusCodeException;

import static java.lang.String.format;

public class WpApiParsedException extends Exception {

    private static Gson mapper;

    private final String code;
    private final String errorMessage;
    private final Object data;

    public WpApiParsedException(ParsedRestException parsed) {
        this(parsed.getCause().getMessage(), parsed.getCause(), parsed.getErrorMessage(), parsed.getCode(), parsed.getData());
    }

    private WpApiParsedException(String message, HttpStatusCodeException cause, String errorMessage, String code, Object data) {
        super(format("%s - %s", message, errorMessage), cause);
        this.code = code;
        this.errorMessage = errorMessage;
        this.data = data;
    }

    public static WpApiParsedException of(ParsedRestException parsed) {
        return new WpApiParsedException(parsed.getCause().getMessage(), parsed.getCause(), parsed.getErrorMessage(), parsed.getCode(), parsed.getData());
    }

    public static WpApiParsedException of(HttpStatusCodeException cause) {
        return of(ParsedRestException.of(cause));
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

    private static Gson getMapper() {
        return mapper == null ? mapper = new Gson() : mapper;
    }
}
