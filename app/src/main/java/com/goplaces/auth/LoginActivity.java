package com.goplaces.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.goplaces.activitys.MainActivity;
import com.goplaces.R;
import com.goplaces.helper.FirebaseHelper;

public class LoginActivity extends AppCompatActivity {
    private EditText editTextEmail;
    private EditText editTextPassword;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ImageButton imageButtonToolbar = findViewById(R.id.imageButtonBack);
        TextView textViewToolbar = findViewById(R.id.textViewToolbar);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        progressBar = findViewById(R.id.progressBar);

        textViewToolbar.setText("Acesse sua conta");
        imageButtonToolbar.setVisibility(View.GONE);
    }

    public void createAccount(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
    }

    public void auth(View view) {
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();

        progressBar.setVisibility(View.VISIBLE);

        FirebaseHelper.getAuth().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()) {
                        startActivity(new Intent(this, MainActivity.class));
                        finish();
                    } else {
                        Toast.makeText(this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    progressBar.setVisibility(View.GONE);
                });
    }
}