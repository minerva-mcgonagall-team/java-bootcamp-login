package com.bootcamp.demo.repository;

public interface RepositoryFactory {
    UserRepository createUserRepository();

    SessionRepository createSessionsRepository();

    UtilRepository createUtilRepository();
}
