package com.bootcamp.demo.repository;

import com.bootcamp.demo.model.Session;
import com.google.cloud.firestore.Firestore;


import java.util.Set;

public class FirebaseSessionRepository implements SessionRepository{
    Firestore firestoreDB;

    public FirebaseSessionRepository(Firestore firestoreDB) {
        this.firestoreDB = firestoreDB;
    }

    @Override
    public Set<Session> findAll() {
        return null;
    }

    @Override
    public String save(Session session) {
        return null;
    }

    @Override
    public void remove(Session o) {

    }

    @Override
    public Session findById(String id) {
        return null;
    }
}
