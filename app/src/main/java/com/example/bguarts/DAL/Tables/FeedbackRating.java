package com.example.bguarts.DAL.Tables;

import com.example.bguarts.SharedClasses.AppFeedback;
import com.example.bguarts.SharedClasses.AppFeedbackRating;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class FeedbackRating extends RealmObject implements ProjectRealMObject, Feedback {

    @PrimaryKey
    protected int questionNumber;
    private int rating;

    public FeedbackRating() {
        //DO NOT USE THIS CONSTRUCTOR!!!!!!!!!!!!!!!!
    }

    public FeedbackRating(int questionNumber, int rating) {
        this.questionNumber = questionNumber;
        this.rating = rating;
    }

    public int getQuestionNumber() {
        return questionNumber;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public void insertMeToDB(Realm realm) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                FeedbackRating fr = realm.createObject(FeedbackRating.class);
                fr.rating = rating;
                fr.questionNumber = questionNumber;
            }
        });
    }


    @Override
    public AppFeedback cloneMe() {
        return new AppFeedbackRating(questionNumber,rating);
    }
}
