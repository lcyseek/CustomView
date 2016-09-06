package com.example.custom01.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;

import com.example.custom01.Util;

/**
 * Created by luchunyang on 16/8/29.
 *
 *08-29 22:42:00.672 23707-23707/com.example.custom01 I/MyView01: onMeasure: [widthMode=EXACTLY,widthSize=300] [heightMode=AT_MOST,heightSize=1271] [getMeasureWidth=0,getMeasureHeight=0] [getWidth=0,getHeight=0]
 08-29 22:42:00.672 23707-23707/com.example.custom01 I/MyView01: onMeasure: [widthMode=EXACTLY,widthSize=500] [heightMode=EXACTLY,heightSize=300] [getMeasureWidth=500,getMeasureHeight=500] [getWidth=0,getHeight=0]
 08-29 22:42:00.702 23707-23707/com.example.custom01 I/MyView01: onMeasure: [widthMode=EXACTLY,widthSize=300] [heightMode=AT_MOST,heightSize=1319] [getMeasureWidth=500,getMeasureHeight=500] [getWidth=0,getHeight=0]
 08-29 22:42:00.702 23707-23707/com.example.custom01 I/MyView01: onMeasure: [widthMode=EXACTLY,widthSize=500] [heightMode=EXACTLY,heightSize=300] [getMeasureWidth=500,getMeasureHeight=500] [getWidth=0,getHeight=0]
 08-29 22:42:00.712 23707-23707/com.example.custom01 I/MyView01: onMeasure: [widthMode=EXACTLY,widthSize=300] [heightMode=AT_MOST,heightSize=1319] [getMeasureWidth=500,getMeasureHeight=500] [getWidth=500,getHeight=500]
 08-29 22:42:00.712 23707-23707/com.example.custom01 I/MyView01: onMeasure: [widthMode=EXACTLY,widthSize=500] [heightMode=EXACTLY,heightSize=300] [getMeasureWidth=500,getMeasureHeight=500] [getWidth=500,getHeight=500]

 */
public class MyView01 extends LinearLayout {
    public static final String TAG = MyView01.class.getSimpleName();


    public MyView01(Context context) {
        super(context);
    }

    public MyView01(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView01(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.i(TAG, "onDraw: ");
        super.onDraw(canvas);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        Log.i(TAG, "dispatchDraw: ");
        super.dispatchDraw(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        String s = Util.modeDescirbe(widthMode,widthSize,heightMode,heightSize);

        Log.i(TAG, "onMeasure: " + s +  " [getMeasureWidth=" + getMeasuredWidth() + ",getMeasureHeight=" + getMeasuredHeight() + "] [getWidth=" + getWidth() + ",getHeight=" + getHeight() + "]");

//        setMeasuredDimension(500,500);
        super.onMeasure(widthMeasureSpec,heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        Log.i(TAG, "onLayout:  [getMeasureWidth=" + getMeasuredWidth() + ",getMeasureHeight=" + getMeasuredHeight() + "] [getWidth=" + getWidth() + ",getHeight=" + getHeight() + "]");
        super.onLayout(changed, left, top, right, bottom);
    }
//
//    @Override
//    public void layout(int l, int t, int r, int b) {
//        Log.i(TAG, "layout: l=" + l + " t=" + t + " r=" + r + " b=" + b +" gwtWidth="+getWidth()+" getHeight="+getHeight());
//        super.layout(l, t, r, b);
//    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        Log.i(TAG, "onSizeChanged: " + "w=" + w + " h=" + h + " oldW=" + oldw + " oldh=" + oldh+" getWidth="+getWidth()+" getHeight="+getHeight());
        super.onSizeChanged(w, h, oldw, oldh);
    }
}
