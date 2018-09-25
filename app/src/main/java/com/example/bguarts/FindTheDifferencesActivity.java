package com.example.bguarts;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import com.github.chrisbanes.photoview.PhotoView;

import java.util.LinkedList;

public class FindTheDifferencesActivity extends AppCompatActivity {

    TextView test;
    PhotoView ftdImg;
    LinkedList<Difference> difs = new LinkedList<Difference>();
    int finalHeight, finalWidth;
    Canvas canvas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_the_differences);



        test = (TextView)findViewById(R.id.test);

        ftdImg = (PhotoView) findViewById(R.id.ftdImg);

        makeDifferences();

        ftdImg.setOnPhotoTapListener((imageView, x, y) -> processTap(x,y));


        ViewTreeObserver vto = ftdImg.getViewTreeObserver();
        vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            public boolean onPreDraw() {
                // Remove after the first run so it doesn't fire forever
                ftdImg.getViewTreeObserver().removeOnPreDrawListener(this);
                finalHeight = ftdImg.getMeasuredHeight();
                finalWidth = ftdImg.getMeasuredWidth();
                return true;
            }
        });


        BitmapFactory.Options myOptions = new BitmapFactory.Options();
        myOptions.inDither = true;
        myOptions.inScaled = false;
        myOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;// important
        myOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ftdspongebob,myOptions);

        Bitmap workingBitmap = Bitmap.createBitmap(bitmap);
        Bitmap mutableBitmap = workingBitmap.copy(Bitmap.Config.ARGB_8888, true);

        canvas = new Canvas(mutableBitmap);

        ftdImg.setAdjustViewBounds(true);
        ftdImg.setImageBitmap(mutableBitmap);
    }

    private void makeDifferences() {
        //TODO - add here from the DB all the differences
        //For example - spongebob difference
        Difference spongBobrRight = new Difference(0.555, 0.50, 0.23, 0.2);
        Difference spongBobLeft = new Difference(0.09, 0.50, 0.23, 0.2);
        spongBobrRight.setSecondPic(spongBobLeft);
        spongBobLeft.setSecondPic(spongBobrRight);
        difs.add(spongBobrRight);
        difs.add(spongBobLeft);
    }

    private void processTap(float x, float y) {
        //TODO here need to check all the differences and see if the point (x, y) is correct. and to circle the difference.

        test.setText("x = " + x + "\ny = " + y + "\nWidth = " + finalWidth + "" +
                "\nHeight = " + finalHeight + "\nx*Width = " + x*finalWidth + "\ny*Height = " + y*finalHeight);
//        drawCircleOnTheImage(x, y);
        for (Difference d: difs) {
            if(!d.isExist() && d.isInDifference(x,y)) {
                d.draw();
                return;
            }
        }
        //TODO mistakes in the game

    }


    class Difference{
        double x;
        double y;
        double width;
        double height;
        Difference secondPic;
        boolean exist;
        Paint myPaint;

        public Difference(double x, double y, double width, double height){
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.secondPic = null;
            this.exist = false;
            this.myPaint = new Paint();
        }

        public boolean isInDifference(double x, double y){
            return this.x <= x && this.y <= y && (this.x + this.width) >= x && (this.y + this.height) >= y;
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

        public double getWidth() {
            return width;
        }

        public void setWidth(double width) {
            this.width = width;
        }

        public double getHeight() {
            return height;
        }

        public void setHeight(double height) {
            this.height = height;
        }

        public Difference getSecondPic() {
            return secondPic;
        }

        public void setSecondPic(Difference secondPic) {
            this.secondPic = secondPic;
        }

        public boolean isExist() {
            return exist;
        }

        public void setExist(boolean exist) {
            this.exist = exist;
            if(secondPic != null)
                secondPic.exist = exist; //DON'T USE setExist because of the recursion.
        }

        public void draw(){

            myPaint.setStyle(Paint.Style.STROKE);
            myPaint.setAntiAlias(true);
            myPaint.setColor(Color.RED);
            myPaint.setStrokeWidth(10);


            canvas.drawRect((float)this.x*finalWidth, (float)this.y*finalHeight, (float)(this.x +this.width)*finalWidth,(float)(this.y + this.height)*finalHeight, myPaint);



            this.exist = true;

            if(!secondPic.exist)
                secondPic.draw();
            ftdImg.invalidate();
        }
    }
}
