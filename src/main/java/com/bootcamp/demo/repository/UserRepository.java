package com.bootcamp.demo.repository;

import com.bootcamp.demo.model.User;

import java.util.Set;



public interface UserRepository extends IRepository<User> {
    public Set<User> findAll() ;
    public String save(User user) ;
    public String remove(String id);
    public User findById(String id) ;

}
