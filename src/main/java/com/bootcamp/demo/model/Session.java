package com.bootcamp.demo.model;

import java.time.LocalDateTime;

/**
 * A Session object stores the user, the start and end date and time of the session
 *
 * Next needed update: DI of the User attribute automatically when a session is created, and the user will be
 * recognised after the Log In
 *
 * @author Denisa Dragota
 * @version 11/11/2021
 */
public class Session {

    private final LocalDateTime startSession;
    private LocalDateTime endSession;
    private User user;

    /**
     * Session constructor that initialises a session start time with the current date and time
     * when creating the object and setting the end date and time null
     */
    public Session(){
        this.startSession = LocalDateTime.now();
        this.endSession = null;
    };

    public LocalDateTime getStartSession() {
        return startSession;
    }

    public LocalDateTime getEndSession() {
        return endSession;
    }

    public void setEndSession(LocalDateTime endSession) {
        this.endSession = endSession;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
