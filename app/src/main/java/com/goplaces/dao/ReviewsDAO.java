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
        review.setUserId(FirebaseHelper.getIdFirebase());
        DatabaseReference reviewsReference = FirebaseHelper.getDatabaseReference()
                .child("reviews")
                .child(review.getId());
        reviewsReference.setValue(review);

        DatabaseReference myReviewsReference = FirebaseHelper.getDatabaseReference()
                .child("myReviews")
                .child(FirebaseHelper.getIdFirebase())
                .child(review.getId());
        myReviewsReference.setValue(review);

        return true;
    }

    @Override
    public boolean editReview(Review review) {
//        for(Review newReview : reviews) {
//            if(newReview.getId() == review.getId()) {
//                newReview.setCity(review.getCity());
//                newReview.setCountry(review.getCountry());
//                newReview.setDescription(review.getDescription());
//                newReview.setRating(review.getRating());
//                return true;
//            }
//        }
        return false;
    }

    @Override
    public ArrayList<Review> listReviews() {
        return reviews;
    }

    @Override
    public boolean removeReview(Review review) {
//        for(Review temp : reviews) {
//            if(temp.getId() == review.getId()) {
//                reviews.remove(review);
//                return true;
//            }
//        }
        return false;
    }


}
