package com.goplaces.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.goplaces.R;
import com.goplaces.helper.FirebaseHelper;
import com.goplaces.model.User;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        findViewById(R.id.imageButtonBack).setOnClickListener(v -> finish());

        TextView textViewToolbar = findViewById(R.id.textViewToolbar);
        textViewToolbar.setText("Criar conta");
    }
}