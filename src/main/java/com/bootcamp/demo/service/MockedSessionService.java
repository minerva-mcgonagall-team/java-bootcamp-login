package com.bootcamp.demo.service;

import com.bootcamp.demo.model.Session;
import com.bootcamp.demo.model.User;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

@Service
public class MockedSessionService implements ISessionService {
    @Override
    public List<Session> findSesionsForUser(User user) {
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

        return Arrays.asList(session1, session2, session3);
    }
}
