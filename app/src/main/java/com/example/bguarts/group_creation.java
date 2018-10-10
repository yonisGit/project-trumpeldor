package com.example.bguarts;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.ActionBar;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.DigitsKeyListener;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class group_creation extends AppCompatActivity {

//TODO add login to facebook and goolge
    TableLayout playersTable;
    int nextPlayerNumber=2;
    int NUMOFPATHS = 3;
    View[] op = new View[NUMOFPATHS];
    Integer length;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_creation);

        findViewById(R.id.startGameGC).setEnabled(false);
        playersTable=findViewById(R.id.ageTable);
        op[0] = findViewById(R.id.shortButton);
        op[1] = findViewById(R.id.mediumButton);
        op[2] = findViewById(R.id.longButton);
        for (View option: op) {
            option.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        }

    }

    public void selectLength(View view){
        length = Integer.parseInt(view.getTag().toString());

        findViewById(R.id.startGameGC).setEnabled(true);
        for (View option: op) {
            option.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            option.setEnabled(true);
        }
        view.setBackgroundColor(getResources().getColor(R.color.green));
        view.setEnabled(false);


    }

    public void addPlayerFunc(View view) {
        TableRow additionalRow=new TableRow(this);
        additionalRow.setLayoutParams((new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT)));
        
        TextView playerNumberTextView=new TextView(this);
        playerNumberTextView.setText(""+nextPlayerNumber);
        nextPlayerNumber++;
        playerNumberTextView.setLayoutParams((new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT)));

        EditText ageEditText=new EditText(this);
        ageEditText.setLayoutParams((new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT)));
        ageEditText.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
        ageEditText.setKeyListener(DigitsKeyListener .getInstance("0123456789"));


        additionalRow.addView(playerNumberTextView,0);
        additionalRow.addView(ageEditText,1);
        playersTable.addView(additionalRow);
        
    }

    public void startButtonFunc(View view) {
        //TODO read from DB track points information

        List <Integer> agesList=new ArrayList();
        for(int i = 0; i < playersTable.getChildCount(); i++) {
            View row1 = playersTable.getChildAt(i);
            if (row1 instanceof TableRow) {
                TableRow row = (TableRow) row1;
                if(row.getChildAt(1)instanceof EditText){
                    EditText et=(EditText)row.getChildAt(1);
                    agesList.add(Integer.parseInt(et.getText().toString()));
                }
            }
        }
        String groupName=((EditText)findViewById(R.id.FroupNameETGC)).getText().toString();

        //length,agesList,groupName


        Intent i = new Intent(group_creation.this,main_game.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);

    }
}
