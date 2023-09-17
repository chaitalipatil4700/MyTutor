package com.tutor.mytutor.model;

import java.util.List;

public abstract class Question {
    protected final String questionText;

    public Question(String questionText) {
        this.questionText = questionText;
    }


    //We can implement hashCode and equals method here, but not needed in this case.

    public abstract int calculateScore(List<String> selectedOptions);
}
