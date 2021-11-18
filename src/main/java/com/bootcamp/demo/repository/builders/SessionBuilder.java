package com.bootcamp.demo.repository.builders;

import com.bootcamp.demo.model.Session;
import com.google.cloud.firestore.QueryDocumentSnapshot;

public class SessionBuilder {
    public static Session documentToModel(QueryDocumentSnapshot queryDocumentSnapshot){
        return queryDocumentSnapshot.toObject(Session.class);
    }
}
