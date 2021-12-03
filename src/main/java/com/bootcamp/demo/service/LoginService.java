package com.bootcamp.demo.service;

import com.bootcamp.demo.model.Session;
import com.bootcamp.demo.model.User;
import com.bootcamp.demo.repository.RepositoryFactory;
import com.bootcamp.demo.repository.SessionRepository;
import com.bootcamp.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Set;

/**
 * LoginService implementation
 * Next to add: forgotPassword()
 */
@Service
public class LoginService implements ILoginService {
    private final RepositoryFactory repositoryFactory;

    @Autowired
    public LoginService(RepositoryFactory repositoryFactory) {
        this.repositoryFactory = repositoryFactory;
    }


    /**
     * @param newUser the new user
     * @return true, if user was successfully saved, or false if email is already used
     */
    @Override
    public boolean registerUser(User newUser) {
        boolean successStatus = false;
        UserRepository userRepository = repositoryFactory.createUserRepository();
        if (userRepository.findByEmail(newUser.getEmail()) == null) {
            userRepository.save(newUser, newUser.getId());
            successStatus = true;
        }

        return successStatus;

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
        boolean successStatus = false;
        UserRepository userRepository = repositoryFactory.createUserRepository();
        SessionRepository sessionRepository = repositoryFactory.createSessionsRepository();
        User loggedUser = userRepository.findByEmail(email);
        if (loggedUser != null && loggedUser.getPassword().equals(password)) {
            Session session = new Session(loggedUser);
            sessionRepository.save(session, session.getId());
            successStatus = true;
        }
        return successStatus;
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
        boolean successStatus = true;
        Instant endTime = Instant.now();
        SessionRepository sessionRepository = repositoryFactory.createSessionsRepository();
        for (Session session : sessionRepository.getAllActiveSessions(user)) {
            session.setEndSession(endTime);
            if (!sessionRepository.updateSession(session)) {
                successStatus = false;
            }
        }
        return successStatus;
    }

    @Override
    public Set<Session> getAllSessions(User user) {
        SessionRepository sessionRepository = repositoryFactory.createSessionsRepository();
        return sessionRepository.getAllSessions(user);
    }
}

