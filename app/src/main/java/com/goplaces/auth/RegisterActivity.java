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
    private EditText editTextFullName;
    private EditText editTextUsername;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editTextFullName = findViewById(R.id.editTextName);
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);

        TextView textViewToolbar = findViewById(R.id.textViewToolbar);
        textViewToolbar.setText("Criar conta");
    }

    public void addUser(View view) {
        String fullName = editTextFullName.getText().toString();
        String username = editTextUsername.getText().toString();
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();

        User user = new User(fullName, username, email, password);
        FirebaseHelper.getAuth().createUserWithEmailAndPassword(user.getEmail(), user.getPassword())
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()) {
                        Toast.makeText(getApplicationContext(),
                                        "Cadastro concluído!",
                                        Toast.LENGTH_LONG).show();
                        String id = task.getResult().getUser().getUid();

                        user.setId(id);
                        user.save();

                    } else {
                        Toast.makeText(getApplicationContext(),
                                "Falha ao registrar!",
                                Toast.LENGTH_LONG).show();
                    }
                });
    }

    public void create(View view) {
        startActivity(new Intent(this, LoginActivity.class));
    }

    public void backToLogin() {
        findViewById(R.id.imageButtonBack).setOnClickListener(view -> finish());
    }
}