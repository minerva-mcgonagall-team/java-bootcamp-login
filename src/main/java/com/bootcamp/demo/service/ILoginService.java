package com.bootcamp.demo.service;

import com.bootcamp.demo.dto.request.LoginRequest;
import com.bootcamp.demo.dto.request.RegisterRequest;
import com.bootcamp.demo.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * LoginService interface used for registration, login and logout
 */
public interface ILoginService {


    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest signupRequest);


    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest);

    /**
     * Sets the endSession to the local time for each Session from the list of active sessions of a user
     * Updates the sessionRepository with each updated Session
     *
     * @param user is the current logged in user that asks to log out
     * @return true if he was successfully logged out from all his devices (all his sessions were ended), else false
     */
    boolean logoutUser(User user);
}
