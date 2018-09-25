package com.example.bguarts.SharedClasses;

public abstract class AppHint {

    protected int hintNumber;

    public AppHint(int hintNumber) {
        this.hintNumber = hintNumber;
    }

    public int getHintNumber() {
        return hintNumber;
    }

    public void setHintNumber(int hintNumber) {
        this.hintNumber = hintNumber;
    }
}
