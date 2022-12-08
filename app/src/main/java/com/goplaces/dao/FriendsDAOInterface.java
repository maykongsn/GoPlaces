package com.goplaces.dao;

import android.content.Context;

import com.goplaces.model.Friend;

import java.util.ArrayList;

public interface FriendsDAOInterface {
    static FriendsDAOInterface getInstance(Context context) {
        return null;
    }

    boolean addFriend(Friend friend);
}
