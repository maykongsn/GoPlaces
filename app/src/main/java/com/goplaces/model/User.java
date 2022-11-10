package com.goplaces.model;

import com.google.firebase.database.DatabaseReference;
import com.goplaces.helper.FirebaseHelper;

public class User {
    private String id;
    private String fullName;
    private String username;
    private String email;
    private String password;

    public User(String fullName, String username, String email, String password) {
        this.fullName = fullName;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public void save() {
        DatabaseReference usersReference = FirebaseHelper.getDatabaseReference();
        usersReference.child("users").child(this.getId()).setValue(this);
    }
}
