package com.bootcamp.demo.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import java.io.IOException;
import java.io.InputStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class FirebaseConfig {

    @Bean
    public FirebaseAuth firebaseAuth() throws IOException {

        InputStream serviceAccount = new ClassPathResource("firebase_config.json").getInputStream();

        FirebaseOptions options = new FirebaseOptions.Builder()
            .setCredentials(GoogleCredentials.fromStream(serviceAccount))
            .setDatabaseUrl("https://java-bootcamp-db-default-rtdb.europe-west1.firebasedatabase.app/").build();

        FirebaseApp.initializeApp(options);

        return FirebaseAuth.getInstance();
    }

}
