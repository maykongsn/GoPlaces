package com.goplaces.activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.goplaces.R;
import com.goplaces.adapter.FriendsAdapter;
import com.goplaces.dao.FriendsDAO;
import com.goplaces.dao.FriendsDAOInterface;
import com.goplaces.model.Friend;
import com.tsuryo.swipeablerv.SwipeLeftRightCallback;
import com.tsuryo.swipeablerv.SwipeableRecyclerView;

import java.util.ArrayList;

public class MyFriendsActivity extends AppCompatActivity {
    FriendsDAOInterface friendsDAO;
    FriendsAdapter adapter;
    ArrayList<Friend> friends;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_friends);

        friendsDAO = FriendsDAO.getInstance(this);
        friends = friendsDAO.listFriends();

        findViewById(R.id.imageButtonBack).setOnClickListener(view -> finish());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        adapter = new FriendsAdapter(friends);

        SwipeableRecyclerView recyclerViewPlaces = findViewById(R.id.recyclerViewFriends);
        recyclerViewPlaces.setLayoutManager(linearLayoutManager);
        recyclerViewPlaces.setAdapter(adapter);

        recyclerViewPlaces.setListener(new SwipeLeftRightCallback.Listener() {
            @Override
            public void onSwipedLeft(int position) {
                removeFriend(friends.get(position));
            }

            @Override
            public void onSwipedRight(int position) {

            }
        });
    }

    public void addFriend(View View) {
        Intent intent = new Intent(this, FormFriendActivity.class);
        startActivityForResult(intent, 1);
    }

    public void removeFriend(Friend friend) {
        friendsDAO.removeFriend(friend);
        adapter.notifyDataSetChanged();
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == 1) {
            String username = data.getExtras().getString("username");

            Friend friend = new Friend(username);

            friendsDAO.addFriend(friend);
            adapter.notifyDataSetChanged();
        }
    }
}