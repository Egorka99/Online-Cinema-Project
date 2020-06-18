package com.epam.library.model;

public class User extends Entity {
    private int id;
    private String nickname;
    private String password;
    private boolean isBlocked;
    private boolean isAdmin;

    public User(int id, String nickname, String password, boolean isBlocked, boolean isAdmin) {
        this.id = id;
        this.nickname = nickname;
        this.password = password;
        this.isBlocked = isBlocked;
        this.isAdmin = isAdmin;
    }

    public int getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public String getPassword() {
        return password;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public boolean isAdmin() {
        return isAdmin;
    }
}
