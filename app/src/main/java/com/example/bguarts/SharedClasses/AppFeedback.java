package com.example.bguarts.SharedClasses;

public abstract class AppFeedback {

    protected int questionNumber;

    public AppFeedback(int questionNumber) {
        this.questionNumber = questionNumber;
    }

    public int getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
    }
}
