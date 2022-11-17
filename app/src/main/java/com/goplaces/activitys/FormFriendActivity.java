package com.goplaces.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.goplaces.R;

public class FormFriendActivity extends AppCompatActivity {
    EditText editTextUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_friend);

        TextView textViewToolbar = findViewById(R.id.textViewToolbar);
        textViewToolbar.setText("Adicionar amigo");

        findViewById(R.id.imageButtonBack).setOnClickListener(view -> finish());

        editTextUsername = findViewById(R.id.editTextUsername);
    }

    public void addFriend(View view) {
        String username = editTextUsername.getText().toString();

        Intent intent = new Intent();
        intent.putExtra("username", username);

        setResult(1, intent);
        finish();
    }
}