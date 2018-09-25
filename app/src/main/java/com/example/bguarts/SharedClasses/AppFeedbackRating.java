package com.example.bguarts.SharedClasses;

public class AppFeedbackRating extends AppFeedback {

    private int rating;

    public AppFeedbackRating(int questionNumber, int rating) {
        super(questionNumber);
        this.rating = rating;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
