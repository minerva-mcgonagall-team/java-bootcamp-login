package com.bootcamp.demo.service;

import com.bootcamp.demo.model.Session;

import java.util.List;

public interface ISessionService {
    List<Session> findSesionsForUser(int userId);
}
