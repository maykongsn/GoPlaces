package com.goplaces.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.goplaces.R;
import com.goplaces.activitys.ReviewDetailsActivity;
import com.goplaces.adapter.ReviewsAdapter;
import com.goplaces.dao.ReviewsDAO;
import com.goplaces.dao.ReviewsDAOInterface;
import com.goplaces.model.Review;

import java.util.ArrayList;

public class FavoritesFragment extends Fragment implements ReviewsAdapter.OnClickListener {
    ReviewsDAOInterface reviewsDAO;
    ReviewsAdapter adapter;
    ArrayList<Review> reviews;

    RecyclerView recyclerViewReviews;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);

        reviewsDAO = ReviewsDAO.getInstance(getContext());
        reviews = reviewsDAO.listReviews();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        adapter = new ReviewsAdapter(reviews, this);

        recyclerViewReviews = view.findViewById(R.id.recyclerViewReviews);
        recyclerViewReviews.setLayoutManager(linearLayoutManager);
        recyclerViewReviews.setAdapter(adapter);

        return view;
    }

    @Override
    public void onClick(Review review) {
        Intent intent = new Intent(getActivity(), ReviewDetailsActivity.class);
        intent.putExtra("id", ""+review.getId());
        intent.putExtra("city", review.getCity());
        intent.putExtra("country", review.getCountry());
        intent.putExtra("description", review.getDescription());
        intent.putExtra("rating", review.getRating());
        startActivity(intent);
    }
}