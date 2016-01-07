package com.afrozaar.wp_api_v2_client_android.model.wp_v2;

/**
 * Created by jay on 12/10/15.
 */

public enum PostStatus {
    draft,
    publish,
    pending,
    future,
    private_("private");

    public final String value;

    PostStatus() {
        this.value = this.name().toLowerCase();
    }

    PostStatus(String value) {
        this.value = value;
    }
}