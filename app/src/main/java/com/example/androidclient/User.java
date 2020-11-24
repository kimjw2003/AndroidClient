package com.example.androidclient;

public class User {
    String user;
    String password;

    public void setUser(String user) {
        this.user = user;
    }

    public String getUser() {
        return user;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public User(String user, String password){
        this.user = user;
        this.password = password;
    }
}
