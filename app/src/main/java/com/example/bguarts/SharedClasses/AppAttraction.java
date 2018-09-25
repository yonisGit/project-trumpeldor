package com.example.bguarts.SharedClasses;

public abstract class AppAttraction {

    protected int attractionNumber;

    public AppAttraction(int attractionNumber) {
        this.attractionNumber = attractionNumber;
    }

    public int getAttractionNumber() {
        return attractionNumber;
    }

    public void setAttractionNumber(int attractionNumber) {
        this.attractionNumber = attractionNumber;
    }
}
