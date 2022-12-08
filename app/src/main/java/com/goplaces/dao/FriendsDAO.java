package com.goplaces.dao;

import android.content.Context;

import com.google.firebase.database.DatabaseReference;
import com.goplaces.helper.FirebaseHelper;
import com.goplaces.model.Friend;

import java.util.ArrayList;

public class FriendsDAO implements FriendsDAOInterface {
//    ArrayList<Friend> friends = new ArrayList<>();
    private static Context context;
    private static FriendsDAO friendsDAO = null;

    private FriendsDAO(Context context) {
        FriendsDAO.context = context;
    }

    public static FriendsDAOInterface getInstance(Context context) {
        if(friendsDAO == null) {
            friendsDAO = new FriendsDAO(context);
        }
        return friendsDAO;
    }
//
//    @Override
//    public ArrayList<Friend> listFriends() {
//        return friends;
//    }

    @Override
    public boolean addFriend(Friend friend) {
//        friends.add(friend);
        DatabaseReference friendsReference = FirebaseHelper.getDatabaseReference()
                .child("friends")
                .child(FirebaseHelper.getIdFirebase());
        friendsReference.setValue(friend.getFriends());
        return true;
    }

//    @Override
//    public boolean removeFriend(Friend friend) {
//        for(Friend temp : friends) {
//            if(temp.getUsername().equals(friend.getUsername())) {
//                friends.remove(friend);
//                return true;
//            }
//        }
//        return false;
//    }
}
