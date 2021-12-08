package com.bootcamp.demo.repository;

import com.bootcamp.demo.model.Session;
import com.bootcamp.demo.model.User;

import java.util.List;
import java.util.Set;


public interface SessionRepository extends IRepository<Session> {
    List<Session> findSessionsForUserId(String userid);
    Boolean updateSession(Session session);
}
