package com.example.bguarts.DAL.Tables;

import com.example.bguarts.SharedClasses.AppHint;
import com.example.bguarts.SharedClasses.AppHintPicture;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class HintPicture extends RealmObject implements ProjectRealMObject, Hint {

    @PrimaryKey
    protected int hintNumber;
    @Required
    private String picturePath;

    public HintPicture() {
        //DO NOT USE THIS CONSTRUCTOR!!!!!!!!!!!!!!!!
    }

    public HintPicture(int hintNumber, String picturePath) {
        this.hintNumber = hintNumber;
        this.picturePath = picturePath;
    }

    public int getHintNumber() {
        return hintNumber;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    @Override
    public void insertMeToDB(Realm realm) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                HintPicture hp = realm.createObject(HintPicture.class);
                hp.picturePath = picturePath;
                hp.hintNumber = hintNumber;
            }
        });
    }

    @Override
    public AppHint cloneMe() {
        return new AppHintPicture(hintNumber,picturePath);
    }
}
