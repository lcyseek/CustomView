package com.example.custom01.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.custom01.R;
import com.example.custom01.Util;

/**
 * Created by luchunyang on 16/8/25.
 */
public class CustomTitleView extends View {

    public static final String TAG = CustomTitleView.class.getSimpleName();
    private String text;
    private int color;
    private int backColor;
    private int size;
    private Paint mPaint;

    public CustomTitleView(Context context) {
        super(context);
    }

    public CustomTitleView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CustomTitleView, 0, 0);
        text = array.getString(R.styleable.CustomTitleView_cus_title);
        size = array.getDimensionPixelSize(R.styleable.CustomTitleView_cus_title_size, size);
        color = array.getColor(R.styleable.CustomTitleView_cus_title_color, color);
        backColor = array.getColor(R.styleable.CustomTitleView_cus_back_color, backColor);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setTextSize(size);
    }

    //为了适配wrap_content
    // getWidth(): View在設定好佈局後整個View的寬度。
    //getMeasuredWidth(): 對View上的內容進行測量後得到的View內容佔據的寬度
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        Log.i(TAG, "onMeasure: "+getMeasuredWidth()+"|"+getMeasuredHeight());

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int height, width;

        if (widthMode == MeasureSpec.EXACTLY)
            width = widthSize;
        else {
            Rect bounds = new Rect();
            mPaint.getTextBounds(text, 0, text.length(), bounds);
            width = bounds.width() + getPaddingLeft() + getPaddingRight();
        }

        if (heightMode == MeasureSpec.EXACTLY)
            height = heightSize;
        else {
            Rect bounds = new Rect();
            mPaint.getTextBounds(text, 0, text.length(), bounds);
            height = bounds.height() + getPaddingTop() + getPaddingBottom();
        }


        Log.i(TAG, "onMeasure: " + Util.modeDescirbe(widthMode, widthSize, heightMode, heightSize));
        Log.i(TAG, "onMeasure: ---->" + width + "|" + height);
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setColor(backColor);

        Log.i(TAG, "onDraw: ");
        Rect target = new Rect(0, 0, getMeasuredWidth(), getMeasuredHeight());
        canvas.drawRect(target, mPaint);

        Paint.FontMetricsInt metricsInt = mPaint.getFontMetricsInt();
        int baseLine = (target.bottom + target.top - metricsInt.bottom - metricsInt.top) / 2;
        mPaint.setColor(color);
        mPaint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(text, target.centerX(), baseLine, mPaint);
    }

    int i = 0;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            text += i;
            //当view确定自身已经不再适合现有的区域时，该view本身调用这个方法要求parent view重新调用他的onMeasure onLayout来对重新设置自己位置。
            requestLayout();
        }
        return true;
    }
}
