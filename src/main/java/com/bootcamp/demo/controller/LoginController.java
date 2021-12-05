package com.bootcamp.demo.controller;


import com.bootcamp.demo.dto.reply.JwtResponse;
import com.bootcamp.demo.dto.request.LoginRequest;
import com.bootcamp.demo.repository.RepositoryFactory;
import com.bootcamp.demo.security.jwt.JwtUtils;
import com.bootcamp.demo.service.LoginService;
import com.bootcamp.demo.service.userDetails.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

private final LoginService loginService;

@Autowired
public LoginController(LoginService loginService){
    this.loginService = loginService;
}


    @RequestMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
            return loginService.login(loginRequest);
    }








}
