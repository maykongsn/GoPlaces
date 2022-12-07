package com.goplaces.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.goplaces.R;

public class FormPlaceActivity extends AppCompatActivity {
    EditText editTextCity;
    EditText editTextCountry;

    String id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_place);

        TextView textViewToolbar = findViewById(R.id.textViewToolbar);
        textViewToolbar.setText("Lugares");

        findViewById(R.id.imageButtonBack).setOnClickListener(view -> finish());

        editTextCity = findViewById(R.id.editTextCity);
        editTextCountry = findViewById(R.id.editTextCountry);

        if(getIntent().getExtras() != null) {
            id = getIntent().getExtras().getString("id");
            String city = getIntent().getExtras().getString("city");
            String country = getIntent().getExtras().getString("country");

            editTextCity.setText(city);
            editTextCountry.setText(country);
        }
    }

    public void addPlace(View view) {
        String city = editTextCity.getText().toString();
        String country = editTextCountry.getText().toString();

        Intent intent = new Intent();
        intent.putExtra("city", city);
        intent.putExtra("country", country);

        if(!id.equals("")) {
            intent.putExtra("id", "" + id);
        }

        setResult(1, intent);
        finish();
    }
}