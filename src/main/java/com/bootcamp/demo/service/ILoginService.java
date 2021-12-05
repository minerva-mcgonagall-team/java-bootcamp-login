package com.bootcamp.demo.service;

import com.bootcamp.demo.dto.request.LoginRequest;
import com.bootcamp.demo.dto.request.SignupRequest;
import com.bootcamp.demo.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * LoginService interface used for registration, login and logout
 */
public interface ILoginService {

    /**
     * @param newUser the new user
     * @return true, if user was successfully saved, or false if email is already used
     */
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signupRequest);

    /**
     * if user was successfully logged in, a new session is created
     *
     * @param email    is the email introduced by the user
     * @param password is the password introduced by the user
     * @return true if the user with the given email and password exists in the database, else false
     */
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) ;

    /**
     * Sets the endSession to the local time for each Session from the list of active sessions of a user
     * Updates the sessionRepository with each updated Session
     *
     * @param user is the current logged in user that asks to log out
     * @return true if he was successfully logged out from all his devices (all his sessions were ended), else false
     */
    boolean logoutUser(User user);
}
