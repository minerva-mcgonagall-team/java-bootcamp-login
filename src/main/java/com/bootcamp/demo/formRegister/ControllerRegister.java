package com.bootcamp.demo.formRegister;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ControllerRegister {

    @RequestMapping("/register")
    public String register() {
        return "register";
    }


}