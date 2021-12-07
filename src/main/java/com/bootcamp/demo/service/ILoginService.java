package com.bootcamp.demo.service;

import com.bootcamp.demo.dto.request.LoginRequest;
import com.bootcamp.demo.dto.request.RegisterRequest;
import com.bootcamp.demo.dto.request.UserSessionsRequest;
import com.bootcamp.demo.model.Session;
import com.bootcamp.demo.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Set;

/**
 * LoginService interface used for registration, login and logout
 */
public interface ILoginService {


    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest signupRequest);


    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) ;

    public ResponseEntity<?> getAllSessions(@RequestBody UserSessionsRequest userSessionsRequest);
    boolean logoutUser(User user);
}
