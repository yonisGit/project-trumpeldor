package com.example.bguarts.DAL.Tables;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class User extends RealmObject implements ProjectRealMObject {

    @PrimaryKey @Required
    private String name;
    private RealmList<Integer> playersAges;
    private int score;
    private String email;

    public User() {
        //DO NOT USE THIS CONSTRUCTOR!!!!!!!!!!!!!!!!
    }

    public User(String name) {
        this.name = name;
        playersAges = new RealmList<Integer>();
        this.score = 0;
        this.email = "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addPlayerAge(Integer playerAge){
        playersAges.add(playerAge);
    }

    public void deletePlayer(int indexPlayer){
        playersAges.deleteFromRealm(indexPlayer);
    }

    public List<Integer> getPlayerAges(){
        return this.playersAges;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public void insertMeToDB(Realm realm) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                User user = realm.createObject(User.class, name);
                user.playersAges = playersAges;
                user.email = email;
                user.score = score;
//                user.name = name;
            }
        });
    }
}
