package com.bootcamp.demo.repository;

import com.bootcamp.demo.model.User;
import com.google.cloud.firestore.*;



public class FirebaseUserRepository extends FirebaseAbstractRepository<User> implements UserRepository{
    private static final String COLLECTION_PATH = "accounts";
    private final Firestore firestoreDB;

    public FirebaseUserRepository(Firestore firestoreDB) {
        this.firestoreDB = firestoreDB;
    }

    @Override
    public CollectionReference getCollection() {
        return firestoreDB.collection(COLLECTION_PATH);
    }
}
