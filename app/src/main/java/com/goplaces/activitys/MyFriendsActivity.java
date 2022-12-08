package com.goplaces.activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.goplaces.R;
import com.goplaces.adapter.FriendsAdapter;
import com.goplaces.dao.FriendsDAO;
import com.goplaces.dao.FriendsDAOInterface;
import com.goplaces.helper.FirebaseHelper;
import com.goplaces.model.Favorite;
import com.goplaces.model.Friend;
import com.goplaces.model.Review;
import com.goplaces.model.User;
import com.tsuryo.swipeablerv.SwipeLeftRightCallback;
import com.tsuryo.swipeablerv.SwipeableRecyclerView;

import java.util.ArrayList;
import java.util.Collections;

public class MyFriendsActivity extends AppCompatActivity {
    FriendsDAOInterface friendsDAO;
    FriendsAdapter adapter;
    ArrayList<String> friends = new ArrayList<>();
    ArrayList<Friend> users = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_friends);

        friendsDAO = FriendsDAO.getInstance(this);
//        friends = friendsDAO.listFriends();

        findViewById(R.id.imageButtonBack).setOnClickListener(view -> finish());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        adapter = new FriendsAdapter(users);

        SwipeableRecyclerView recyclerViewPlaces = findViewById(R.id.recyclerViewFriends);
        recyclerViewPlaces.setLayoutManager(linearLayoutManager);
        recyclerViewPlaces.setAdapter(adapter);

        recyclerViewPlaces.setListener(new SwipeLeftRightCallback.Listener() {
            @Override
            public void onSwipedLeft(int position) {
                removeFriend(position);
            }

            @Override
            public void onSwipedRight(int position) {

            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();

        getFriends();
    }

    public void addFriend(View View) {
        Intent intent = new Intent(this, FormFriendActivity.class);
        startActivityForResult(intent, 1);
    }

    public void removeFriend(int position) {
        friends.remove(position);
        Friend friend = new Friend();
        friend.setFriends(friends);
        friendsDAO.addFriend(friend);
//        friendsDAO.removeFriend(friend);
//        adapter.notifyDataSetChanged();
    }

    public void getFriends() {
        DatabaseReference friendsReference = FirebaseHelper.getDatabaseReference()
                .child("friends")
                .child(FirebaseHelper.getIdFirebase());
        friendsReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                friends.clear();
                users.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    friends.add(dataSnapshot.getValue(String.class));
                }
                if(friends.size() > 0) {
                    for(int i = 0; i < friends.size(); i++) {
                        DatabaseReference usersReference = FirebaseHelper.getDatabaseReference()
                                .child("users")
                                .child(friends.get(i));
                        usersReference.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                Friend friend = snapshot.getValue(Friend.class);
                                users.add(friend);
                                if(users.size() == friends.size()) {
                                    adapter.notifyDataSetChanged();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                } else {
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private static final String tag = "teste";

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == 1) {
            String username = data.getExtras().getString("username");
            DatabaseReference userReference = FirebaseHelper.getDatabaseReference()
                    .child("users");
            userReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for(DataSnapshot ds : snapshot.getChildren()) {
                        User user = ds.getValue(User.class);
                        if(username.equals(user.getUsername())) {
                            friends.add(user.getId());
                            Log.e(tag, friends.get(0));
                        }
                    }
                    Friend friend = new Friend();
                    friend.setFriends(friends);
                    friendsDAO.addFriend(friend);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

//            Friend friend = new Friend(username);
//
//            friendsDAO.addFriend(friend);
            adapter.notifyDataSetChanged();
        }
    }
}