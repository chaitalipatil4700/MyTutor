package com.tutor.mytutor.scoring;



import com.tutor.mytutor.model.Question;

import java.util.List;
import java.util.Map;

public class TutorScorer {
    public int calculateTotalScore(Map<Question, List<String>> questionToSelectedOptions) {
        int totalScore = 0;
        for (Question question : questionToSelectedOptions.keySet()) {
            totalScore += question.calculateScore(questionToSelectedOptions.get(question));
        }
        return totalScore;
    }
}