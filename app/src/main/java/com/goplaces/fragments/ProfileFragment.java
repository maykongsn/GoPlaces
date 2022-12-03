package com.goplaces.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.goplaces.R;
import com.goplaces.activitys.EditProfileActivity;
import com.goplaces.activitys.MyPlacesActivity;
import com.goplaces.activitys.MyReviewsActivity;
import com.goplaces.auth.LoginActivity;
import com.goplaces.helper.FirebaseHelper;

public class ProfileFragment extends Fragment {
    private TextView textViewUsername;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        textViewUsername = view.findViewById(R.id.textViewUsername);
        configClick(view);

        getUsername();

        return view;
    }

    private void getUsername() {
        DatabaseReference usernameReference = FirebaseHelper.getDatabaseReference()
                .child("users")
                .child(FirebaseHelper.getIdFirebase());
        usernameReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String username = (String) snapshot.child("username").getValue();

                textViewUsername.setText(username);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void configClick(View view) {
        view.findViewById(R.id.buttonMyReviews).setOnClickListener(v ->
                startActivity(new Intent(getActivity(), MyReviewsActivity.class)));

        view.findViewById(R.id.buttonMyPlaces).setOnClickListener(v ->
                startActivity(new Intent(getActivity(), MyPlacesActivity.class)));

        view.findViewById(R.id.buttonEditProfile).setOnClickListener(v ->
                startActivity(new Intent(getActivity(), EditProfileActivity.class)));

        view.findViewById(R.id.buttonLogout).setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), LoginActivity.class));
            getActivity().finish();
        });

    }
}