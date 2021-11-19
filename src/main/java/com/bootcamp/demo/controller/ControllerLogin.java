package com.bootcamp.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ControllerLogin {



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

}
