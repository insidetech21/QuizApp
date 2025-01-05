package com.example.quizapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizapp.R;
import com.example.quizapp.model.Question;

import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder> {

    private final List<Question> questions;
    private final OnQuestionAnsweredListener listener;

    public QuestionAdapter(List<Question> questions, OnQuestionAnsweredListener listener) {
        this.questions = questions;
        this.listener = listener;
    }

    @NonNull
    @Override
    public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_question, parent, false);
        return new QuestionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionViewHolder holder, int position) {
        Question question = questions.get(position);
        holder.questionText.setText((position + 1) + ". " + question.getQuestion());

        // Set unique background colors for questions
        int[] questionColors = {
                R.color.colorQuestion1,
                R.color.colorQuestion2,
                R.color.colorQuestion3,
                R.color.colorQuestion4
        };
        holder.cardView.setCardBackgroundColor(
                holder.itemView.getContext().getResources().getColor(
                        questionColors[position % questionColors.length]
                )
        );

        // Setup OptionAdapter
        OptionAdapter optionAdapter = new OptionAdapter(
                question.getOptions(),
                question.getAnswer(),
                isCorrect -> listener.onQuestionAnswered(isCorrect)
        );
        holder.optionsRecyclerView.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext()));
        holder.optionsRecyclerView.setAdapter(optionAdapter);
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    static class QuestionViewHolder extends RecyclerView.ViewHolder {
        TextView questionText;
        RecyclerView optionsRecyclerView;
        androidx.cardview.widget.CardView cardView; // Add cardView reference

        public QuestionViewHolder(@NonNull View itemView) {
            super(itemView);
            questionText = itemView.findViewById(R.id.questionTextView);
            optionsRecyclerView = itemView.findViewById(R.id.optionsRecyclerView);
            cardView = itemView.findViewById(R.id.cardView); // Initialize cardView
        }
    }

    public interface OnQuestionAnsweredListener {
        void onQuestionAnswered(boolean isCorrect);
    }
}
