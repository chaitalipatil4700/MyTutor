package com.tutor.mytutor;


import com.tutor.mytutor.model.Question;
import com.tutor.mytutor.model.Tutor;
import com.tutor.mytutor.questions.MultipleChoiceQuestion;
import com.tutor.mytutor.questions.SingleChoiceQuestion;
import com.tutor.mytutor.scoring.TutorScorer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // Example usage:
        Map<String, Integer> optionToScore1 = new HashMap<>();
        optionToScore1.put("Online tutoring", 1);
        optionToScore1.put("Home schooling", 1);
        optionToScore1.put("After school club", 1);
        optionToScore1.put("None", 0);

        Map<String, Integer> optionToScore2 = new HashMap<>();
        optionToScore2.put("0-1 years", 0);
        optionToScore2.put("1-2 years", 1);
        optionToScore2.put("3 or more", 2);
        optionToScore2.put("None", 0);

        Question question1 = new MultipleChoiceQuestion("What kind of tutoring experience do you have?", optionToScore1);
        Question question2 = new SingleChoiceQuestion("How much overall tutoring experience do you have?", optionToScore2);

        Map<Question, List<String>> questionToSelectedOptions = new HashMap<>();
        questionToSelectedOptions.put(question1, List.of("Online tutoring", "Home schooling"));
        questionToSelectedOptions.put(question2, List.of("1-2 years"));

        Tutor tutor = new Tutor(1, questionToSelectedOptions);
        TutorScorer scorer = new TutorScorer();
        int totalScore = scorer.calculateTotalScore(tutor.getQuestionToSelectedOptions());

        System.out.println("Tutor ID: " + tutor.getTutorId());
        System.out.println("Tutor's Total Score: " + totalScore);
    }
}