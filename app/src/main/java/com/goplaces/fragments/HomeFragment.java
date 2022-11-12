package com.goplaces.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.goplaces.R;
import com.goplaces.activitys.FormReviewActivity;

public class HomeFragment extends Fragment {
    private Button buttonNewReview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        buttonNewReview = view.findViewById(R.id.buttonNewReview);

        newReview();

        return view;
    }

    public void newReview() {
        buttonNewReview.setOnClickListener(view -> {
            startActivity(new Intent(getActivity(), FormReviewActivity.class));
        });
    }
}