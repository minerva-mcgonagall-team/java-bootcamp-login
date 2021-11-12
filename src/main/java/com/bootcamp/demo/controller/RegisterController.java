package com.bootcamp.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("registerWarning")
public class RegisterController {

    @RequestMapping(value = "/reg", method = RequestMethod.GET)
    public String showRegisterPage(ModelMap model) {
        return "reg";
    }

    @RequestMapping(value = "/reg", method = RequestMethod.POST)
    public String handleRegisterRequest(ModelMap model,
                                        @RequestParam String username,
                                        @RequestParam String password,
                                        @RequestParam String email) {

        return "redirect:/log";
    }
}