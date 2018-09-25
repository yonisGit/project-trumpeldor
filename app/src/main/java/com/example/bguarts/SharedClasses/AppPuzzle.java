package com.example.bguarts.SharedClasses;

public class AppPuzzle extends AppAttraction {

    private String puzzlePicturePath;

    public AppPuzzle(int attractionNumber, String puzzlePicturePath) {
        super(attractionNumber);
        this.puzzlePicturePath = puzzlePicturePath;
    }

    public String getPuzzlePicturePath() {
        return puzzlePicturePath;
    }

    public void setPuzzlePicturePath(String puzzlePicturePath) {
        this.puzzlePicturePath = puzzlePicturePath;
    }
}
