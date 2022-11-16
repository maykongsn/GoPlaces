package com.goplaces.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
    EditText editTextCity;
    EditText editTextCountry;
    EditText editTextDescription;
    RatingBar ratingBar;

    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_review);
        id = -1;

        TextView textViewToolbar = findViewById(R.id.textViewToolbar);

        editTextCity = findViewById(R.id.editTextCity);
        editTextCountry = findViewById(R.id.editTextCountry);
        editTextDescription = findViewById(R.id.editTextDescription);
        ratingBar = findViewById(R.id.ratingBar);

        if(getIntent().getExtras() != null) {
            String idString = getIntent().getExtras().getString("id");
            String city = getIntent().getExtras().getString("city");
            String country = getIntent().getExtras().getString("country");
            String description = getIntent().getExtras().getString("description");
            float rating = getIntent().getExtras().getFloat("rating");

            if(idString != null) {
                id = Integer.parseInt(idString);
            }

            editTextCity.setText(city);
            editTextCountry.setText(country);
            editTextDescription.setText(description);
            ratingBar.setRating(rating);
        }

        textViewToolbar.setText("Novo Review");
    }

    public void addReview(View view) {
        String city = editTextCity.getText().toString();
        String country = editTextCountry.getText().toString();
        String description = editTextDescription.getText().toString();
        float rating = ratingBar.getRating();

        Intent intent = new Intent();
        intent.putExtra("city", city);
        intent.putExtra("country", country);
        intent.putExtra("description", description);
        intent.putExtra("rating", rating);

        if( id >= 0 ) {
            intent.putExtra("id", "" + id);
        }
        setResult(1, intent);
        finish();
    }

}