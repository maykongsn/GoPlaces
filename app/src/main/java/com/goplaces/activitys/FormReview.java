package com.goplaces.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.goplaces.R;

public class FormReview extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_review);
        TextView textViewToolbar = findViewById(R.id.textViewToolbar);

        textViewToolbar.setText("Novo Review");
    }
}