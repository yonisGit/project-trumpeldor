package com.example.bguarts.DAL.Tables;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Difference extends RealmObject implements ProjectRealMObject {

    @PrimaryKey
    private int numberDifference;
    private double x;
    private double y;

    public Difference() {
        //DO NOT USE THIS CONSTRUCTOR!!!!!!!!!!!!!!!!
    }

    public Difference(int numberDifference, double x, double y) {
        this.numberDifference = numberDifference;
        this.x = x;
        this.y = y;
    }

    public int getNumberDifference() {
        return numberDifference;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public void insertMeToDB(Realm realm) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Difference df = realm.createObject(Difference.class);
                df.numberDifference = numberDifference;
                df.x = x;
                df.y = y;
            }
        });
    }
}
