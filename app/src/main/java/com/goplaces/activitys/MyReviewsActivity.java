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
import com.goplaces.adapter.ReviewsAdapter;
import com.goplaces.dao.ReviewsDAO;
import com.goplaces.dao.ReviewsDAOInterface;
import com.goplaces.helper.FirebaseHelper;
import com.goplaces.model.Review;
import com.tsuryo.swipeablerv.SwipeLeftRightCallback;
import com.tsuryo.swipeablerv.SwipeableRecyclerView;

import java.util.ArrayList;
import java.util.Collections;

public class MyReviewsActivity extends AppCompatActivity implements ReviewsAdapter.OnClickListener {
    ReviewsDAOInterface reviewsDAO;
    ReviewsAdapter adapter;
    ArrayList<Review> reviews = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_reviews);

        reviewsDAO = ReviewsDAO.getInstance(this);

        findViewById(R.id.imageButtonBack).setOnClickListener(view -> finish());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        adapter = new ReviewsAdapter(reviews, this);

        SwipeableRecyclerView recyclerViewReviews = findViewById(R.id.recyclerViewReviews);
        recyclerViewReviews.setLayoutManager(linearLayoutManager);
        recyclerViewReviews.setAdapter(adapter);

        recyclerViewReviews.setListener(new SwipeLeftRightCallback.Listener() {
            @Override
            public void onSwipedLeft(int position) {
                removeReview(reviews.get(position));
            }

            @Override
            public void onSwipedRight(int position) {
                editReview(reviews.get(position));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        loadReviews();
    }

    public void listReviews() {
        reviews = reviewsDAO.listReviews();
    }

    public void loadReviews() {
        reviews.clear();
        DatabaseReference reviewsReference = FirebaseHelper.getDatabaseReference()
                .child("myReviews")
                .child(FirebaseHelper.getIdFirebase());
        reviewsReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.getChildren()) {
                    Review review = ds.getValue(Review.class);
                    reviews.add(review);
                }
                Collections.reverse(reviews);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void addReview(View view) {
        Intent intent = new Intent(this, FormReviewActivity.class);
        startActivityForResult(intent, 1);
    }

    public void editReview(Review review) {
        Intent intent = new Intent(this, FormReviewActivity.class);

        intent.putExtra("id", ""+review.getId());
        intent.putExtra("city", review.getCity());
        intent.putExtra("country", review.getCountry());
        intent.putExtra("description", review.getDescription());
        intent.putExtra("rating", review.getRating());
        startActivityForResult(intent, 2);
    }

    public void removeReview(Review review) {
        reviews.remove(review);
        reviewsDAO.removeReview(review);
        adapter.notifyDataSetChanged();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == 1) {
            String city = data.getExtras().getString("city");
            String country = data.getExtras().getString("country");
            String description = data.getExtras().getString("description");
            float rating = data.getExtras().getFloat("rating");

            Review review = new Review(city, country, description, rating);
            reviewsDAO.addReview(review);
            adapter.notifyDataSetChanged();
        } else if(requestCode == 2 && resultCode == 1) {
            String idString = data.getExtras().getString("id");
            String city = data.getExtras().getString("city");
            String country = data.getExtras().getString("country");
            String description = data.getExtras().getString("description");
            float rating = data.getExtras().getFloat("rating");
            if(idString != null) {
                Review review = new Review(city, country, description, rating);
                review.setId(idString);

                reviewsDAO.editReview(review);
                adapter.notifyDataSetChanged();
            }
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onClick(Review review) {
        Intent intent = new Intent(this, ReviewDetailsActivity.class);
        intent.putExtra("id", ""+ review.getId());
        intent.putExtra("userId", ""+ review.getUserId());
        intent.putExtra("city", review.getCity());
        intent.putExtra("country", review.getCountry());
        intent.putExtra("description", review.getDescription());
        intent.putExtra("rating", review.getRating());
        startActivity(intent);
    }
}