package com.bootcamp.demo.service;

import com.bootcamp.demo.model.Session;
import com.bootcamp.demo.model.User;
import com.bootcamp.demo.repository.SessionRepository;
import com.bootcamp.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;

/**
 * LoginService implementation
 * Next to add: forgotPassword()
 */
@Service
public class LoginService implements ILoginService {
    private UserRepository userRepository;
    private SessionRepository sessionRepository;

    public LoginService(UserRepository userRepository, SessionRepository sessionRepository) {
        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
    }

    /**
     * @param newUser the new user
     * @return true, if user was successfully saved, or false if email is already used
     */
    @Override
    public boolean registerUser(User newUser) {
        boolean succesStatus = false;
        if (userRepository.findByEmail(newUser.getEmail()) == null) {
            userRepository.save(newUser);
            succesStatus = true;
        }

        return succesStatus;

    }

    /**
     * if user was successfully logged in, a new session is created
     *
     * @param email    is the email introduced by the user
     * @param password is the password introduced by the user
     * @return true if the user with the given email and password exists in the database, else false
     */
    @Override
    public boolean loginUser(String email, String password) {
        boolean succesStatus = false;
        User loggedUser = userRepository.findByEmail(email);
        if (loggedUser != null && loggedUser.getPassword().equals(password)) {
            sessionRepository.save(new Session(loggedUser));
            succesStatus = true;
        }
        return succesStatus;
    }

    /**
     * Sets the endSession to the local time for each Session from the list of active sessions of a user
     * Updates the sessionRepository with each updated Session
     *
     * @param user is the current logged in user that asks to log out
     * @return true if he was successfully logged out from all his devices (all his sessions were ended), else false
     */
    @Override
    public boolean logoutUser(User user) {
        boolean succesStatus = true;
        Instant endTime = Instant.now();
        for (Session session : sessionRepository.getAllActiveSessions(user)) {
            session.setEndSession(endTime);
            if (!sessionRepository.updateSession(session)) {
                succesStatus = false;
            }
        }
        return succesStatus;
    }
}
