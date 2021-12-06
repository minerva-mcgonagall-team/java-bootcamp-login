package com.bootcamp.demo.repository;


import com.bootcamp.demo.model.Session;
import com.bootcamp.demo.model.User;
import com.bootcamp.demo.repository.exception.RepositoryException;
import com.google.cloud.firestore.*;


import java.util.HashSet;
import java.util.Set;

import java.util.concurrent.ExecutionException;


public class FirebaseSessionRepository extends FirebaseAbstractRepository<Session> implements SessionRepository {
    Firestore firestoreDB;

    private static final String COLLECTION_PATH = "minerva-sessions";

    public FirebaseSessionRepository(Firestore firestoreDB) {
        this.firestoreDB = firestoreDB;
    }


    @Override
    public CollectionReference getCollection() {
        return firestoreDB.collection(COLLECTION_PATH);
    }

    @Override
    public Boolean updateSession(Session session) {
        if (null == session) {
            throw new IllegalArgumentException();
        }
        save(session, session.getId());
        return true;
    }

    @Override
    public Set<Session> getAllActiveSessions(User user) {
        try {
            Iterable<QueryDocumentSnapshot> documents = getCollection()
                    .get()
                    .get()
                    .getDocuments();
            Set<Session> result = new HashSet<>();
            for (QueryDocumentSnapshot doc : documents) {
                Session session = doc.toObject(Session.class);
                if ((session.getUser().equals(user)) && (session.getEndSession() == null)) {
                    result.add(session);
                }
            }
            return result;
        } catch (ExecutionException | InterruptedException e) {
            throw new RepositoryException("Exception getAllActiveSessions " + e);
        }
    }
}

