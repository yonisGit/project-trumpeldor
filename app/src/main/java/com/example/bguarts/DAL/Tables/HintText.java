package com.example.bguarts.DAL.Tables;

import com.example.bguarts.SharedClasses.AppHint;
import com.example.bguarts.SharedClasses.AppHintText;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class HintText extends RealmObject implements ProjectRealMObject, Hint {

    @PrimaryKey
    protected int hintNumber;
    @Required
    private String text;

    public HintText() {
        //DO NOT USE THIS CONSTRUCTOR!!!!!!!!!!!!!!!!
    }

    public HintText(int hintNumber, String text) {
        this.hintNumber = hintNumber;
        this.text = text;
    }

    public int getHintNumber() {
        return hintNumber;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public void insertMeToDB(Realm realm) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                HintText ht = realm.createObject(HintText.class);
                ht.text = text;
                ht.hintNumber = hintNumber;
            }
        });
    }

    @Override
    public AppHint cloneMe() {
        return new AppHintText(hintNumber,text);
    }
}
