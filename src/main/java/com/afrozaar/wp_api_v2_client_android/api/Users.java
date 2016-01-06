package com.afrozaar.wp_api_v2_client_android.api;


import com.afrozaar.wp_api_v2_client_android.model.wordpress.User;

import java.util.List;

public interface Users {
    List<User> getUsers();

    User createUser(User user, String username, String password);

    User getUser(long userId);

    User getUser(long userId, String context);

    User updateUser(User user);

    User deleteUser(User user);
}
