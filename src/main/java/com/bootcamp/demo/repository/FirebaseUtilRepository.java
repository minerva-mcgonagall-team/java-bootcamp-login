package com.bootcamp.demo.repository;

import com.google.cloud.firestore.*;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class FirebaseUtilRepository implements UtilRepository {
    private final Firestore firestoreDB;

    public FirebaseUtilRepository(Firestore firestoreDB) {
        this.firestoreDB = firestoreDB;
    }

    public Set<String> getAllPaths() {
        return StreamSupport.stream(firestoreDB.listCollections().spliterator(), false)
                .map(CollectionReference::getPath)
                .collect(Collectors.toUnmodifiableSet());
    }

    @Override
    public void deleteCollectionPath(String collectionName) {
        firestoreDB.collection(collectionName).listDocuments().forEach(DocumentReference::delete);
    }
}
