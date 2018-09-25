package com.example.bguarts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.bguarts.DAL.DataLayer;
import com.example.bguarts.DAL.DataLayerRealm;
import com.example.bguarts.DAL.Tables.User;

import java.util.List;

import com.example.bguarts.SharedClasses.AppUser;
import com.facebook.stetho.Stetho;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity {

    //todo change to proxy
    public DataLayer dal = new DataLayerRealm();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dal.initDataBase(this); //init once
//        Realm.init(this);

//        if (BuildConfig.DEBUG) {
//            Stetho.initialize(
//                    Stetho.newInitializerBuilder(this)
//                            .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
//                            .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
//                            .build()
//            );
//        }
//
//
//        //for me
//        dal.deleteAllDataFromDB();
//        TextView t = (TextView)findViewById(R.id.debug);
//        User a = new User("a");
//        a.setScore(1);
//        User b = new User("b");
//        b.setScore(2);
//        User c = new User("c");
//
//        try {
//            dal.insertNewItem(a);
//            dal.insertNewItem(b);
//            dal.insertNewItem(c);
//            List<AppUser> users = dal.getTopUsers();
//            String msg = "";
//            for (AppUser u:users) {
//                msg = msg + u.toString() + "\n";
//            }
//            t.setText(msg);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


    }
}
