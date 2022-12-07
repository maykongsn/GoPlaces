package com.goplaces.activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.goplaces.R;
import com.goplaces.adapter.CommentsAdapter;
import com.goplaces.dao.CommentsDAO;
import com.goplaces.dao.CommentsDAOInterface;
import com.goplaces.dao.FavoritesDAO;
import com.goplaces.dao.FavoritesDAOInterface;
import com.goplaces.helper.FirebaseHelper;
import com.goplaces.model.Comment;
import com.goplaces.model.Favorite;
import com.goplaces.model.Review;
import com.goplaces.model.User;
import com.like.LikeButton;
import com.like.OnLikeListener;

import java.util.ArrayList;

public class ReviewDetailsActivity extends AppCompatActivity {
    TextView textViewCity;
    TextView textViewCountry;
    TextView textViewDescription;
    TextView textViewUsername;
    RatingBar ratingBar;
    LikeButton likeButton;
    CommentsDAOInterface commentsDAO;
    FavoritesDAOInterface favoritesDAO;
    CommentsAdapter adapter;

    ArrayList<String> favorites = new ArrayList<>();
    ArrayList<Comment> comments;

    String reviewId;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_details);

        commentsDAO = CommentsDAO.getInstance(this);
        comments = commentsDAO.listComments();

        favoritesDAO = FavoritesDAO.getInstance(this);

        textViewCity = findViewById(R.id.textViewCity);
        textViewCountry = findViewById(R.id.textViewCountry);
        textViewDescription = findViewById(R.id.textViewDescription);
        textViewUsername = findViewById(R.id.textViewUsername);
        ratingBar = findViewById(R.id.ratingBar);
        likeButton = findViewById(R.id.likeButton);

        findViewById(R.id.imageButtonBack).setOnClickListener(view -> finish());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        adapter = new CommentsAdapter(comments);

        RecyclerView recyclerViewComments = findViewById(R.id.recyclerViewComments);
        recyclerViewComments.setLayoutManager(linearLayoutManager);
        recyclerViewComments.setAdapter(adapter);

        if(getIntent().getExtras() != null) {
            reviewId = getIntent().getExtras().getString("id");
            userId = getIntent().getExtras().getString("userId");
            String city = getIntent().getExtras().getString("city");
            String country = getIntent().getExtras().getString("country");
            String description = getIntent().getExtras().getString("description");
            float rating = getIntent().getExtras().getFloat("rating");

            textViewCity.setText(city);
            textViewCountry.setText(country);
            textViewDescription.setText(description);
            ratingBar.setRating(rating);

            getUser();
        }

        like();
        getFavorites();
   }

    public void getUser() {
        DatabaseReference userReference = FirebaseHelper.getDatabaseReference().child("users").child(userId);
        userReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                textViewUsername.setText("@" + user.getUsername());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void like() {
        likeButton.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                configLike(true);
            }

            @Override
            public void unLiked(LikeButton likeButton) {
                configLike(false);
            }
        });
    }

    public void configLike(boolean like) {
        if(like) {
            likeButton.setLiked(true);
            favorites.add(reviewId);
        } else {
            likeButton.setLiked(false);
            favorites.remove(reviewId);
        }

        Favorite favorite = new Favorite();
        favorite.setFavorites(favorites);
        favoritesDAO.addFavorite(favorite);
    }

    public void getFavorites() {
        DatabaseReference favoritesReference = FirebaseHelper.getDatabaseReference()
                .child("favorites")
                .child(FirebaseHelper.getIdFirebase());
        favoritesReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    favorites.add(dataSnapshot.getValue(String.class));
                }

                if(favorites.contains(reviewId)) {
                    likeButton.setLiked(true);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void addComment(View View) {
        Intent intent = new Intent(this, FormCommentActivity.class);
        startActivityForResult(intent, 1);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == 1) {
            String message = data.getExtras().getString("message");

            Comment comment = new Comment(message);

            commentsDAO.addComment(comment);
            adapter.notifyDataSetChanged();
        }
    }
}