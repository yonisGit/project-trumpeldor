package com.example.bguarts.SharedClasses;

import java.util.List;

public class AppAmericanQuestion extends AppAttraction {
    private String question;
    private List<String> answers;
    private int indexOfCorrectAnswer;

    public AppAmericanQuestion(int attractionNumber, String question, List<String> answers, int indexOfCorrectAnswer) {
        super(attractionNumber);
        this.question = question;
        this.answers = answers;
        this.indexOfCorrectAnswer = indexOfCorrectAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswerByIndex(int indexOfAnswer) {
        return answers.get(indexOfAnswer);
    }

    public void setAnswer(String answer, int indexOfAnswer) {
        answers.set(indexOfAnswer, answer);
    }

    public void addAnswer(String ans){
        this.answers.add(ans);
    }

    public int getIndexOfCorrectAnswer() {
        return indexOfCorrectAnswer;
    }

    public void setIndexOfCorrectAnswer(int indexOfCorrectAnswer) {
        this.indexOfCorrectAnswer = indexOfCorrectAnswer;
    }
}
