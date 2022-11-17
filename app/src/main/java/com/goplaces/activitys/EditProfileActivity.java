package com.goplaces.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.goplaces.R;

public class EditProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        findViewById(R.id.imageButtonBack).setOnClickListener(view -> finish());
    }
}