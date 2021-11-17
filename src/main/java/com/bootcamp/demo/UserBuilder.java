package com.bootcamp.demo;

import com.bootcamp.demo.model.User;
import com.google.cloud.firestore.QueryDocumentSnapshot;

public class UserBuilder {
    public static User documentToModel(QueryDocumentSnapshot queryDocumentSnapshot){
        return queryDocumentSnapshot.toObject(User.class);
    }
}
