package com.example.bguarts.DAL.Tables;

import com.example.bguarts.SharedClasses.AppFeedback;
import com.example.bguarts.SharedClasses.AppFeedbackText;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class FeedbackText extends RealmObject implements ProjectRealMObject, Feedback {

    @PrimaryKey
    protected int questionNumber;
    private String answer;

    public FeedbackText() {
        //DO NOT USE THIS CONSTRUCTOR!!!!!!!!!!!!!!!!
    }

    public FeedbackText(int questionNumber, String answer) {
        this.questionNumber = questionNumber;
        this.answer = answer;
    }

    public int getQuestionNumber() {
        return questionNumber;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public void insertMeToDB(Realm realm) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                FeedbackText ft = realm.createObject(FeedbackText.class);
                ft.questionNumber = questionNumber;
                ft.answer = answer;
            }
        });
    }

    @Override
    public AppFeedback cloneMe() {
        return new AppFeedbackText(questionNumber, answer);
    }
}
