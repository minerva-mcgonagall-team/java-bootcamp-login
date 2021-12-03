package com.bootcamp.demo.service;

import com.bootcamp.demo.model.Session;
import com.bootcamp.demo.model.User;

import java.util.Set;

/**
 * LoginService interface used for registration, login and logout
 */
public interface ILoginService {

    /**
     * @param newUser the new user
     * @return true, if user was successfully saved, or false if email is already used
     */
    boolean registerUser(User newUser);

    /**
     * if user was successfully logged in, a new session is created
     *
     * @param email    is the email introduced by the user
     * @param password is the password introduced by the user
     * @return true if the user with the given email and password exists in the database, else false
     */
    boolean loginUser(String email, String password);

    /**
     * Sets the endSession to the local time for each Session from the list of active sessions of a user
     * Updates the sessionRepository with each updated Session
     *
     * @param user is the current logged in user that asks to log out
     * @return true if he was successfully logged out from all his devices (all his sessions were ended), else false
     */
    boolean logoutUser(User user);

    public Set<Session> getAllSessions(User user);
}
