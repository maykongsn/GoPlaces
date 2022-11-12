package com.goplaces.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.goplaces.R;
import com.goplaces.activitys.EditProfileActivity;
import com.goplaces.adapter.MyReviewsAdapter;
import com.goplaces.auth.LoginActivity;
import com.goplaces.dao.ReviewsDAO;
import com.goplaces.dao.ReviewsDAOInterface;
import com.goplaces.helper.FirebaseHelper;
import com.goplaces.model.Review;
import com.tsuryo.swipeablerv.SwipeableRecyclerView;

import java.util.ArrayList;
import java.util.Collections;

public class ProfileFragment extends Fragment implements MyReviewsAdapter.OnClickListener {
    ReviewsDAOInterface reviewsDAO;
    MyReviewsAdapter adapter;
    ArrayList<Review> reviews = new ArrayList<>();

    private SwipeableRecyclerView recyclerViewReviews;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        reviewsDAO = ReviewsDAO.getInstance(getActivity());

        recyclerViewReviews = view.findViewById(R.id.recyclerViewReviews);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        adapter = new MyReviewsAdapter(reviews, this);
        recyclerViewReviews.setLayoutManager(linearLayoutManager);
        recyclerViewReviews.setAdapter(adapter);
        configClick(view);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        reviews = reviewsDAO.listReviews();
        adapter.notifyDataSetChanged();
    }

    private void configClick(View view) {
        view.findViewById(R.id.textViewEditProfile).setOnClickListener(v ->
                startActivity(new Intent(getActivity(), EditProfileActivity.class)));

        view.findViewById(R.id.textViewLogout).setOnClickListener(v ->
                startActivity(new Intent(getActivity(), LoginActivity.class)));
    }

    public void listReviews() {
//        DatabaseReference reviewsReference = FirebaseHelper.getDatabaseReference()
//                .child("reviews")
//                .child(FirebaseHelper.getIdFirebase());
//        reviewsReference.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if(snapshot.exists()) {
//                    reviews.clear();
//                    for(DataSnapshot dataSnapshot : snapshot.getChildren()) {
//                        Review review = dataSnapshot.getValue(Review.class);
//                        reviews.add(review);
//                    }
//                    Collections.reverse(reviews);
//                    adapter.notifyDataSetChanged();
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
        reviews = reviewsDAO.listReviews();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(Review review) {

    }
}