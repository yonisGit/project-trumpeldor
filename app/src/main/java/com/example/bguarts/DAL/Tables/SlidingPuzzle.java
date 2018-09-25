package com.example.bguarts.DAL.Tables;

import com.example.bguarts.SharedClasses.AppAttraction;
import com.example.bguarts.SharedClasses.AppSlidingPuzzle;

import java.util.LinkedList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class SlidingPuzzle extends RealmObject implements ProjectRealMObject, Attraction {

    @PrimaryKey
    protected int attractionNumber;
    @Required
    private RealmList<String> piecesPaths;

    public SlidingPuzzle() {
        //DO NOT USE THIS CONSTRUCTOR!!!!!!!!!!!!!!!!
    }

    public SlidingPuzzle(int attractionNumber) {
        this.attractionNumber = attractionNumber;
        this.piecesPaths = new RealmList<String>();
    }

    public int getAttractionNumber() {
        return attractionNumber;
    }

    public String getPiecePath(int indexOfPiece){
        return piecesPaths.get(indexOfPiece);
    }

    public void addPiecePath(String piecePath){
        piecesPaths.add(piecePath);
    }

    public int getSizeOfPieces(){
        return piecesPaths.size();
    }

    @Override
    public void insertMeToDB(Realm realm) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                SlidingPuzzle sp = realm.createObject(SlidingPuzzle.class);
                sp.piecesPaths = piecesPaths;
                sp.attractionNumber = attractionNumber;
            }
        });
    }

    @Override
    public AppAttraction cloneMe() {
        List<String> pieces = new LinkedList<String>();
        pieces.addAll(piecesPaths);
        return new AppSlidingPuzzle(attractionNumber,pieces);
    }
}
