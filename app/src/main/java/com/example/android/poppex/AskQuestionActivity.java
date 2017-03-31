package com.example.android.poppex;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AskQuestionActivity extends AppCompatActivity {

    private static final String EXTRA_QUESTION = "QUESTION";

    private DatabaseReference database;
    private TextView titleTextView;
    private TextView descriptionTextView;
    private AskQuestion question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_question);

        database = FirebaseDatabase.getInstance().getReference();

        titleTextView = (TextView) findViewById(R.id.questionTitle);
        descriptionTextView = (TextView) findViewById(R.id.questionDescription);

        question = getIntent().getParcelableExtra(EXTRA_QUESTION);
        if (question != null) {
            titleTextView.setText(question.getQuestionTitle());
            descriptionTextView.setText(question.getQuestionDescription());
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (question == null) {
                    question = new AskQuestion();
                    question.setUserId(database.child("questions").push().getKey());
                }
                question.setQuestionTitle(titleTextView.getText().toString());
                Toast.makeText(getApplicationContext(), titleTextView.getText().toString(), Toast.LENGTH_SHORT).show();
                question.setQuestionDescription(descriptionTextView.getText().toString());
                database.child("questions").child(question.getUserId()).setValue(question);
                finish();
            }
        });

    }

    public static Intent newInstance(Context context, AskQuestion question) {
        Intent intent = new Intent(context, AskQuestionActivity.class);
        if (question != null) {
            intent.putExtra(EXTRA_QUESTION, question);
        }
        return intent;
    }
}
