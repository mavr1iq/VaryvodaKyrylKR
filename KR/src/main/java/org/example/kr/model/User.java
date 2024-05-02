package org.example.kr.model;

public class User {
    private int id;
    private final String name;
    private final String password;
    private final Boolean admin;

    public User(int id, String name, String password, Boolean admin) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.admin = admin;
    }

    public String getName() {
        return name;
    }
    public String getPassword() {
        return password;
    }
    public int getId() {
        return id;
    }
    public Boolean isAdmin() {
        return admin;
    }
}
