package com.example.quizapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.quizapp.model.Question;
import com.example.quizapp.repository.QuizRepository;

import java.util.List;

public class QuizViewModel extends ViewModel {
    private final QuizRepository repository;
    private final LiveData<List<Question>> questions;

    public QuizViewModel() {
        repository = new QuizRepository();
        questions = repository.getQuestions();
    }

    public LiveData<List<Question>> getQuestions() {
        return questions;
    }
}
