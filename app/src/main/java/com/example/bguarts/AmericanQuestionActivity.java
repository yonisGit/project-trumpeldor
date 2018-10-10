package com.example.bguarts;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AmericanQuestionActivity extends AppCompatActivity {

    int answerNumber;           //TODO - DB
    int image = R.drawable.onion;   //TODO - DB
    int winningScore = 100;         //TODO - change later
    Button[] op;
    View question = null;
    ObjectAnimator anim = null;
    List<String> answers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_american_question);

        answerNumber = 1;           //TODO - DB
        answers=new ArrayList<String>();//TODO -DB
        answers.add("תשובה מס' " + (1));answers.add("תשובה מס' " + (2));answers.add("תשובה מס' " + (3));answers.add("תשובה מס' " + (4));
        

        GridLayout gridLayout =findViewById(R.id.GridLayout1);
        op = new Button[answers.size()];
        int num=0;
        for (String answer : answers)
        {
            op[num]=new Button(this);
            op[num].setText(answer);
            op[num].setId(num+1);
            gridLayout.addView(op[num]);
            op[num].setOnClickListener((View v) -> { aqFunction(v); } );
            op[num].setTag(num+1);
            op[num].setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            num++;

        }


        question = findViewById(R.id.question);
        ((TextView)question).setText("תוכן השאלה יופיע כאן נניח שהתשובה הנכונה היא תשובה מספר 1");      //TODO - DB




        Button infoButton = (Button) findViewById(R.id.info);
        infoButton.setOnClickListener((View v) -> { finish(); } );

        ImageView img= (ImageView) findViewById(R.id.americanQuestionImage);
        image=0;
        if(image==0){
            img.setVisibility(View.GONE);
        }
        else {
            img.setImageResource(image);
        }

    }


    public void aqFunction(View view) {
        Integer tag = Integer.parseInt(view.getTag().toString());
        int color=R.color.colorPrimary;
        if(anim != null)
            anim.end();
        for (View option: op) {
            option.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            option.setEnabled(false);
        }

        color = ((tag.intValue() == answerNumber) ? Color.GREEN : Color.RED);
        anim = ObjectAnimator.ofInt(view, "backgroundColor", color, R.color.colorPrimary,
                color);
        anim.setDuration(800);
        anim.setEvaluator(new ArgbEvaluator());
        anim.setRepeatMode(ValueAnimator.REVERSE);
        anim.setRepeatCount(2);
        if (tag.intValue() == answerNumber) {
            anim.addListener(new AnimatorListenerAdapter(){
                @Override
                public void onAnimationEnd(Animator animation) {
                    //TODO update score, select next track point
                    Intent i = new Intent(AmericanQuestionActivity.this,main_game.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(i);
                }
            });
        } else {
            anim.addListener(new AnimatorListenerAdapter(){
                @Override
                public void onAnimationEnd(Animator animation) {
                    winningScore*=0.75;
                    for (View option: op) {
                        option.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                        option.setEnabled(true);
                    }
                }
            });
        }
        anim.start();
    }
}
