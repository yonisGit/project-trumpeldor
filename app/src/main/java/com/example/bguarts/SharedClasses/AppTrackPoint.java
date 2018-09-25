package com.example.bguarts.SharedClasses;

import java.util.List;

public class AppTrackPoint {
    private int pointNumber;
    private int x;
    private int y;
    private String description;
    private List<String> picturesPaths;
    private List<String> videosPaths;
    private List<AppHint> hints;
    private AppAmericanQuestion americanQuestion;
    private AppAttraction attraction;


    public AppTrackPoint(int pointNumber, int x, int y, String description, AppAttraction attraction, AppAmericanQuestion americanQuestion, List<AppHint> hints, List<String> picturesPaths, List<String> videosPaths)  {
        this.pointNumber = pointNumber;
        this.x = x;
        this.y = y;
        this.description = description;
        this.picturesPaths = picturesPaths;
        this.videosPaths = videosPaths;
        this.hints = hints;
        this.americanQuestion = americanQuestion;
        this.attraction = attraction;
    }

    public int getPointNumber() {
        return pointNumber;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AppAttraction getAttraction() {
        return attraction;
    }

    public void setAttraction(AppAttraction attraction) {
        this.attraction = attraction;
    }

    public AppAmericanQuestion getAmericanQuestion() {
        return americanQuestion;
    }

    public void setAmericanQuestion(AppAmericanQuestion americanQuestion) {
        this.americanQuestion = americanQuestion;
    }

    public String getPicturePathByIndex(int pictureIndex){
        return picturesPaths.get(pictureIndex);
    }

    public String getVideoPathByIndex(int videoIndex){
        return videosPaths.get(videoIndex);
    }

    public AppHint getHintByIndex(int hintIndex){
        return hints.get(hintIndex);
    }

    public void addPicturePath(String picturePath){
        this.picturesPaths.add(picturePath);
    }

    public void addVideoPath(String videoPath){
        this.videosPaths.add(videoPath);
    }

    public void addHint(AppHint hint){
        this.addHint(hint);
    }
}
