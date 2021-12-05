package com.bootcamp.demo.controller;

import com.bootcamp.demo.dto.LoginRequest;
import com.bootcamp.demo.model.Session;
import com.bootcamp.demo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ControllerLogin {

    public final LoginService loginService;

    public ControllerLogin (@Autowired LoginService loginService){
        this.loginService =loginService;
    }

    @RequestMapping("/login")
    public boolean login(@RequestBody LoginRequest loginRequest) {
        return loginService.loginUser(loginRequest.getEmail(),loginRequest.getPassword());
    }
    //Mapare pe login

    @RequestMapping("/")
    public String index() {
        return "index";
    }
    //Mapare pe index(pagina în care-l primim)

    @RequestMapping("/login-error")
    public String loginError() {
        return "login-error";
    }
    //Mapare pe eroare de login, adică nu a găsit niciun user:(

    @GetMapping("/sessions/{userId}")
    public ResponseEntity<List<Session>> getSessionForUser(@PathVariable String userId) {
        List<Session> sessions = loginService.findSessionsFor(userId);

        return new ResponseEntity<>(sessions, HttpStatus.OK);
    }
}
