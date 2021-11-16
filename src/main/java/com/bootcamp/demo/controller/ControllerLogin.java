package com.bootcamp.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ControllerLogin {

    @RequestMapping("/log")
    public String login() {
        return "log";
    }

    @RequestMapping("/")
    public String index() {
        return "index";
    }


}
