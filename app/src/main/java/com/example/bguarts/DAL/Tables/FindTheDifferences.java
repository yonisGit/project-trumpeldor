package com.example.bguarts.DAL.Tables;

import com.example.bguarts.SharedClasses.AppAttraction;
import com.example.bguarts.SharedClasses.AppDifference;
import com.example.bguarts.SharedClasses.AppFindTheDifferences;

import java.util.LinkedList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class FindTheDifferences extends RealmObject implements ProjectRealMObject, Attraction {

    @PrimaryKey
    protected int attractionNumber;
    @Required
    private String picturePath;
    private RealmList<Difference> differences;

    public FindTheDifferences() {
        //DO NOT USE THIS CONSTRUCTOR!!!!!!!!!!!!!!!!
    }

    public FindTheDifferences(int attractionNumber, String picturePath) {
        this.attractionNumber = attractionNumber;
        this.picturePath = picturePath;
        this.differences = new RealmList<Difference>();
    }

    public int getAttractionNumber() {
        return attractionNumber;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public void addDifference(Difference dif){
        this.differences.add(dif);
    }

    public Difference getDifferenceByIndex(int indexOfDifference){
        return this.differences.get(indexOfDifference);
    }

    public int getSizeOfDifferences(){
        return differences.size();
    }

    @Override
    public void insertMeToDB(Realm realm) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                FindTheDifferences ftd = realm.createObject(FindTheDifferences.class);
                ftd.differences = differences;
                ftd.picturePath = picturePath;
                ftd.attractionNumber = attractionNumber;
            }
        });
    }

    @Override
    public AppAttraction cloneMe() {
        List<AppDifference> diffs= new LinkedList<AppDifference>();
        for (Difference dif:differences) {
            diffs.add(new AppDifference(dif.getNumberDifference(),dif.getX(),dif.getY()));
        }
        return new AppFindTheDifferences(attractionNumber,picturePath,diffs);
    }
}
