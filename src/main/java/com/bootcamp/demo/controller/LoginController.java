package com.bootcamp.demo.controller;


import com.bootcamp.demo.dto.request.LoginRequest;
import com.bootcamp.demo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/auth")
public class LoginController {

    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }


    @RequestMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        return loginService.login(loginRequest);
    }
}
