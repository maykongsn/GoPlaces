package com.goplaces.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.goplaces.MainActivity;
import com.goplaces.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ImageButton imageButtonToolbar = findViewById(R.id.imageButtonBack);
        TextView textViewToolbar = findViewById(R.id.textViewToolbar);

        textViewToolbar.setText("Acesse sua conta");
        imageButtonToolbar.setVisibility(View.GONE);
    }

    public void createAccount(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
    }

    public void auth(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }
}