package com.goplaces.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.goplaces.R;
import com.goplaces.activitys.FormReview;

public class HomeFragment extends Fragment {
    private Button buttonNewReview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        initComponents(view);

        configClicks();

        return view;
    }

    private void configClicks() {
        buttonNewReview.setOnClickListener(v -> startActivity(new Intent(getActivity(), FormReview.class)));
    }

    private void initComponents(View view) {
        buttonNewReview = view.findViewById(R.id.buttonNewReview);

    }
}