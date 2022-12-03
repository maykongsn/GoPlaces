package com.goplaces.activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.goplaces.R;
import com.goplaces.helper.FirebaseHelper;
import com.goplaces.model.User;

public class EditProfileActivity extends AppCompatActivity {
    private EditText editTextName;
    private EditText editTextUsername;
    private EditText editTextEmail;
    private ProgressBar progressBar;

    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        editTextName = findViewById(R.id.editTextName);
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextEmail = findViewById(R.id.editTextEmail);
        progressBar = findViewById(R.id.progressBar);

        getProfile();

        findViewById(R.id.imageButtonBack).setOnClickListener(view -> finish());
    }

    private void getProfile() {
        DatabaseReference profileReference = FirebaseHelper.getDatabaseReference()
                .child("users")
                .child(FirebaseHelper.getIdFirebase());
        profileReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                user = snapshot.getValue(User.class);
                editTextName.setText(user.getFullName());
                editTextUsername.setText(user.getUsername());
                editTextEmail.setText(user.getEmail());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void updateProfile(View view) {
        String name = editTextName.getText().toString();
        String username = editTextUsername.getText().toString();

        user.setFullName(name);
        user.setUsername(username);

        DatabaseReference userReference = FirebaseHelper.getDatabaseReference();
        userReference.child("users")
                .child(user.getId())
                .setValue(user);
    }
}