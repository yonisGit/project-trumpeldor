package com.example.bguarts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class CampusArt extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campus_art);

        TextView textView = findViewById(R.id.description);
        //TODO replace ▼
        String loadedDescription="this should replace by loaded description";
        textView.setText(loadedDescription);

        Button homePageButton=findViewById(R.id.homePageButton);
        homePageButton.setOnClickListener((View v) -> {
            startActivity(new Intent(CampusArt.this, MainActivity.class));
        });
    }
}
