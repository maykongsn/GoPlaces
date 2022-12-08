package com.goplaces.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.goplaces.R;
import com.goplaces.activitys.EditProfileActivity;
import com.goplaces.activitys.MyFriendsActivity;
import com.goplaces.activitys.ReviewDetailsActivity;
import com.goplaces.adapter.ReviewsAdapter;
import com.goplaces.dao.ReviewsDAO;
import com.goplaces.dao.ReviewsDAOInterface;
import com.goplaces.helper.FirebaseHelper;
import com.goplaces.model.Review;

import java.util.ArrayList;
import java.util.Collections;

public class FeedFragment extends Fragment implements ReviewsAdapter.OnClickListener {
    ReviewsDAOInterface reviewsDAO;
    ReviewsAdapter adapter;
    ArrayList<Review> reviews = new ArrayList<>();
    ArrayList<String> friends = new ArrayList<>();

    RecyclerView recyclerViewReviews;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feed, container, false);

        reviewsDAO = ReviewsDAO.getInstance(getContext());

        view.findViewById(R.id.buttonAddFriend).setOnClickListener(v ->
                startActivity(new Intent(getActivity(), MyFriendsActivity.class)));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        adapter = new ReviewsAdapter(reviews, this);

        recyclerViewReviews = view.findViewById(R.id.recyclerViewReviews);
        recyclerViewReviews.setLayoutManager(linearLayoutManager);
        recyclerViewReviews.setAdapter(adapter);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        getReviews();
    }

    public void getReviews() {
        DatabaseReference friendsReference = FirebaseHelper.getDatabaseReference()
                .child("friends")
                .child(FirebaseHelper.getIdFirebase());
        friendsReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                friends.clear();
                reviews.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    friends.add(dataSnapshot.getValue(String.class));
                }
                if(friends.size() > 0) {
                    for(int i = 0; i < friends.size(); i++) {
                        DatabaseReference reviewsReference = FirebaseHelper.getDatabaseReference()
                                .child("myReviews")
                                .child(friends.get(i));
                        reviewsReference.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for(DataSnapshot ds : snapshot.getChildren()) {
                                    Review review = ds.getValue(Review.class);
                                    reviews.add(review);
                                }
                                adapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                } else {
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onClick(Review review) {
        Intent intent = new Intent(getContext(), ReviewDetailsActivity.class);
        intent.putExtra("id", ""+ review.getId());
        intent.putExtra("userId", ""+ review.getUserId());
        intent.putExtra("city", review.getCity());
        intent.putExtra("country", review.getCountry());
        intent.putExtra("description", review.getDescription());
        intent.putExtra("rating", review.getRating());
        startActivity(intent);
    }
}