package com.example.bguarts.DAL.Tables;

import com.example.bguarts.SharedClasses.AppAttraction;
import com.example.bguarts.SharedClasses.AppPuzzle;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Puzzle extends RealmObject implements ProjectRealMObject, Attraction {

    @PrimaryKey
    protected int attractionNumber;
    @Required
    private String puzzlePicturePath;

    public Puzzle() {
        //DO NOT USE THIS CONSTRUCTOR!!!!!!!!!!!!!!!!
    }

    public Puzzle(int attractionNumber, String puzzlePicturePath) {
        this.attractionNumber = attractionNumber;
        this.puzzlePicturePath = puzzlePicturePath;
    }

    public int getAttractionNumber() {
        return attractionNumber;
    }

    public String getPuzzlePicturePath() {
        return puzzlePicturePath;
    }

    public void setPuzzlePicturePath(String puzzlePicturePath) {
        this.puzzlePicturePath = puzzlePicturePath;
    }

    @Override
    public void insertMeToDB(Realm realm) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Puzzle puzzle = realm.createObject(Puzzle.class);
                puzzle.puzzlePicturePath = puzzlePicturePath;
                puzzle.attractionNumber = attractionNumber;
            }
        });
    }

    @Override
    public AppAttraction cloneMe() {
        return new AppPuzzle(attractionNumber,puzzlePicturePath);
    }
}
