package com.afrozaar.wp_api_v2_client_android.model.wp_v2;

/**
 * Created by jay on 12/21/15.
 */
public enum Role {
    administrator,
    editor,
    author,
    contributor,
    subscriber;

    public final String value;

    Role(){this.value = this.name().toLowerCase();}
    Role(String value){this.value = value;}
}
