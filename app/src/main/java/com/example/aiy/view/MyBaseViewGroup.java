package com.example.aiy.view;

import android.content.Context;
import android.text.Layout;
import android.util.AttributeSet;
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
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams(p);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(),attrs);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
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
            MarginLayoutParams params=(MarginLayoutParams)child.getLayoutParams();
            int h=child.getMeasuredHeight()+params.topMargin+params.bottomMargin;
            int w=child.getMeasuredWidth()+params.leftMargin+params.rightMargin;
            height+=h;
            width=Math.max(width,w);
        }
        setMeasuredDimension((widthMeasureMode==MeasureSpec.EXACTLY)?widthMeasureSize:width,
                (heightMeasureMode==MeasureSpec.EXACTLY)?heightMeasureSize:height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int top=0;
        int count=getChildCount();
        for (int i=0;i<count;i++){
            View child=getChildAt(i);
            MarginLayoutParams params= (MarginLayoutParams) child.getLayoutParams();
            int childHeight=child.getMeasuredHeight();
            int childWidth=child.getMeasuredWidth();
            child.layout(params.leftMargin,top+params.topMargin,childWidth+params.leftMargin,top+childHeight+params.topMargin);
            top+=(childHeight+params.topMargin+params.bottomMargin);
        }
    }
}
