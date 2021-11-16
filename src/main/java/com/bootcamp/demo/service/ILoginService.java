package com.bootcamp.demo.service;

import com.bootcamp.demo.model.User;

/**
 * LoginService interface used for registration, login and logout
 */
public interface ILoginService {

    boolean registerUser(User new_user);

    boolean loginUser(String email, String password);

    boolean logoutUser(User user);
}
