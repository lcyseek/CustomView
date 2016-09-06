package com.example.measure;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by luchunyang on 16/9/2.
 */
public class MyTextView extends TextView {

    public static final String TAG = MyTextView.class.getSimpleName();

    public MyTextView(Context context) {
        super(context);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        String width_mode = "";
        String height_mode = "";

        if (widthMode == MeasureSpec.EXACTLY)
            width_mode = "EXACTLY";
        else if (widthMode == MeasureSpec.AT_MOST)
            width_mode = "AT_MOST";
        else if (widthMode == MeasureSpec.UNSPECIFIED)
            width_mode = "UNSPECIFIED";

        if (heightMode == MeasureSpec.EXACTLY)
            height_mode = "EXACTLY";
        else if (heightMode == MeasureSpec.AT_MOST)
            height_mode = "AT_MOST";
        else if (heightMode == MeasureSpec.UNSPECIFIED)
            height_mode = "UNSPECIFIED";


        Log.i(TAG, "onMeasure: " + "[widthMode=" + width_mode + " widthSize=" + widthSize + " heightMode=" + height_mode + " heightSize=" + heightSize + "]");

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
