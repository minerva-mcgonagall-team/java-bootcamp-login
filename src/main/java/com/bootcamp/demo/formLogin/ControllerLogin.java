package com.bootcamp.demo.formLogin;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ControllerLogin {

    @RequestMapping("/login")
    public String login() {
        return "login";
    }
    }

