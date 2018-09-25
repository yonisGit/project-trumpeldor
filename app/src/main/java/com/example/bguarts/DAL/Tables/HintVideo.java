package com.example.bguarts.DAL.Tables;

import com.example.bguarts.SharedClasses.AppHint;
import com.example.bguarts.SharedClasses.AppHintVideo;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class HintVideo extends RealmObject implements ProjectRealMObject, Hint {

    @PrimaryKey
    protected int hintNumber;
    @Required
    private String videoPath;

    public HintVideo() {
        //DO NOT USE THIS CONSTRUCTOR!!!!!!!!!!!!!!!!
    }

    public HintVideo(int hintNumber, String videoPath) {
        this.hintNumber = hintNumber;
        this.videoPath = videoPath;
    }

    public int getHintNumber() {
        return hintNumber;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    @Override
    public void insertMeToDB(Realm realm) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                HintVideo hv = realm.createObject(HintVideo.class);
                hv.videoPath = videoPath;
                hv.hintNumber = hintNumber;
            }
        });
    }

    @Override
    public AppHint cloneMe() {
        return new AppHintVideo(hintNumber,videoPath);
    }
}
