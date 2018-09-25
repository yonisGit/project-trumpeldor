package com.example.bguarts.SharedClasses;

import java.util.List;

public class AppFindTheDifferences extends AppAttraction {
    private String picturePath;
    private List<AppDifference> differences;

    public AppFindTheDifferences(int attractionNumber, String picturePath, List<AppDifference> differences) {
        super(attractionNumber);
        this.picturePath = picturePath;
        this.differences = differences;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public void addDifference(AppDifference dif){
        this.differences.add(dif);
    }

    public AppDifference getDifferenceByIndex(int indexOfDifference){
        return this.differences.get(indexOfDifference);
    }

    public int getSizeOfDifferences(){
        return differences.size();
    }
}
