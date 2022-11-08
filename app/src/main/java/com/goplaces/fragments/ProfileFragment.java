package com.goplaces.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.goplaces.R;
import com.goplaces.activitys.EditProfileActivity;
import com.goplaces.auth.LoginActivity;

public class ProfileFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        configClick(view);

        return view;
    }

    private void configClick(View view) {
        view.findViewById(R.id.textViewEditProfile).setOnClickListener(v ->
                startActivity(new Intent(getActivity(), EditProfileActivity.class)));

        view.findViewById(R.id.textViewLogout).setOnClickListener(v ->
                startActivity(new Intent(getActivity(), LoginActivity.class)));
    }
}