package com.bootcamp.demo.dto.request;

import com.bootcamp.demo.dto.AbstractDTO;

public class LoginRequest extends AbstractDTO {

    private String email;
    private String password;

    public LoginRequest() {

    }

    public LoginRequest(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
