package com.goplaces.activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import com.goplaces.R;
import com.goplaces.adapter.CommentsAdapter;
import com.goplaces.dao.CommentsDAO;
import com.goplaces.dao.CommentsDAOInterface;
import com.goplaces.model.Comment;
import com.like.LikeButton;

import java.util.ArrayList;

public class ReviewDetailsActivity extends AppCompatActivity {
    TextView textViewCity;
    TextView textViewCountry;
    TextView textViewDescription;
    RatingBar ratingBar;
    LikeButton likeButton;
    CommentsDAOInterface commentsDAO;
    CommentsAdapter adapter;
    ArrayList<Comment> comments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_details);

        commentsDAO = CommentsDAO.getInstance(this);
        comments = commentsDAO.listComments();

        textViewCity = findViewById(R.id.textViewCity);
        textViewCountry = findViewById(R.id.textViewCountry);
        textViewDescription = findViewById(R.id.textViewDescription);
        ratingBar = findViewById(R.id.ratingBar);
        likeButton = findViewById(R.id.likeButton);

        findViewById(R.id.imageButtonBack).setOnClickListener(view -> finish());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        adapter = new CommentsAdapter(comments);

        RecyclerView recyclerViewComments = findViewById(R.id.recyclerViewComments);
        recyclerViewComments.setLayoutManager(linearLayoutManager);
        recyclerViewComments.setAdapter(adapter);

        if(getIntent().getExtras() != null) {
            String city = getIntent().getExtras().getString("city");
            String country = getIntent().getExtras().getString("country");
            String description = getIntent().getExtras().getString("description");
            float rating = getIntent().getExtras().getFloat("rating");

            textViewCity.setText(city);
            textViewCountry.setText(country);
            textViewDescription.setText(description);
            ratingBar.setRating(rating);
        }
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