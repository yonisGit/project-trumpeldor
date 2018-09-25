package com.example.bguarts.DAL;

import android.content.Context;

import com.example.bguarts.DAL.Tables.Feedback;
import com.example.bguarts.DAL.Tables.FeedbackRating;
import com.example.bguarts.DAL.Tables.FeedbackText;
import com.example.bguarts.DAL.Tables.Hint;
import com.example.bguarts.DAL.Tables.ProjectRealMObject;
import com.example.bguarts.DAL.Tables.Track;
import com.example.bguarts.DAL.Tables.TrackPoint;
import com.example.bguarts.DAL.Tables.User;
import com.example.bguarts.SharedClasses.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmModel;
import io.realm.RealmResults;
import io.realm.Sort;

public class DataLayerRealm implements DataLayer {

    Realm db;
    @Override
    public void initDataBase(Context context) {
        Realm.init(context);
        RealmConfiguration config = new RealmConfiguration.Builder().name("bguarts.realm").build();
        Realm.setDefaultConfiguration(config);
    }

    @Override
    public void deleteAllDataFromDB() {

        try(Realm realm = Realm.getDefaultInstance()) {
            realm.beginTransaction();
            realm.deleteAll();
            realm.commitTransaction();
        }
    }

    @Override
    public void insertNewItem(Object data) throws Exception {
        try(Realm realm = Realm.getDefaultInstance()) {
            ((ProjectRealMObject) data).insertMeToDB(realm);
        }
    }


    //may take time because here we retrieve all the track.
    @Override
    public List<AppTrackPointLocation> getTrackPointsAndLocations(int trackNumber) throws Exception {
        List<AppTrackPointLocation> ans = new LinkedList<AppTrackPointLocation>();
        Track t = null;
        try(Realm realm = Realm.getDefaultInstance()) {
            t = realm.where(Track.class).equalTo("trackNumber", trackNumber).findFirst();
            for (TrackPoint tp: t.getAllMyPoints()) {
                ans.add(new AppTrackPointLocation(tp.getPointNumber(), tp.getX(), tp.getY()));
            }
        }
        if(t == null)
            throw new NullPointerException("Track not found");
        return ans;
    }

    @Override
    public AppTrackPoint getTrackPointByPointNumber(int pointNumber) throws Exception {
        TrackPoint tp = null;
        AppTrackPoint ans = null;
        try(Realm realm = Realm.getDefaultInstance()) {
            tp = realm.where(TrackPoint.class).equalTo("pointNumber", pointNumber).findFirst();
            ans = new AppTrackPoint(tp.getPointNumber(), tp.getX(), tp.getY(), tp.getDescription(), tp.getAttraction().cloneMe(), (AppAmericanQuestion)tp.getAmericanQuestion().cloneMe(), tp.cloneMyHints(), tp.getAllPicturePaths(), tp.getAllVideoPaths());
        }
        if(tp == null)
            throw new NullPointerException("TrackPoint not found");
        return ans;
    }

    @Override
    public List<AppFeedback> getAllFeedBacks() throws Exception{
        RealmResults<FeedbackRating> fr = null;
        RealmResults<FeedbackText> ft = null;
        List<AppFeedback> ans = new LinkedList<AppFeedback>();
        try(Realm realm = Realm.getDefaultInstance()) {
            fr = realm.where(FeedbackRating.class).findAll();
            for (FeedbackRating f: fr) {
                ans.add(f.cloneMe());
            }
            ft = realm.where(FeedbackText.class).findAll();
            for (FeedbackText f:ft) {
                ans.add(f.cloneMe());
            }
        }

        if(ans.size() == 0)
            throw new NullPointerException("No Feedbacks");
        return ans;
    }


    //for now we return all users sort by the field score.
    @Override
    public List<AppUser> getTopUsers() throws Exception{
        List<AppUser> ans = new LinkedList<AppUser>();
        RealmResults<User> res = null;
        try(Realm realm = Realm.getDefaultInstance()) {
            res = realm.where(User.class).sort("score", Sort.DESCENDING).findAll();
            for (User u: res) {
                List<Integer> ages = new LinkedList<Integer>();
                ages.addAll(u.getPlayerAges());
                ans.add(new AppUser(u.getName(), ages, u.getScore(), u.getEmail()));
            }
        }
        if(res == null)
            throw new NullPointerException("No Users");
        return ans;
    }


}
