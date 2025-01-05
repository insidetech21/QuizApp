package com.example.quizapp.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.quizapp.model.Question;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.DocumentSnapshot;


import java.util.List;
import java.util.ArrayList;

import android.util.Log;


public class QuizRepository {
    private final FirebaseFirestore db;

    public QuizRepository() {
        db = FirebaseFirestore.getInstance();
    }

    public LiveData<List<Question>> getQuestions() {
        MutableLiveData<List<Question>> questionsLiveData = new MutableLiveData<>();

        db.collection("questions").get().addOnSuccessListener(queryDocumentSnapshots -> {
            List<Question> questionList = new ArrayList<>();
            for (DocumentSnapshot doc : queryDocumentSnapshots) {
                Question question = doc.toObject(Question.class);
                if (question != null) {
                    questionList.add(question);
                } else {
                    Log.e("Repository", "Null question found in document");
                }
            }
            Log.d("Repository", "Fetched questions: " + questionList.size());
            questionsLiveData.setValue(questionList);
        }).addOnFailureListener(e -> {
            Log.e("Repository", "Error fetching questions", e);
        });

        return questionsLiveData;
    }

}
