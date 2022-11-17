package com.goplaces.dao;

import android.content.Context;

import com.goplaces.model.Friend;

import java.util.ArrayList;

public interface FriendsDAOInterface {
    static FriendsDAOInterface getInstance(Context context) {
        return null;
    }

    ArrayList<Friend> listFriends();
    boolean addFriend(Friend friend);
    boolean removeFriend(Friend friend);
}
