package com.bootcamp.demo.repository.builders;

import com.bootcamp.demo.model.User;
import com.google.cloud.firestore.QueryDocumentSnapshot;

public class UserBuilder {
    public static User documentToModel(QueryDocumentSnapshot queryDocumentSnapshot){
        return queryDocumentSnapshot.toObject(User.class);
    }
}
