package com.goplaces.dao;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.goplaces.helper.FirebaseHelper;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import com.goplaces.model.Review;

import java.util.ArrayList;
import java.util.Collections;

public class ReviewsDAO implements ReviewsDAOInterface {
    ArrayList<Review> reviews = new ArrayList<>();
    private static Context context;
    private static final String TAG = "teste";
    private static ReviewsDAO reviewsDAO = null;

    private ReviewsDAO(Context context) {
        ReviewsDAO.context = context;
    }

    public static ReviewsDAOInterface getInstance(Context context) {
        if(reviewsDAO == null) {
            reviewsDAO = new ReviewsDAO(context);
        }
        return reviewsDAO;
    }

    @Override
    public boolean addReview(Review review) {
        DatabaseReference reviewsReference = FirebaseHelper.getDatabaseReference()
                .child("reviews")
                .child(FirebaseHelper.getIdFirebase())
                .child(review.getId());
        reviewsReference.setValue(review);
        return true;
    }

    @Override
    public ArrayList<Review> listReviews() {
        DatabaseReference reviewsReference = FirebaseHelper.getDatabaseReference()
                .child("reviews")
                .child(FirebaseHelper.getIdFirebase());
        reviewsReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    reviews.clear();
                    for(DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        Review review = dataSnapshot.getValue(Review.class);
                        reviews.add(review);
                    }
                    Collections.reverse(reviews);
                    Log.d(TAG, reviews.get(2).getCity());

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
        return reviews;
    }


}
