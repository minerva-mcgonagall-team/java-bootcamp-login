package com.bootcamp.demo.repository;

import com.bootcamp.demo.model.Session;


import java.util.Set;

public interface SessionRepository extends IRepository<Session>{
    public Set<Session> findAll() ;
    public String save(Session session);
    public String remove(String id) ;
    public Session findById(String id) ;
}
