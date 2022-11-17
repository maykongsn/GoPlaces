package com.goplaces.model;

public class Comment {
    private String message;

    public Comment(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
