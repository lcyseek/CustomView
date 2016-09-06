package com.example.custom.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.custom.ViewUtil;

/**
 * Created by luchunyang on 16/8/30.
 */
public class view02 extends View {

    public static final String TAG = view02.class.getSimpleName();

    public view02(Context context) {
        super(context);
    }

    public view02(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public view02(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        String des = ViewUtil.describeMode(widthMeasureSpec,heightMeasureSpec);
        Log.i(TAG, "onMeasure: "+des);
//        setMeasuredDimension(100,100);
        int widthMode = View.MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = View.MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = View.MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = View.MeasureSpec.getSize(heightMeasureSpec);

        int reqWidth = 0;
        int reqHeight = 0;
        if(widthMode == MeasureSpec.AT_MOST)
            reqWidth = widthSize;
        if(heightMode == MeasureSpec.AT_MOST)
            reqHeight = heightSize;

        setMeasuredDimension(reqWidth,reqHeight);
    }
}
