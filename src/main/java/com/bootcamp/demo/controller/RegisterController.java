package com.bootcamp.demo.controller;

import com.bootcamp.demo.dto.request.RegisterRequest;
import com.bootcamp.demo.model.Session;
import com.bootcamp.demo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class RegisterController {
    private final LoginService loginService;

    @Autowired
    public RegisterController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest) {
        return loginService.registerUser(registerRequest);
    }

    @GetMapping("/sessions/{userId}")
    public ResponseEntity<List<Session>> getSessionForUser(@PathVariable String userId) {
        List<Session> sessions = loginService.findSessionsFor(userId);

        return new ResponseEntity<>(sessions, HttpStatus.OK);
    }
}