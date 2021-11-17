package com.bootcamp.demo.repository;

import com.bootcamp.demo.model.Session;

import java.util.Optional;
import java.util.Set;

public interface SessionRepository extends IRepository<Session>{

    public Set<Session> findAll() ;
    public String save(Session session);
    public void remove(Session session) ;
    public Session findById(String id) ;
}
