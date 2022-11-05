package com.goplaces.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.goplaces.R;

public class RegisterActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        TextView textViewToolbar = findViewById(R.id.textViewToolbar);
        textViewToolbar.setText("Criar conta");

    }

    public void create(View view) {
        startActivity(new Intent(this, LoginActivity.class));
    }

    public void backToLogin() {
        findViewById(R.id.imageButtonBack).setOnClickListener(view -> finish());
    }
}