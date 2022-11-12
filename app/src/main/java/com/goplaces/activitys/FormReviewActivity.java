package com.goplaces.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.goplaces.R;
import com.goplaces.dao.ReviewsDAO;
import com.goplaces.dao.ReviewsDAOInterface;
import com.goplaces.helper.FirebaseHelper;
import com.goplaces.model.Review;

public class FormReviewActivity extends AppCompatActivity {
    private ReviewsDAOInterface reviewsDAO;
    private Review review;
    private boolean newReview = true;

    EditText editTextCity;
    EditText editTextCountry;
    EditText editTextDescription;
    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_review);
        TextView textViewToolbar = findViewById(R.id.textViewToolbar);

        reviewsDAO = ReviewsDAO.getInstance(this);

        editTextCity = findViewById(R.id.editTextCity);
        editTextCountry = findViewById(R.id.editTextCountry);
        editTextDescription = findViewById(R.id.editTextDescription);
        ratingBar = findViewById(R.id.ratingBar);

        textViewToolbar.setText("Novo Review");
    }

    public void addReview(View view) {
        String city = editTextCity.getText().toString();
        String country = editTextCountry.getText().toString();
        String description = editTextDescription.getText().toString();
        float rating = ratingBar.getRating();

        review = new Review(FirebaseHelper.getIdFirebase(), city, country, description, rating);
        reviewsDAO.addReview(review);
    }

}