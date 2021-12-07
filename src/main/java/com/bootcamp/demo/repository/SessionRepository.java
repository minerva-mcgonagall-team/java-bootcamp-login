package com.bootcamp.demo.repository;

import com.bootcamp.demo.model.Session;
import com.bootcamp.demo.model.User;

import java.util.Set;


public interface SessionRepository extends IRepository<Session> {
    public Set<Session> getAllActiveSessions(User user);
    Boolean updateSession(Session session);
    public Set<Session> getAllSessions(User user);

}
