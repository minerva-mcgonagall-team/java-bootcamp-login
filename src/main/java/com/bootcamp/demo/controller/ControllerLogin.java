package com.bootcamp.demo.controller;

import com.bootcamp.demo.dto.LoginRequest;
import com.bootcamp.demo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ControllerLogin {

    public final LoginService loginService;

    @Autowired
    public ControllerLogin(LoginService loginService) {
        this.loginService = loginService;
    }

    @RequestMapping("/login")
    public boolean login(@RequestBody LoginRequest loginRequest) {
        return loginService.loginUser(loginRequest.getEmail(), loginRequest.getPassword());
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


}
