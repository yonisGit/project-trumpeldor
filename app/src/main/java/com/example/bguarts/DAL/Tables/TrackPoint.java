package com.example.bguarts.DAL.Tables;

import com.example.bguarts.SharedClasses.AppHint;

import java.util.LinkedList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class TrackPoint extends RealmObject implements ProjectRealMObject {
    @PrimaryKey
    private int pointNumber;
    private int x;
    private int y;
    private String description;
    private RealmList<String> picturesPaths;
    private RealmList<String> videosPaths;
    private RealmList<HintText> textHints;
    private RealmList<HintPicture> picturesHints;
    private RealmList<HintVideo> videosHints;
    private AmericanQuestion americanQuestion;
    private FindTheDifferences ftdAttraction;
    private Puzzle puzzleAttraction;
    private SlidingPuzzle slidingPuzzleAttraction;


    public TrackPoint() {
        //DO NOT USE THIS CONSTRUCTOR!!!!!!!!!!!!!!!!
    }

    public void initialPolymorphismFields(Attraction attraction){
        this.videosHints = new RealmList<HintVideo>();
        this.textHints = new RealmList<HintText>();
        this.picturesHints = new RealmList<HintPicture>();
        setAttraction(attraction);
    }

    public TrackPoint(int pointNumber, int x, int y, String description, Attraction attraction, AmericanQuestion americanQuestion) {
        this.pointNumber = pointNumber;
        this.x = x;
        this.y = y;
        this.description = description;
        this.picturesPaths = new RealmList<String>();
        this.videosPaths = new RealmList<String>();
        this.americanQuestion = americanQuestion;
        initialPolymorphismFields(attraction);
    }

    public int getPointNumber() {
        return pointNumber;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Attraction getAttraction() {
        Attraction ans = ftdAttraction;
        if(ans == null)
            ans = puzzleAttraction;
        if(ans == null)
            ans = slidingPuzzleAttraction;
        return ans;
    }

    public void setAttraction(Attraction attraction) {
        this.ftdAttraction = null;
        this.puzzleAttraction = null;
        this.slidingPuzzleAttraction = null;
        if(attraction instanceof FindTheDifferences)
            this.ftdAttraction = (FindTheDifferences)attraction;
        else if(attraction instanceof Puzzle)
            this.puzzleAttraction = (Puzzle)attraction;
        else if(attraction instanceof SlidingPuzzle)
            this.slidingPuzzleAttraction = (SlidingPuzzle)attraction;
    }

    public AmericanQuestion getAmericanQuestion() {
        return americanQuestion;
    }

    public void setAmericanQuestion(AmericanQuestion americanQuestion) {
        this.americanQuestion = americanQuestion;
    }

    public String getPicturePathByIndex(int pictureIndex){
        return picturesPaths.get(pictureIndex);
    }

    public String getVideoPathByIndex(int videoIndex){
        return videosPaths.get(videoIndex);
    }

    public Hint getHintByIndex(int hintIndex){
        int temp = hintIndex;
        if(textHints.size() > temp)
            return textHints.get(temp);
        temp = temp - textHints.size();
        if(picturesHints.size() > temp)
           return picturesHints.get(temp);
        temp = temp - picturesHints.size();
        if(videosHints.size() > temp)
            return videosHints.get(temp);
        return null;
    }

    public void addPicturePath(String picturePath){
        this.picturesPaths.add(picturePath);
    }

    public void addVideoPath(String videoPath){
        this.videosPaths.add(videoPath);
    }

    public void addHint(Hint hint){
        if(hint instanceof HintText)
            this.textHints.add((HintText)hint);
        else if(hint instanceof HintPicture)
            this.picturesHints.add((HintPicture)hint);
        else if(hint instanceof HintVideo)
            this.videosHints.add((HintVideo)hint);
    }

    public List<AppHint> cloneMyHints(){
        List<AppHint> hints = new LinkedList<AppHint>();
        for (HintText ht:textHints) {
            hints.add(ht.cloneMe());
        }
        for (HintPicture hp:picturesHints) {
            hints.add(hp.cloneMe());
        }
        for (HintVideo hv:videosHints) {
            hints.add(hv.cloneMe());
        }
        return hints;
    }

    public List<String> getAllPicturePaths(){
        List<String> ans = new LinkedList<String>();
        ans.addAll(picturesPaths);
        return ans;
    }

    public List<String> getAllVideoPaths(){
        List<String> ans = new LinkedList<String>();
        ans.addAll(videosPaths);
        return ans;
    }

    @Override
    public void insertMeToDB(Realm realm) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                TrackPoint tp = realm.createObject(TrackPoint.class);
                tp.textHints = textHints;
                tp.picturesHints = picturesHints;
                tp.videosHints = videosHints;
                tp.videosPaths = videosPaths;
                tp.picturesPaths = picturesPaths;
                tp.americanQuestion = americanQuestion;
                tp.slidingPuzzleAttraction = slidingPuzzleAttraction;
                tp.puzzleAttraction = puzzleAttraction;
                tp.ftdAttraction = ftdAttraction;
                tp.pointNumber = pointNumber;
                tp.description = description;
                tp.x = x;
                tp.y = y;
            }
        });
    }
}
