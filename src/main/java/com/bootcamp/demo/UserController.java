package com.bootcamp.demo;

import com.bootcamp.demo.model.Session;
import com.bootcamp.demo.model.User;
import com.bootcamp.demo.service.ISessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private ISessionService sessionService;

    public UserController(@Autowired ISessionService sessionService) {
        this.sessionService = sessionService;
    }

    @RequestMapping("/sessions")
    public ResponseEntity<List<Session>> getSessionForUser(User user) {
       List<Session> sessions = sessionService.findSesionsForUser(user);

        return new ResponseEntity<>(sessions, HttpStatus.OK);
    }
}
