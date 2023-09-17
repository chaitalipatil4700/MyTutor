package com.tutor.mytutor.model;



import java.util.List;
import java.util.Map;

public class Tutor {
    private final int tutorId;
    private final Map<Question, List<String>> questionToSelectedOptions;

    public Tutor(int tutorId, Map<Question, List<String>> questionToSelectedOptions) {
        this.tutorId = tutorId;
        this.questionToSelectedOptions = questionToSelectedOptions;
    }

    public int getTutorId() {
        return tutorId;
    }

    public Map<Question, List<String>> getQuestionToSelectedOptions() {
        return questionToSelectedOptions;
    }
}