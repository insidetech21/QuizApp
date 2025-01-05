package com.example.quizapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizapp.adapter.QuestionAdapter;
import com.example.quizapp.viewmodel.QuizViewModel;
import com.google.firebase.FirebaseApp;

public class MainActivity extends AppCompatActivity {
    private QuizViewModel viewModel;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;  // ProgressBar for loader
    private int score = 0; // To track the score
    private int totalQuestions = 0; // Total questions count

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Firebase
        FirebaseApp.initializeApp(this);

        // Initialize RecyclerView and ProgressBar
        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.ProgressBar);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Add animations
        DefaultItemAnimator animator = new DefaultItemAnimator();
        animator.setAddDuration(300); // Animation duration for item addition
        animator.setRemoveDuration(300); // Animation duration for item removal
        recyclerView.setItemAnimator(animator);

        // Initialize ViewModel and observe data
        viewModel = new ViewModelProvider(this).get(QuizViewModel.class);

        // Show loader while data is loading
        progressBar.setVisibility(ProgressBar.VISIBLE);

        viewModel.getQuestions().observe(this, questions -> {
            if (questions != null && !questions.isEmpty()) {
                Log.d("MainActivity", "Questions received: " + questions.size());
                totalQuestions = questions.size(); // Set total questions count

                // Hide loader after receiving data
                progressBar.setVisibility(ProgressBar.GONE);

                // Create and set adapter with a callback for score tracking
                QuestionAdapter adapter = new QuestionAdapter(questions, isCorrect -> {
                    if (isCorrect) score++; // Increment score if the answer is correct
                });
                recyclerView.setAdapter(adapter);
            } else {
                Log.d("MainActivity", "No questions received");
                progressBar.setVisibility(ProgressBar.GONE); // Hide loader if no data
            }
        });

        // Initialize and set up Submit button
        Button submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(v -> {
            new AlertDialog.Builder(this).setTitle("Quiz Completed").setMessage("Your Score: " + score + "/" + totalQuestions).setPositiveButton("OK", null).show();
        });
    }
}
