package com.example.aiy.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * <p>功能简述：
 * <p>Created by Aiy on 2017/8/30.
 */

public class Flow_View extends ViewGroup {

    public Flow_View(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(),attrs);
    }
    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams(p);
    }
    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode=MeasureSpec.getMode(widthMeasureSpec);
        int widthSize=MeasureSpec.getSize(widthMeasureSpec);
        int heightMode=MeasureSpec.getMode(heightMeasureSpec);
        int heightSize=MeasureSpec.getSize(heightMeasureSpec);

        int lineWidth=0;
        int lineHeight=0;
        int layoutWidth=0;
        int layoutHeight=0;

        for (int i=0;i<getChildCount();i++){
            View child=getChildAt(i);
            measureChildWithMargins(child,widthMeasureSpec,0,heightMeasureSpec,0);
            MarginLayoutParams layoutParams= (MarginLayoutParams) child.getLayoutParams();
            int childWidth=child.getMeasuredWidth()+layoutParams.leftMargin+layoutParams.rightMargin;
            int childHeight=child.getMeasuredHeight()+layoutParams.topMargin+layoutParams.bottomMargin;
            if (lineWidth+childWidth > widthSize){
                layoutWidth=Math.max(lineWidth,childWidth);
                layoutHeight+=lineHeight;
                lineWidth=childWidth;
                lineHeight=childHeight;
            }else {
                lineWidth+=childWidth;
                lineHeight=Math.max(lineHeight,childHeight);
            }
            if (i==(getChildCount()-1)){
                layoutHeight+=lineHeight;
                layoutWidth=Math.max(layoutWidth,lineWidth);
            }
        }

        setMeasuredDimension((widthMode==MeasureSpec.EXACTLY)?widthSize:layoutWidth,
                (heightMode==MeasureSpec.EXACTLY)?heightSize:layoutHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int top=0;
        int left=0;
        int lineWidth=0;
        int lineHeight=0;

        for (int i=0;i<getChildCount();i++){
            View child=getChildAt(i);
            MarginLayoutParams lp= (MarginLayoutParams) child.getLayoutParams();
            int childHeight=lp.topMargin+lp.bottomMargin+child.getMeasuredHeight();
            int childWidth=lp.leftMargin+lp.rightMargin+child.getMeasuredWidth();
            if (childWidth+lineWidth>getMeasuredWidth()){
                left=0;
                top+=lineHeight;
                lineHeight=childHeight;
                lineWidth=childWidth;
            }else{
                lineWidth+=childWidth;
                lineHeight=Math.max(lineHeight,childHeight);
            }
            int leftPoint=left+lp.leftMargin;
            int topPoint=top+lp.topMargin;
            int rightPoint=leftPoint+child.getMeasuredWidth();
            int bottomPoint=topPoint+child.getMeasuredHeight();
            child.layout(leftPoint,topPoint,rightPoint,bottomPoint);
            left+=childWidth;
        }
    }
}
