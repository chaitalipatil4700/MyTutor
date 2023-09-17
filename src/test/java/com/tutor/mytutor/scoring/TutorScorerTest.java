package com.tutor.mytutor.scoring;

import com.tutor.mytutor.model.Question;
import com.tutor.mytutor.model.Tutor;
import com.tutor.mytutor.questions.MultipleChoiceQuestion;
import com.tutor.mytutor.questions.SingleChoiceQuestion;
import org.junit.Before;
import org.junit.Test;


import java.util.*;

import static org.junit.Assert.assertEquals;


public class TutorScorerTest {
    Question multipleChoiceQuestion1;
    Question singleChoiceQuestion1;

    @Before
    public void setup(){
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

        multipleChoiceQuestion1 = new MultipleChoiceQuestion("What kind of tutoring experience do you have?", optionToScore1);
        singleChoiceQuestion1 = new SingleChoiceQuestion("How much overall tutoring experience do you have?", optionToScore2);

    }

    @Test
    public void testMultipleChoiceQuestionScoring() {

        // Selecting "Online tutoring" and "Home schooling" should result in a total score of 2.
        List<String> selectedOptions = Arrays.asList("Online tutoring", "Home schooling");
        int score = multipleChoiceQuestion1.calculateScore(selectedOptions);

        assertEquals(2, score);
    }

    @Test
    public void testSingleChoiceQuestionScoring() {
        // Selecting "1-2 years" should result in a score of 1.
        List<String> selectedOptions = Arrays.asList("1-2 years");
        int score = singleChoiceQuestion1.calculateScore(selectedOptions);

        assertEquals(1, score);
    }

    @Test
    public void testTutorScoringValidOptions() {

        Map<Question, List<String>> questionToSelectedOptions = new HashMap<>();
        questionToSelectedOptions.put(multipleChoiceQuestion1, Arrays.asList("Online tutoring", "Home schooling"));
        questionToSelectedOptions.put(singleChoiceQuestion1, Arrays.asList("1-2 years"));

        Tutor tutor = new Tutor(1, questionToSelectedOptions);
        TutorScorer scorer = new TutorScorer();
        int totalScore = scorer.calculateTotalScore(tutor.getQuestionToSelectedOptions());

        assertEquals(3, totalScore); // Total score should be 3 (2 from multipleChoiceQuestion1 + 1 from singleChoiceQuestion1).
    }

    @Test
    public void testTutorScoringValidSingleChoiceInvalidMultipleChoice() {

        Map<Question, List<String>> questionToSelectedOptions = new HashMap<>();
        questionToSelectedOptions.put(multipleChoiceQuestion1, Arrays.asList("Invalid choice", "InvalidChoice"));
        questionToSelectedOptions.put(singleChoiceQuestion1, Arrays.asList("3 or more"));

        Tutor tutor = new Tutor(1, questionToSelectedOptions);
        TutorScorer scorer = new TutorScorer();
        int totalScore = scorer.calculateTotalScore(tutor.getQuestionToSelectedOptions());

        assertEquals(2, totalScore); // Total score should be 2 (0 from multipleChoiceQuestion1 + 2 from singleChoiceQuestion1).
    }

    @Test
    public void testTutorScoringInvalidSingleChoiceValidMultipleChoice() {

        Map<Question, List<String>> questionToSelectedOptions = new HashMap<>();
        questionToSelectedOptions.put(multipleChoiceQuestion1, Arrays.asList("Online tutoring", "Home schooling"));
        questionToSelectedOptions.put(singleChoiceQuestion1, Arrays.asList("Invalid choice"));

        Tutor tutor = new Tutor(1, questionToSelectedOptions);
        TutorScorer scorer = new TutorScorer();
        int totalScore = scorer.calculateTotalScore(tutor.getQuestionToSelectedOptions());

        assertEquals(2, totalScore); // Total score should be 2 (2 from multipleChoiceQuestion1 + 0 from singleChoiceQuestion1).
    }


    @Test
    public void testTutorScoringInvalidSingleChoiceInvalidMultipleChoice() {

        Map<Question, List<String>> questionToSelectedOptions = new HashMap<>();
        questionToSelectedOptions.put(multipleChoiceQuestion1, Arrays.asList("Invalid choice", "Invalid choice"));
        questionToSelectedOptions.put(singleChoiceQuestion1, Arrays.asList("Invalid choice"));

        Tutor tutor = new Tutor(1, questionToSelectedOptions);
        TutorScorer scorer = new TutorScorer();
        int totalScore = scorer.calculateTotalScore(tutor.getQuestionToSelectedOptions());

        assertEquals(0, totalScore); // Total score should be 0 (0 from multipleChoiceQuestion1 + 0 from singleChoiceQuestion1).
    }

    @Test
    public void testTutorScoringValidSingleChoiceEmptyMultipleChoice() {

        Map<Question, List<String>> questionToSelectedOptions = new HashMap<>();
        questionToSelectedOptions.put(multipleChoiceQuestion1, Collections.emptyList());
        questionToSelectedOptions.put(singleChoiceQuestion1, Arrays.asList("1-2 years"));

        Tutor tutor = new Tutor(1, questionToSelectedOptions);
        TutorScorer scorer = new TutorScorer();
        int totalScore = scorer.calculateTotalScore(tutor.getQuestionToSelectedOptions());

        assertEquals(1, totalScore); // Total score should be 1 (0 from multipleChoiceQuestion1 + 1 from singleChoiceQuestion1).
    }

    @Test
    public void testTutorScoringValidMultipleChoiceEmptySingleChoice() {

        Map<Question, List<String>> questionToSelectedOptions = new HashMap<>();
        questionToSelectedOptions.put(multipleChoiceQuestion1, Arrays.asList("Online tutoring", "Home schooling"));
        questionToSelectedOptions.put(singleChoiceQuestion1, Collections.emptyList());

        Tutor tutor = new Tutor(1, questionToSelectedOptions);
        TutorScorer scorer = new TutorScorer();
        int totalScore = scorer.calculateTotalScore(tutor.getQuestionToSelectedOptions());

        assertEquals(2, totalScore); // Total score should be 2 (2 from multipleChoiceQuestion1 + 0 from singleChoiceQuestion1).
    }

    @Test
    public void testTutorScoringEmptyMultipleChoiceEmptySingleChoice() {

        Map<Question, List<String>> questionToSelectedOptions = new HashMap<>();
        questionToSelectedOptions.put(multipleChoiceQuestion1, Collections.emptyList());
        questionToSelectedOptions.put(singleChoiceQuestion1, Collections.emptyList());

        Tutor tutor = new Tutor(1, questionToSelectedOptions);
        TutorScorer scorer = new TutorScorer();
        int totalScore = scorer.calculateTotalScore(tutor.getQuestionToSelectedOptions());

        assertEquals(0, totalScore); // Total score should be 0 (0 from multipleChoiceQuestion1 + 0 from singleChoiceQuestion1).
    }

    @Test
    public void testTutorScoringValidMultipleChoiceMultipleOptionsInSingleChoice() {

        Map<Question, List<String>> questionToSelectedOptions = new HashMap<>();
        questionToSelectedOptions.put(multipleChoiceQuestion1, Arrays.asList("Online tutoring", "Home schooling"));
        questionToSelectedOptions.put(singleChoiceQuestion1, Arrays.asList("1-2 years","3 or more"));

        Tutor tutor = new Tutor(1, questionToSelectedOptions);
        TutorScorer scorer = new TutorScorer();
        int totalScore = scorer.calculateTotalScore(tutor.getQuestionToSelectedOptions());

        assertEquals(2, totalScore); // Total score should be 2 (2 from multipleChoiceQuestion1 + 0 from singleChoiceQuestion1).
    }



}