package com.bootcamp.demo.model;

import java.time.Instant;


/**
 * A Session object stores the user, the start and end date and time of the session
 * <p>
 * Next needed update: DI of the User attribute automatically when a session is created, and the user will be
 * recognised after the Log In
 *
 * @author Denisa Dragota
 * @version 11/11/2021
 */

public class Session extends AbstractModel{

    private final Instant startSession;
    private Instant endSession;
    private User user;

    /**
     * Session constructor that initialises a session start time with the current date and time
     * when creating the object and setting the end date and time null
     */
    public Session(User user) {
        this.startSession = Instant.now();
        this.endSession = null;
        this.user = user;
    }

    public Instant getStartSession() {
        return startSession;
    }

    public Instant getEndSession() {
        return endSession;
    }

    public void setEndSession(Instant endSession) {
        this.endSession = endSession;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
