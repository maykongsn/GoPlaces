package com.goplaces.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.goplaces.R;

public class FormReview extends AppCompatActivity {
    int id;

    EditText editTextCity;
    EditText editTextCountry;
    EditText editTextDescription;
    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_review);
        TextView textViewToolbar = findViewById(R.id.textViewToolbar);
        id = -1;

        editTextCity = findViewById(R.id.editTextCity);
        editTextCountry = findViewById(R.id.editTextCountry);
        editTextDescription = findViewById(R.id.editTextDescription);
        ratingBar = findViewById(R.id.ratingBar);
//        if(getIntent().getExtras() != null) {
//            String idString = getIntent().getExtras().getString("id");
//            String city = getIntent().getExtras().getString("city");
//            String country = getIntent().getExtras().getString("country");
//            String description = getIntent().getExtras().getString("description");
//            float rating = getIntent().getExtras().getFloat("rating");
//
//            if(idString != null) {
//                id = Integer.parseInt(idString);
//                Toast.makeText(this, "Id: " + id, Toast.LENGTH_LONG).show();
//            }
//            editTextCity.setText(city);
//            editTextCountry.setText(country);
//            editTextDescription.setText(description);
//            ratingBar.setRating(rating);
//        }

        textViewToolbar.setText("Novo Review");
    }

    public void add(View view) {
        String city = editTextCity.getText().toString();
        String country = editTextCountry.getText().toString();
        String description = editTextDescription.getText().toString();
        float rating = ratingBar.getRating();

        Intent intent = new Intent();
        intent.putExtra("city", city);
        intent.putExtra("country", country);
        intent.putExtra("description", description);
        intent.putExtra("rating", rating);

        if( id >= 0 ) intent.putExtra("id", "" + id );
        setResult(1, intent);
        finish();
    }
}