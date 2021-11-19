package com.bootcamp.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ControllerLogin {

    private final LoginService loginService;
    //am nevoie de ea pentru maparea POST si GET


    @RequestMapping("/login")
    public String login() {
        return "login";
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

    @GetMapping
    public String loginService(@RequestBody String email, String password) {
        return loginService.loginUser(email, password);
    }

}
