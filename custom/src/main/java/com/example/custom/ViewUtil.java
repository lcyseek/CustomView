package com.example.custom;

import android.view.View;

/**
 * Created by luchunyang on 16/8/30.
 */
public class ViewUtil {
    public static String describeMode(int widthMeasureSpec,int heightMeasureSpec){

        int widthMode = View.MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = View.MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = View.MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = View.MeasureSpec.getSize(heightMeasureSpec);

        String width_mode="";
        if(widthMode == View.MeasureSpec.AT_MOST)
            width_mode = "AT_MOST";
        else if(widthMode == View.MeasureSpec.EXACTLY)
            width_mode = "EXACTLY";
        else if(widthMode == View.MeasureSpec.UNSPECIFIED)
            width_mode = "UNSPECIFIED";

        String height_mode="";
        if(heightMode == View.MeasureSpec.AT_MOST)
            height_mode = "AT_MOST";
        else if(heightMode == View.MeasureSpec.EXACTLY)
            height_mode = "EXACTLY";
        else if(heightMode == View.MeasureSpec.UNSPECIFIED)
            height_mode = "UNSPECIFIED";

        return "[widthMode="+width_mode+" widthSize="+widthSize+"  heightMode="+height_mode+" heightSize="+heightSize+"]";
    }
}
