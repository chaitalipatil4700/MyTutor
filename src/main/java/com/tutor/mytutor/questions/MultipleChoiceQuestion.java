package com.tutor.mytutor.questions;



import com.tutor.mytutor.model.Question;

import java.util.List;
import java.util.Map;

public class MultipleChoiceQuestion extends Question {
    private final Map<String, Integer> optionToScore;

    public MultipleChoiceQuestion(String text, Map<String, Integer> optionToScore) {
        super(text);
        this.optionToScore = optionToScore;
    }

    @Override
    public int calculateScore(List<String> selectedOptions) {
        int totalScore = 0;
        for (String selectedOption : selectedOptions) {
            if (optionToScore.containsKey(selectedOption)) {
                totalScore += optionToScore.get(selectedOption);
            }
        }
        return totalScore;
    }
}