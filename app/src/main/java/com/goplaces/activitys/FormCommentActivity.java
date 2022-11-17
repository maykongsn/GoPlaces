package com.goplaces.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.goplaces.R;

public class FormCommentActivity extends AppCompatActivity {
    EditText editTextComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_comment);

        TextView textViewToolbar = findViewById(R.id.textViewToolbar);
        textViewToolbar.setText("Adicionar comentário");

        findViewById(R.id.imageButtonBack).setOnClickListener(view -> finish());

        editTextComment = findViewById(R.id.editTextComment);
    }

    public void addComment(View view) {
        String message = editTextComment.getText().toString();

        Intent intent = new Intent();
        intent.putExtra("message", message);

        setResult(1, intent);
        finish();
    }
}