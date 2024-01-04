package com.example.conserjera;

public class User {
    private String userId;
    private String userName;
    private String email;
    private String role;

    public User() {
        // Constructor vacío requerido por Firebase
    }

    public User(String userId, String userName, String email, String role) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.role = role;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }
}
