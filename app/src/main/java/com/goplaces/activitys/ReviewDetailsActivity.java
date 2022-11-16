package com.goplaces.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

import com.goplaces.R;
import com.like.LikeButton;

public class ReviewDetailsActivity extends AppCompatActivity {
    TextView textViewCity;
    TextView textViewCountry;
    TextView textViewDescription;
    RatingBar ratingBar;
    LikeButton likeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_details);

        textViewCity = findViewById(R.id.textViewCity);
        textViewCountry = findViewById(R.id.textViewCountry);
        textViewDescription = findViewById(R.id.textViewDescription);
        ratingBar = findViewById(R.id.ratingBar);
        likeButton = findViewById(R.id.likeButton);

        findViewById(R.id.imageButtonBack).setOnClickListener(view -> finish());

        if(getIntent().getExtras() != null) {
            String city = getIntent().getExtras().getString("city");
            String country = getIntent().getExtras().getString("country");
            String description = getIntent().getExtras().getString("description");
            float rating = getIntent().getExtras().getFloat("rating");

            textViewCity.setText(city);
            textViewCountry.setText(country);
            textViewDescription.setText(description);
            ratingBar.setRating(rating);
        }
    }
}