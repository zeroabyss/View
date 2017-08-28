package com.example.aiy.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.RegionIterator;
import android.util.Log;
import android.view.View;

/**
 * Created by Aiy on 2017/8/28.
 */

public class Canvas_Region extends View{
    private static final String TAG = "Canvas_Region";
    private Context contex;
    private Paint paint;
    private Region region;
    public Canvas_Region(Context context) {
        super(context);
        this.contex=context;
        paint=new Paint();
        region=new Region();
        paint.setAntiAlias(true);
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
       // regionSetPath(canvas);
        regionOp(canvas, Region.Op.INTERSECT);
    }

    private void createRegion(Canvas canvas){
        region.set(20,20,200,500);
        drawRegion(canvas,region,paint);
    }

    private void regionSetPath(Canvas canvas){
        paint.setStyle(Paint.Style.STROKE);
        region.setEmpty();
        Path path=new Path();

        //RectF Rect区别是精度，一个是float 一个是int
        RectF rectF=new RectF(20,20,200,500);
        path.addOval(rectF, Path.Direction.CCW);

        region.setPath(path,new Region(20,20,200,200));
        drawRegion(canvas,region,paint);
    }

    private void regionOp(Canvas canvas, Region.Op op){
        Rect rect1=new Rect(0,100,300,200);
        Rect rect2=new Rect(100,0,200,300);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(rect1,paint);
        canvas.drawRect(rect2,paint);
        Region region1=new Region(rect1);
        Region region2=new Region(rect2);

        region1.op(region2, op);
        Paint paintRegion=new Paint();
        paintRegion.setStyle(Paint.Style.FILL);
        paintRegion.setColor(Color.RED);
        drawRegion(canvas,region1,paintRegion);

    }
    private void drawRegion(Canvas canvas,Region rgn,Paint paint){
        RegionIterator iterator=new RegionIterator(rgn);
        Rect rect=new Rect();
        while (iterator.next(rect)){
            Log.d(TAG, "drawRegion: "+rect.left);
            Log.d(TAG, "drawRegion: "+rect.right);
            Log.d(TAG,rect.top+"");
            canvas.drawRect(rect,paint);
        }
    }
}
