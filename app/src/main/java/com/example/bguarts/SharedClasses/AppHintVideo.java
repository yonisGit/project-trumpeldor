package com.example.bguarts.SharedClasses;

public class AppHintVideo extends AppHint {

    private String videoPath;

    public AppHintVideo(int hintNumber, String videoPath) {
        super(hintNumber);
        this.videoPath = videoPath;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }
}
