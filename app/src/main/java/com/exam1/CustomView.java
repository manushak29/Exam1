package com.exam1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Anna on 05/04/2016.
 */
public class CustomView extends View implements View.OnTouchListener{
    private ArrayList<Circle> circles = new ArrayList<>();
    private ArrayList<Paint> paints = new ArrayList<>();
    private ArrayList<Integer> radiuses = new ArrayList<>();
    private Random rnd = new Random();
    private int actionIndex;
    private Bitmap bitmap;

    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public Bitmap getBitMap() {
        return bitmap;
    }

    public void setBitMap(Bitmap bitMap) {
        this.bitmap = bitMap;
    }

    protected void onDraw(Canvas canvas) {
        // App crashes because the parameters of this function are not appropriate.
        // Please don't get very upset )
        //canvas.drawBitmap(bitmap, null, null);
        if (circles.size() != 0) {
            for (int i = 0; i < circles.size(); i++) {
                Circle circle = circles.get(i);
                if (circle != null) {
                    canvas.drawCircle(circle.getX(), circle.getY(), radiuses.get(i), paints.get(i));
                }
            }
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        int actionMasked = event.getActionMasked();
        int pointerCount = event.getPointerCount();
        actionIndex = event.getActionIndex();

        switch (actionMasked) {
            case MotionEvent.ACTION_DOWN:
                float cordX = event.getX(actionIndex);
                float cordY = event.getY(actionIndex);
                Circle circle = new Circle();
                circle.setX(cordX);
                circle.setY(cordY);
                circles.add(circle);
                setColorAndRadius();
                invalidate();
                break;

            case MotionEvent.ACTION_POINTER_DOWN:
                Circle moreCircles = new Circle();
                moreCircles.setX(event.getX(actionIndex));
                moreCircles.setY(event.getY(actionIndex));
                circles.add(moreCircles);
                setColorAndRadius();
                invalidate();
                break;

            case MotionEvent.ACTION_MOVE:
                for (int i = 0; i < pointerCount; i++) {
                    Circle c = new Circle();
                    c.setX(event.getX(i));
                    c.setY(event.getY(i));
                    circles.add(c);
                }
                invalidate();
                break;

            case MotionEvent.ACTION_UP:
                break;

            case MotionEvent.ACTION_POINTER_UP:
                break;

        }
        return true;
    }

    public void setColorAndRadius() {
        Paint paint = new Paint();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        paint.setColor(color);
        paints.add(paint);
        radiuses.add(rnd.nextInt());
    }

    public class Circle {
        public float x;
        public float y;


        public float getX() {
            return x;
        }

        public void setX(float x) {
            this.x = x;
        }

        public float getY() {
            return y;
        }

        public void setY(float y) {
            this.y = y;
        }
    }

}
