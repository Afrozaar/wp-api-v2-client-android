package com.afrozaar.wp_api_v2_client_android.api;

import com.afrozaar.wp_api_v2_client_android.model.wordpress.User;

import java.util.List;

/**
 * Created by jay on 12/18/15.
 */
public interface Users {

    User createUser(User user);

    User getUser(long id);

    List<User> getUsers();
}
