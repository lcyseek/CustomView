package com.example.custom01;

import android.view.View;

/**
 * Created by luchunyang on 16/8/25.
 */
public class Util {
    public static String modeDescirbe(int widthMode,int widthSize,int heightMode,int heightSize){
        String mWidthMode="";
        String mHeightMode = "";

        if(widthMode == View.MeasureSpec.EXACTLY)
            mWidthMode = "EXACTLY";
        else if(widthMode == View.MeasureSpec.AT_MOST)
            mWidthMode = "AT_MOST";
        else if(widthMode == View.MeasureSpec.UNSPECIFIED)
            mWidthMode = "UNSPECIFIED";

        if(heightMode == View.MeasureSpec.EXACTLY)
            mHeightMode = "EXACTLY";
        else if(heightMode == View.MeasureSpec.AT_MOST)
            mHeightMode = "AT_MOST";
        else if(heightMode == View.MeasureSpec.UNSPECIFIED)
            mHeightMode = "UNSPECIFIED";

        return "[widthMode="+mWidthMode+",widthSize="+widthSize+"] ["+"heightMode="+mHeightMode+",heightSize="+heightSize+"]";
    }
}
