package com.example.bguarts.SharedClasses;

import java.util.List;

public class AppSlidingPuzzle extends AppAttraction {

    private List<String> piecesPaths;

    public AppSlidingPuzzle(int attractionNumber, List<String> piecesPaths) {
        super(attractionNumber);
        this.piecesPaths = piecesPaths;
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
}
