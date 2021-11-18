package com.bootcamp.demo.repository;

import com.bootcamp.demo.repository.builders.UserBuilder;
import com.bootcamp.demo.model.User;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;

import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class FirebaseUserRepository implements UserRepository{
    private static final String COLLECTION_PATH = "accounts";
    private final Firestore firestoreDB;

    public FirebaseUserRepository(Firestore firestoreDB) {
        this.firestoreDB = firestoreDB;
    }

    @Override
    public Set<User> findAll() {
        try {
        Iterable<QueryDocumentSnapshot> users = firestoreDB.collection(COLLECTION_PATH)
                .get()
                .get()
                .getDocuments();

            return StreamSupport
                    .stream(users.spliterator(), false)
                    .map(UserBuilder::documentToModel)
                    .collect(Collectors.toSet());
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public String save(User user)  {
        if (null == user) throw new IllegalArgumentException();
        try {
            ApiFuture<WriteResult> collectionFuture = firestoreDB
                    .collection(COLLECTION_PATH)
                    .document(user.getId())
                    .set(user);
            return collectionFuture.get().getUpdateTime().toString();
        } catch (ExecutionException | InterruptedException e) {
             throw new RuntimeException();
        }
    }

    @Override
    public String remove(String id) {
        if (null == id) throw new IllegalArgumentException();
        try {
        ApiFuture<WriteResult> collectionFuture = firestoreDB.collection(COLLECTION_PATH)
                .document(id)
                .delete();
        return collectionFuture.get().getUpdateTime().toString();
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public User findById(String id)  {
        DocumentReference documentReference = firestoreDB.collection(COLLECTION_PATH).document(id);
        try {
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot documentSnapshot = future.get();

        User user;
        if(documentSnapshot.exists()){

            user = documentSnapshot.toObject(User.class);
            return  user;
        }
        else {
            return  null;
        }
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException();
        }
    }
}
