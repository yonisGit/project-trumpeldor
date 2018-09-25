package com.example.bguarts.DAL;

import android.content.Context;

import com.example.bguarts.SharedClasses.*;

import java.util.List;

public interface DataLayer {
//    public static enum TABLE_NAME{AMERICAN_QUESTION, DIFFERENCE, FIND_THE_DIFFERENCES, HINT_PICTURE, HINT_TEXT, HINT_VIDEO, PUZZLE, SLIDING_PUZZLE, TRACK, TRACK_POINT, USER};

    /**
     * Initial the database.
     */
    public void initDataBase(Context context);

    public void deleteAllDataFromDB();


    /**
     * Takes the object data and insert it in the external DB.
     * @param data - the data that should insert.
     * @throws Exception
     */
    public void insertNewItem(Object data) throws Exception;

    /**
     * get track's points with locations only to calculate the track.
     * @param trackNumber - the chosen track.
     * @return - <pointNumber, point's location>
     * @throws Exception
     */
    public List<AppTrackPointLocation> getTrackPointsAndLocations(int trackNumber) throws Exception;

    /**
     * get the track point from it's point number.
     * it will include all the data that track point has (videos, pictures, attractions, ...)
     * @param pointNumber - track point's primary key
     * @return - the right track point.
     * @throws Exception
     */
    public AppTrackPoint getTrackPointByPointNumber(int pointNumber) throws Exception;

    /**
     * get all the feedbacks in the system ;
     * @return - list of feedback objects.
     */
    public List<AppFeedback> getAllFeedBacks() throws Exception;

    /**
     * get the top users in the system according to Users' score.
     * @return - list of users.
     */
    public List<AppUser> getTopUsers() throws Exception;


}
