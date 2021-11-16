package com.bootcamp.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ControllerError {


    @RequestMapping("/log-error")
    public String loginError() {
        return "log-error";
    }
}
