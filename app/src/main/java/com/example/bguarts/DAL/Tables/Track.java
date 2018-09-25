package com.example.bguarts.DAL.Tables;

import java.util.LinkedList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Track extends RealmObject implements ProjectRealMObject {

    @PrimaryKey
    private int trackNumber;
    private Track subTrack;
    private RealmList<TrackPoint> myTrackPoints;
    private int difficulty; //1 easy, 2 medium, 3 hard,....

    public Track() {
        //DO NOT USE THIS CONSTRUCTOR!!!!!!!!!!!!!!!!
    }

    public Track(int trackNumber) {
        this.trackNumber = trackNumber;
        subTrack = null;
        myTrackPoints = new RealmList<TrackPoint>();
        difficulty = 1;
    }

    public int getTrackNumber() {
        return trackNumber;
    }

    public Track getSubTrack() {
        return subTrack;
    }

    public void setSubTrack(Track subTrack) {
        this.subTrack = subTrack;
        if(this.difficulty < subTrack.getDifficulty())
            this.difficulty = subTrack.getDifficulty();
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public void addTrackPoint(TrackPoint trackPoint){
        this.myTrackPoints.add(trackPoint);
        if(this.difficulty == subTrack.getDifficulty())
            this.difficulty++;
    }

    public boolean removeTrackPointByTrackPointNumber(int trackPointNumber){
        int index = -1;
        for (int i = 0 ; i < this.myTrackPoints.size(); i++){
            if(this.myTrackPoints.get(i).getPointNumber() == trackPointNumber) {
                index = i;
                break;
            }
        }
        if(index == -1)
            return false;
        this.myTrackPoints.deleteFromRealm(index);
        if(myTrackPoints.size() == 0 && subTrack != null)
            this.difficulty = subTrack.getDifficulty();
        return true;
    }

    public List<TrackPoint> getAllMyPoints(){
        if(subTrack == null)
            return myTrackPoints;
        List<TrackPoint> trackPoints = new LinkedList<TrackPoint>();
        trackPoints.addAll(subTrack.getAllMyPoints());
        trackPoints.addAll(myTrackPoints);
        return trackPoints;
    }

    @Override
    public void insertMeToDB(Realm realm) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Track track = realm.createObject(Track.class);
                track.difficulty = difficulty;
                track.subTrack = subTrack;
                track.trackNumber = trackNumber;
                track.myTrackPoints = myTrackPoints;
            }
        });
    }
}
