package com.example.aiy.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

/**
 * <p>功能简述：
 * <p>Created by Aiy on 2017/8/30.
 */

public class MyBaseViewGroup extends ViewGroup {
    public MyBaseViewGroup(Context context) {
        super(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMeasureMode=MeasureSpec.getMode(widthMeasureSpec);
        int heightMeasureMode=MeasureSpec.getMode(heightMeasureSpec);
        int widthMeasureSize=MeasureSpec.getSize(widthMeasureSpec);
        int heightMeasureSize=MeasureSpec.getSize(heightMeasureSpec);

        int height=0;
        int width=0;
        for (int i=0;i<getChildCount();i++){
            View child=getChildAt(i);
            measureChild(child,widthMeasureSpec,heightMeasureSpec);
            int h=child.getMeasuredHeight();
            int w=child.getMeasuredWidth();
            height+=h;
            width=Math.max(width,w);
        }
        setMeasuredDimension((widthMeasureMode==MeasureSpec.EXACTLY)?widthMeasureSize:width,
                (heightMeasureMode==MeasureSpec.EXACTLY)?heightMeasureSize:height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }
}
