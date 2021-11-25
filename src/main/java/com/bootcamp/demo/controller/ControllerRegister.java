package com.bootcamp.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("registrationWarning")
public class ControllerRegister {

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String register() {
        return "registration";
    }
    //Pentru a cere date de verificare din DataBase

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String handleRegisterRequest(@RequestParam String firstName,
                                        @RequestParam String lastName,
                                        @RequestParam String gender,
                                        @RequestParam String password,
                                        @RequestParam String emaill,
                                        @RequestParam String telephone) {

        return "redirect:/login";
    }
    //Pentru a oferi date de înregistrare din DataBase
}