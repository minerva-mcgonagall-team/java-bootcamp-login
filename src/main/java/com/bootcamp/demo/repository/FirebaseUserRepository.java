package com.bootcamp.demo.repository;

import com.bootcamp.demo.UserBuilder;
import com.bootcamp.demo.model.User;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;

import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class FirebaseUserRepository implements UserRepository{
    Firestore firestoreDB;

    public FirebaseUserRepository(Firestore firestoreDB) {
        this.firestoreDB = firestoreDB;
    }

    @Override
    public Set<User> findAll() {
        try {
        Iterable<QueryDocumentSnapshot> users = firestoreDB.collection("accounts")
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
        try {
            ApiFuture<WriteResult> collectionFuture = firestoreDB
                    .collection("accounts")
                    .document("insert_id_here") //here an Id model would be nice
                    .set(user);
            return collectionFuture.get().getUpdateTime().toString();
        } catch (ExecutionException | InterruptedException e) {
             throw new RuntimeException();
        }
    }

    @Override
    public void remove(User user) {
        ApiFuture<WriteResult> writeResult = firestoreDB.collection("accounts")
                .document("insert_id_here") //here an Id model would be nice
                .delete();
    }

    @Override
    public User findById(String id)  {
        DocumentReference documentReference = firestoreDB.collection("accounts").document(id);
        try {
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot documentSnapshot = future.get();

        User user = null;
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


    //easter egg
    public Set<String> getAllPaths() {
        return StreamSupport.stream(firestoreDB.listCollections().spliterator(), false)
                .map(CollectionReference::getPath)
                .collect(Collectors.toUnmodifiableSet());
    }
}
