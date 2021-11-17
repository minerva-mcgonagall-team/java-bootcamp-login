package com.bootcamp.demo;

import com.bootcamp.demo.model.Session;
import com.bootcamp.demo.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @RequestMapping("/sessions")
    public ResponseEntity<List<Session>> getSessionForUser(User user) {
        User dummyUser = new User(
                "Ada",
                "Alb",
                "adaalb@email.com",
                "Pass123",
                "0745566321",
                User.Gender.FEMALE);

        Session session1 = new Session(user);
        Session session2 = new Session(user);
        Session session3 = new Session(user);

        session1.setEndSession(Instant.now());
        session2.setEndSession(Instant.now());
        session3.setEndSession(Instant.now());

        List<Session> sessionList = Arrays.asList(session1, session2, session3);

        return new ResponseEntity<>(sessionList, HttpStatus.OK);
    }
}
