package com.bootcamp.demo.repository;


import com.google.auth.oauth2.GoogleCredentials;

import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;


import static java.lang.System.getProperty;
import static java.nio.charset.StandardCharsets.UTF_8;

@Component
public class FirebaseRepositoryFactory implements RepositoryFactory {
    Firestore firestoreDB;

    @PostConstruct
    private void initFirestore() throws IOException {
        InputStream serviceAccount = new ByteArrayInputStream(getProperty("firebaseKey").getBytes(UTF_8));
        GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(credentials)
                .build();
        FirebaseApp.initializeApp(options);
        firestoreDB = FirestoreClient.getFirestore();
    }

    @Override
    public UserRepository createUserRepository() {
        return new FirebaseUserRepository(firestoreDB);
    }

    @Override
    public SessionRepository createSessionsRepository() {
        return new FirebaseSessionRepository(firestoreDB);
    }

    @Override
    public UtilRepository createUtilRepository() {
        return new FirebaseUtilRepository(firestoreDB);
    }
}
