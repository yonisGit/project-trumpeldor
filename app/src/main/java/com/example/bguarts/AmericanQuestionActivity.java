package com.example.bguarts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AmericanQuestionActivity extends AppCompatActivity {

    int NUMOFOPTIONS = 4;    //TODO - DB
    int answerNumber = 1;    //TODO - DB
    View[] op = new View[NUMOFOPTIONS];
    View question = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_american_question);
        op[0] = findViewById(R.id.op1);
        op[1] = findViewById(R.id.op2);
        op[2] = findViewById(R.id.op3);
        op[3] = findViewById(R.id.op4);

        question = findViewById(R.id.question);
        ((TextView)question).setText("תוכן השאלה יופיע כאן נניח שהתשובה הנכונה היא תשובה מספר 1");      //TODO - DB

        for(int i = 0 ; i < NUMOFOPTIONS ; i++){
            ((Button)op[i]).setText("תשובה מס' " + (i+1)); //TODO - DB
        }
    }


    public void aqFunction(View view) {
        TextView msg = (TextView)findViewById(R.id.msg);
        Integer tag = Integer.parseInt(view.getTag().toString());
        for (View option: op)
            option.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        if (tag.intValue() == answerNumber) {
            view.setBackgroundColor(getResources().getColor(R.color.green));
            msg.setText("תשובה נכונה!!!");
            msg.setTextColor(getResources().getColor(R.color.green));
        } else {
            view.setBackgroundColor(getResources().getColor(R.color.red));
            msg.setText("תשובה שגויה");
            msg.setTextColor(getResources().getColor(R.color.red));
        }
    }
}
