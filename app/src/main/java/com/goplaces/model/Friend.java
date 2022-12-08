package com.goplaces.model;

import java.util.ArrayList;

public class Friend {
    private String username;
    private ArrayList<String> friends;

    public ArrayList<String> getFriends() {
        return friends;
    }
    public Friend() {}
    public Friend(String username) {
        this.username = username;
    }

    public void setFriends(ArrayList<String> friends) {
        this.friends = friends;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
