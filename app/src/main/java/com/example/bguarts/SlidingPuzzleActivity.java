package com.example.bguarts;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SlidingPuzzleActivity extends AppCompatActivity {

    int NUMOFCOLUMNS = 3;   //TODO TAKE THAT INFORMATION FROM THE DB
    int NUMOFROWS = 3;   //TODO TAKE THAT INFORMATION FROM THE DB

    int TOTAL = NUMOFCOLUMNS * NUMOFROWS;

    int finalWidth;
    int finalHeight;

    ImageView[] imgV = new ImageView[TOTAL];

    GridView gl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sliding_puzzle);

        gl = (GridView)findViewById(R.id.gridViewPS);
        gl.setAdapter(new MyAdapter(this, TOTAL));
    }


    public class MyAdapter extends BaseAdapter {
        private Context mContext;
        private int total;
        private int blankPos;   //more easy to play with the indexes like that
        private Integer[] indexes;  //each cell represent the number of the photo. indexes[blankPos] = total. (always)
        private boolean isDone;

        // Gets the context so it can be used later
        public MyAdapter(Context c, int total) {
            isDone = false;
            mContext = c;
            this.total = total;
            this.blankPos = total - 1; //because of the 0 position
            indexes = new Integer[total];
            for (int i=0; i < total ; i++) {
                indexes[i] = i+1;
            }

            List<Integer> indTemp = (List<Integer>) Arrays.asList(indexes);

            boolean isShuffled = false;
            boolean isSolvable = false;
            while(!isShuffled || !isSolvable) {
                isShuffled = true;

                Collections.shuffle(indTemp);
                indTemp.toArray(indexes);

                for (int i = 0; i < total; i++) {
                    isShuffled = isShuffled && (indexes[i] != i + 1);
                    if (indexes[i] == total)
                        blankPos = i;
                }
                isSolvable = isSolvableFunc();
            }

        }

        private boolean isSolvableFunc(){
            int inversions = 0;

            for(int i=0;i<indexes.length;i++){
                for(int j = i + 1; j < indexes.length; j++){
                    if(indexes[j]>indexes[i]){
                        inversions++;
                    }
                }
            }

            if(inversions%2 == 1)
                return false;
            return true;
        }

        public MyAdapter(Context c, int total, int blankPos, Integer[] indexes) {
            mContext = c;
            this.total = total;
            this.blankPos = blankPos;
            this.indexes = indexes;

            isDone = true;
            for (int i = 0; i < total; i++)
                isDone = isDone && (indexes[i] == i + 1);
        }


        // Total number of things contained within the adapter
        public int getCount() {
            return total;
        }

        // Require for structure, not really used in my code.
        public Object getItem(int position) {
            return null;
        }

        // Require for structure, not really used in my code. Can
        // be used to get the id of an item in the adapter for
        // manual control.
        public long getItemId(int position) {
            return position;
        }

        public View getView(int position,
                            View convertView, ViewGroup parent) {

            SquareImageView piece;
            if (convertView == null) {
                // if it's not recycled, initialize some attributes
                piece = new SquareImageView(mContext);
//            piece.setLayoutParams(new GridView.LayoutParams(100, 55));
//            btn.setPadding(8, 8, 8, 8);
            }
            else {
                piece = (SquareImageView) convertView;
            }


            //TODO CHANGE THE PICTURES ACCORDING TO DB
            int id = getResources().getIdentifier(getPackageName() + ":drawable/c" + indexes[position], null, null);

            piece.setImageResource(id);
            piece.setScaleType(ImageView.ScaleType.CENTER_CROP);

            piece.setId(position);

            if(!isDone) {
                piece.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (isBlankNextToMe()) {
                            indexes[blankPos] = indexes[position];
                            indexes[position] = total;
                            blankPos = position;
                            gl.setAdapter(new MyAdapter(mContext, total, blankPos, indexes));
                        }
                    }

                    private boolean isBlankNextToMe() {
                        return isBlankFromMyLeft() || isBlankFromMyRight() || isBlankUpToMe() || isBlankDownToMe();
                    }

                    private boolean isBlankFromMyLeft() {
                        return (position % NUMOFCOLUMNS != 0) && (position - 1) == blankPos;
                    }

                    private boolean isBlankFromMyRight() {
                        return (position % NUMOFCOLUMNS != NUMOFCOLUMNS - 1) && (position + 1) == blankPos;
                    }

                    private boolean isBlankUpToMe() {
                        return (position / NUMOFROWS != 0) && (position - NUMOFCOLUMNS) == blankPos;
                    }

                    private boolean isBlankDownToMe() {
                        return (position / NUMOFROWS != NUMOFROWS - 1) && (position + NUMOFCOLUMNS) == blankPos;
                    }
                });

                if(position == blankPos)
                    return new SquareImageView(mContext);
            }

            return piece;
        }
    }

    public class SquareImageView extends android.support.v7.widget.AppCompatImageView {

        public SquareImageView(Context context) {
            super(context);
        }

        public SquareImageView(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        public SquareImageView(Context context, AttributeSet attrs, int defStyle) {
            super(context, attrs, defStyle);
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);

            int width = getMeasuredWidth();
            setMeasuredDimension(width, width);
        }

    }
}
