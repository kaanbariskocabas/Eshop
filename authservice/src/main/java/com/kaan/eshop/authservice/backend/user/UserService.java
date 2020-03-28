package com.kaan.eshop.authservice.backend.user;

import com.kaan.eshop.authservice.backend.model.User;

/**
 * @author Kaan Kocabas
 */
public interface UserService {

    public User addUser(User user);

    User findByEmail(String email);

}
