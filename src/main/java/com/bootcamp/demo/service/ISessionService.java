package com.bootcamp.demo.service;

import com.bootcamp.demo.model.Session;
import com.bootcamp.demo.model.User;

import java.util.List;

public interface ISessionService {
    List<Session> findSesionsForUser(User user);
}
