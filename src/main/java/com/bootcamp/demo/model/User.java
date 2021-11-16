package com.bootcamp.demo.model;

/**
 * An User object stores first and last name, email, password, phoneNumber and gender of an user
 * <p>
 * Next needed update: to find a way to store encrypted password, not the real one
 *
 * @author Denisa Dragota
 * @version 15/11/2021
 */

public class User {
    public enum Gender {MALE, FEMALE}

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private Gender gender;

    public User(String firstName, String lastName, String email, String password, String phoneNumber, Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
