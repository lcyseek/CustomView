package com.example.custom01.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;

public class MyView extends LinearLayout {

    public static final String TAG = MyView.class.getSimpleName();

    public MyView(Context context) {
        this(context, null);
    }

    public MyView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
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
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        Log.i(TAG, "onMeasure: [" + "widthSize=" + widthSize + ",heightSize=" + heightSize + "]" + " [getMeasureWidth=" + getMeasuredWidth() + ",getMeasureHeight=" + getMeasuredHeight() + "] [getWidth=" + getWidth() + ",getHeight=" + getHeight() + "]");
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        Log.i(TAG, "onLayout:  [getMeasureWidth=" + getMeasuredWidth() + ",getMeasureHeight=" + getMeasuredHeight() + "] [getWidth=" + getWidth() + ",getHeight=" + getHeight() + "]");
        super.onLayout(changed, left, top, right, bottom);
    }

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
