package com.bootcamp.demo.service;

import com.bootcamp.demo.model.Session;
import com.bootcamp.demo.model.User;
import com.bootcamp.demo.repository.SessionRepository;
import com.bootcamp.demo.repository.UserRepository;

import java.time.Instant;

public class LoginService implements ILoginService {
    private UserRepository userRepository;
    private SessionRepository sessionRepository;

    public LoginService(UserRepository userRepository, SessionRepository sessionRepository) {
        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
    }

    @Override
    public boolean registerUser(User new_user) {
        return userRepository.save(new_user);

    }

    @Override
    public boolean loginUser(String email, String password) {
        User loggedUser = userRepository.findOne(email, password);
        if (loggedUser != null) {
            sessionRepository.save(new Session(loggedUser));
            return true;
        }
        return false;
    }

    @Override
    public boolean logoutUser(User user) {

        for (Session session : sessionRepository.getAll()) {
            if (session.getUser().equals(user) && session.getEndSession() == null) {
                session.setEndSession(Instant.now());
                return true;
            }
        }
        return false;
    }
}
