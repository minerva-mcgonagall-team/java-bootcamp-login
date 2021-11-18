package com.bootcamp.demo.repository;


import com.bootcamp.demo.repository.builders.SessionBuilder;
import com.bootcamp.demo.model.Session;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;



import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class FirebaseSessionRepository implements SessionRepository{
    Firestore firestoreDB;

    private static final String COLLECTION_PATH = "sessions";
    public FirebaseSessionRepository(Firestore firestoreDB) {
        this.firestoreDB = firestoreDB;
    }

    @Override
    public Set<Session> findAll() {
        try {
            Iterable<QueryDocumentSnapshot> sessionSnapshots = firestoreDB.collection(COLLECTION_PATH)
                    .get()
                    .get()
                    .getDocuments();

            return StreamSupport
                    .stream(sessionSnapshots.spliterator(), false)
                    .map(SessionBuilder::documentToModel)
                    .collect(Collectors.toSet());
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public String save(Session session) {
        if (null == session) throw new IllegalArgumentException();
        try {
            ApiFuture<WriteResult> collectionFuture = firestoreDB
                    .collection(COLLECTION_PATH)
                    .document(session.getId())
                    .set(session);
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
    public Session findById(String id) {
        DocumentReference documentReference = firestoreDB.collection(COLLECTION_PATH).document(id);
        try {
            ApiFuture<DocumentSnapshot> future = documentReference.get();
            DocumentSnapshot documentSnapshot = future.get();

            Session session;
            if(documentSnapshot.exists()){
                session = documentSnapshot.toObject(Session.class);
                return  session;
            }
            else {
                return  null;
            }
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException();
        }
    }
    }

