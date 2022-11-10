package com.goplaces.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.goplaces.R;
import com.goplaces.activitys.EditProfileActivity;
import com.goplaces.adapter.MyReviewsAdapter;
import com.goplaces.auth.LoginActivity;
import com.goplaces.dao.ReviewsDAO;
import com.goplaces.dao.ReviewsDAOInterface;
import com.goplaces.model.Review;

import java.util.ArrayList;

public class ProfileFragment extends Fragment implements MyReviewsAdapter.OnClickListener {
    ReviewsDAOInterface reviewsDAO;
    MyReviewsAdapter adapter;
    ArrayList<Review> reviews;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        reviewsDAO = ReviewsDAO.getInstance(getActivity());
        reviews = reviewsDAO.ListReviews();


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        adapter = new MyReviewsAdapter(this, reviews);
        RecyclerView recyclerView = view.findViewById(R.id.reviewsRecyclerView);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        configClick(view);


        return view;
    }

    private void configClick(View view) {
        view.findViewById(R.id.textViewEditProfile).setOnClickListener(v ->
                startActivity(new Intent(getActivity(), EditProfileActivity.class)));

        view.findViewById(R.id.textViewLogout).setOnClickListener(v ->
                startActivity(new Intent(getActivity(), LoginActivity.class)));
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == 1) {
            String city = data.getExtras().getString("city");
            String country = data.getExtras().getString("country");
            String description = data.getExtras().getString("description");
            float rating = data.getExtras().getFloat("rating");

            Review review = new Review(city, country, description, rating);
            Toast.makeText(getActivity(), city, Toast.LENGTH_LONG).show();

            reviewsDAO.addReview(review);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onClick(Review review) {

    }
}