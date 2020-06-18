package com.epam.library.model;

public class History extends Entity{
    private int id;
    private int userId;
    private String actionText;

    public History(int id, int userId, String actionText) {
        this.id = id;
        this.userId = userId;
        this.actionText = actionText;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getActionText() {
        return actionText;
    }
}
