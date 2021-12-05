package com.bootcamp.demo.repository;

import com.bootcamp.demo.model.User;
import com.bootcamp.demo.repository.exception.RepositoryException;
import com.google.cloud.firestore.*;
import org.springframework.stereotype.Repository;


import java.util.concurrent.ExecutionException;

@Repository
public class FirebaseUserRepository extends FirebaseAbstractRepository<User> implements UserRepository {
    private static final String COLLECTION_PATH = "accounts";
    private final Firestore firestoreDB;

    public FirebaseUserRepository(Firestore firestoreDB) {
        this.firestoreDB = firestoreDB;
    }

    public User findByEmail(String email) {
        if (null == email) throw new IllegalArgumentException();
        try {
            Iterable<QueryDocumentSnapshot> documents = getCollection()
                    .get()
                    .get()
                    .getDocuments();

            for (QueryDocumentSnapshot doc : documents) {
                User user = doc.toObject(User.class);
                if (user.getEmail().equals(email)) {
                    return user;
                }
            }
            return null;
        } catch (ExecutionException | InterruptedException e) {
            throw new RepositoryException("Exception findByEmail " + e);
        }
    }

    @Override
    public CollectionReference getCollection() {
        return firestoreDB.collection(COLLECTION_PATH);
    }

}