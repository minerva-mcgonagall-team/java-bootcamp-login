package com.bootcamp.demo;

import com.bootcamp.demo.model.Session;
import com.bootcamp.demo.service.ISessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class LoginController {
    private ISessionService sessionService;

    public LoginController(@Autowired ISessionService sessionService) {
        this.sessionService = sessionService;
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }

    @GetMapping("/sessions/{userId}")
    public ResponseEntity<List<Session>> getSessionForUser(@PathVariable int userId) {
        List<Session> sessions = sessionService.findSesionsForUser(userId);

        return new ResponseEntity<>(sessions, HttpStatus.OK);
    }
}
