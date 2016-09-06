package com.example.custom.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import com.example.custom.ViewUtil;

/**
 * Created by luchunyang on 16/8/30.
 */
public class view01 extends TextView {

    public static final String TAG = view01.class.getSimpleName();

    public view01(Context context) {
        super(context);
    }

    public view01(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public view01(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        String des = ViewUtil.describeMode(widthMeasureSpec,heightMeasureSpec);
        Log.i(TAG, "onMeasure: "+des +" getMeasureWidth="+getMeasuredWidth()+" getMeasureHeight="+getMeasuredHeight()+" getWidth="+getWidth()+" getHeight="+getHeight());
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public void layout(int l, int t, int r, int b) {
        Log.i(TAG, "layout: "+" getMeasureWidth="+getMeasuredWidth()+" getMeasureHeight="+getMeasuredHeight()+" getWidth="+getWidth()+" getHeight="+getHeight());
        super.layout(l, t, r, b);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        Log.i(TAG, "onSizeChanged: "+" getMeasureWidth="+getMeasuredWidth()+" getMeasureHeight="+getMeasuredHeight()+" getWidth="+getWidth()+" getHeight="+getHeight());
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        Log.i(TAG, "onLayout: "+" getMeasureWidth="+getMeasuredWidth()+" getMeasureHeight="+getMeasuredHeight()+" getWidth="+getWidth()+" getHeight="+getHeight());
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.i(TAG, "onDraw: "+" getMeasureWidth="+getMeasuredWidth()+" getMeasureHeight="+getMeasuredHeight()+" getWidth="+getWidth()+" getHeight="+getHeight());
        super.onDraw(canvas);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        Log.i(TAG, "dispatchDraw: "+" getMeasureWidth="+getMeasuredWidth()+" getMeasureHeight="+getMeasuredHeight()+" getWidth="+getWidth()+" getHeight="+getHeight());
        super.dispatchDraw(canvas);
    }
}
