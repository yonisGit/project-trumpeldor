package com.example.bguarts.SharedClasses;

public class AppFeedbackText extends AppFeedback {

    private String answer;

    public AppFeedbackText(int questionNumber, String answer) {
        super(questionNumber);
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
