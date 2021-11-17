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
     * @param new_user the new user
     * @return true, if user was successfully saved, else false
     */
    @Override
    public boolean registerUser(User new_user) {
        return userRepository.save(new_user);

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
        User loggedUser = userRepository.findByEmail(email);
        if (loggedUser != null && loggedUser.getPassword().equals(password)) {
            sessionRepository.save(new Session(loggedUser));
            return true;
        }
        return false;
    }

    /**
     * Finds in the session repository list, the session that has the given user and the endSession null (unfinished)
     * Sets the endSession to the local time
     *
     * @param user is the current logged in user that asks to log out
     * @return true if he was successfully logged out
     */
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
