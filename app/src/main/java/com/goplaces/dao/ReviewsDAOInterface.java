package com.goplaces.dao;

import android.content.Context;

import com.goplaces.model.Review;

import java.util.ArrayList;

public interface ReviewsDAOInterface {
    static ReviewsDAOInterface getInstance(Context context) {
        return null;
    }

    Review getReview(int reviewId);
    ArrayList<Review> ListReviews();

    boolean addReview(Review review);
    boolean editReview(Review review);
    boolean deleteReview(int reviewId);
}