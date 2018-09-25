package com.example.bguarts.DAL.Tables;

import com.example.bguarts.SharedClasses.AppAttraction;

import io.realm.RealmModel;

public interface Attraction extends RealmModel {

    public AppAttraction cloneMe();
}
