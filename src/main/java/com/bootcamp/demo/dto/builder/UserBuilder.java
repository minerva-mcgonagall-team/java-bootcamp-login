package com.bootcamp.demo.dto.builder;

import com.bootcamp.demo.dto.request.RegisterRequest;
import com.bootcamp.demo.model.User;

public class UserBuilder {
    public UserBuilder() {
    }

    public static User toEntity(RegisterRequest dto) {
        return new User(dto.getFirstname(),
                dto.getLastname(),
                dto.getEmail(),
                dto.getPassword(),
                dto.getPhoneNumber());
    }
}
