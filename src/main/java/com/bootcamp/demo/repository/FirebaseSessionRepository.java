package com.bootcamp.demo.repository;


import com.bootcamp.demo.model.Session;
import com.google.cloud.firestore.*;





public class FirebaseSessionRepository extends FirebaseAbstractRepository<Session> implements SessionRepository{
    Firestore firestoreDB;

    private static final String COLLECTION_PATH = "sessions";
    public FirebaseSessionRepository(Firestore firestoreDB) {
        this.firestoreDB = firestoreDB;
    }


    @Override
    public CollectionReference getCollection() {
        return firestoreDB.collection(COLLECTION_PATH);
    }
}

