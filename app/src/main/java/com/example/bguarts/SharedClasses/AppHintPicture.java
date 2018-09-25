package com.example.bguarts.SharedClasses;

public class AppHintPicture extends AppHint {

    private String picturePath;

    public AppHintPicture(int hintNumber, String picturePath) {
        super(hintNumber);
        this.picturePath = picturePath;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }
}
