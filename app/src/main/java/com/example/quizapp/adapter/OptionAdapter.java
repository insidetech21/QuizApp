package com.example.quizapp.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizapp.R;

import java.util.List;

public class OptionAdapter extends RecyclerView.Adapter<OptionAdapter.OptionViewHolder> {

    private final List<String> options;
    private final String correctAnswer;
    private final OnOptionSelectedListener listener;
    private int selectedPosition = -1; // Track selected option

    public OptionAdapter(List<String> options, String correctAnswer, OnOptionSelectedListener listener) {
        this.options = options;
        this.correctAnswer = correctAnswer;
        this.listener = listener;
    }

    @NonNull
    @Override
    public OptionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_option, parent, false);
        return new OptionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OptionViewHolder holder, int position) {
        String option = options.get(position);
        holder.optionText.setText(option);

        // Highlight based on selection and correctness
        if (selectedPosition == position) {
            boolean isCorrect = option.equals(correctAnswer);
            holder.itemView.setBackgroundColor(isCorrect ? Color.parseColor("#4CAF50") // Green for correct
                    : Color.parseColor("#F44336") // Red for wrong
            );
        } else {
            holder.itemView.setBackgroundColor(Color.TRANSPARENT);
        }

        holder.itemView.setOnClickListener(v -> {
            selectedPosition = position;
            notifyDataSetChanged();
            listener.onOptionSelected(option.equals(correctAnswer));
        });
    }


    @Override
    public int getItemCount() {
        return options != null ? options.size() : 0;
    }

    static class OptionViewHolder extends RecyclerView.ViewHolder {
        TextView optionText;

        public OptionViewHolder(@NonNull View itemView) {
            super(itemView);
            optionText = itemView.findViewById(R.id.optionTextView);
        }
    }

    public interface OnOptionSelectedListener {
        void onOptionSelected(boolean isCorrect);
    }
}
