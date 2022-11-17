package com.goplaces.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.goplaces.R;
import com.goplaces.activitys.EditProfileActivity;
import com.goplaces.activitys.MyPlacesActivity;
import com.goplaces.activitys.MyReviewsActivity;
import com.goplaces.adapter.ReviewsAdapter;
import com.goplaces.auth.LoginActivity;
import com.goplaces.dao.ReviewsDAO;
import com.goplaces.dao.ReviewsDAOInterface;
import com.goplaces.model.Review;

public class ProfileFragment extends Fragment {
    ReviewsDAOInterface reviewsDAO;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        reviewsDAO = ReviewsDAO.getInstance(getActivity());

        configClick(view);

        return view;
    }

    private void configClick(View view) {
        view.findViewById(R.id.buttonMyReviews).setOnClickListener(v ->
                startActivity(new Intent(getActivity(), MyReviewsActivity.class)));

        view.findViewById(R.id.buttonMyPlaces).setOnClickListener(v ->
                startActivity(new Intent(getActivity(), MyPlacesActivity.class)));

        view.findViewById(R.id.buttonEditProfile).setOnClickListener(v ->
                startActivity(new Intent(getActivity(), EditProfileActivity.class)));

        view.findViewById(R.id.buttonLogout).setOnClickListener(v -> getActivity().finish());

    }
}