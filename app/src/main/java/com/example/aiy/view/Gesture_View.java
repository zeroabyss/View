package com.example.aiy.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Switch;

/**
 * <p>功能简述：
 * <p>Created by Aiy on 2017/8/28.
 */

public class Gesture_View extends View {
    private Path path;
    private Paint paint;

    private float preX,preY;
    public Gesture_View(Context context) {
        super(context);
        path=new Path();
        paint=new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.RED);
        //paint.setStrokeWidth(5);
        paint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(path,paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                /*path.moveTo(event.getX(),event.getY());*/
                path.moveTo(event.getX(),event.getY());
                preX=event.getX();
                preY=event.getY();
                return true;
            case MotionEvent.ACTION_MOVE:
                /*path.lineTo(event.getX(),event.getY());
                postInvalidate();*/
                float endX= (preX+event.getX())/2;
                float endY=(preY+event.getY())/2;
                path.quadTo(preX,preY,endX,endY);
                preX=event.getX();
                preY=event.getY();
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                /*path.reset();
                invalidate();
                break;*/
            default:
                break;
        }
        return super.onTouchEvent(event);
    }
}
