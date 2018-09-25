package com.example.bguarts.DAL.Tables;

import com.example.bguarts.SharedClasses.AppAmericanQuestion;
import com.example.bguarts.SharedClasses.AppAttraction;

import java.util.LinkedList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmModel;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class AmericanQuestion extends RealmObject implements ProjectRealMObject, Attraction {

    @PrimaryKey
    protected int attractionNumber;
    @Required
    private String question;
    @Required
    private RealmList<String> answers;
    private int indexOfCorrectAnswer;

    public AmericanQuestion() {
        //DO NOT USE THIS CONSTRUCTOR!!!!!!!!!!!!!!!!
    }

    public AmericanQuestion(int attractionNumber, String question, String[] answers, int indexOfCorrectAnswer) {
        this.attractionNumber = attractionNumber;
        this.question = question;
        this.answers = new RealmList<String>();
        for(int i = 0; i < answers.length; i ++)
            this.answers.add(answers[i]);
        this.indexOfCorrectAnswer = indexOfCorrectAnswer;
    }

    public int getAttractionNumber() {
        return attractionNumber;
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

    @Override
    public void insertMeToDB(Realm realm) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                AmericanQuestion aq = realm.createObject(AmericanQuestion.class);
                aq.answers = answers;
                aq.indexOfCorrectAnswer = indexOfCorrectAnswer;
                aq.question = question;
                aq.attractionNumber = attractionNumber;
            }
        });
    }

    @Override
    public AppAttraction cloneMe() {
        List<String> myAnswers = new LinkedList<String>();
        myAnswers.addAll(this.answers);
        return new AppAmericanQuestion(attractionNumber, question, myAnswers, indexOfCorrectAnswer);
    }
}
