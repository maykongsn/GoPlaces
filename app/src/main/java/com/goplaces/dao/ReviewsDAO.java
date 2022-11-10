package com.goplaces.dao;

import android.content.Context;

import com.goplaces.model.Review;

import java.util.ArrayList;

public class ReviewsDAO implements ReviewsDAOInterface{
    private static ArrayList<Review> reviews = new ArrayList<>();
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
    public Review getReview(int reviewId) {
        return null;
    }

    @Override
    public ArrayList<Review> ListReviews() {
        return reviews;
    }

    @Override
    public boolean addReview(Review review) {
        reviews.add(review);
        return true;
    }

    @Override
    public boolean editReview(Review review) {
        for(Review newReview : reviews) {
            if(newReview.getId() == review.getId()) {
                newReview.setCity(review.getCity());
                newReview.setCountry(review.getCountry());
                newReview.setDescription(review.getDescription());
                newReview.setRating(review.getRating());
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteReview(int reviewId) {
        return false;
    }

}
