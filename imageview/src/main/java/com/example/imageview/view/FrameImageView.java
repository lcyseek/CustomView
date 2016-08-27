package com.example.imageview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

/**
 * Created by luchunyang on 16/8/19.
 */
public class FrameImageView extends ImageView {

    public static final String TAG = FrameImageView.class.getSimpleName();

    private int mFrameColor;
    private int mFrameWidth;

    public FrameImageView(Context context) {
        this(context,null);
    }

    public FrameImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setFrameColor(int color){
        mFrameColor = color;
    }

    public void setFrameWidth(int width){
        mFrameWidth = width;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //获取Canvas裁剪界限
        Rect rect = canvas.getClipBounds();

        //如果不利用padding扩充矩形,那么边框就要在原图的基础上画了,会稍稍占用原图的边框像素
        rect.bottom -= getPaddingBottom()/2;
        rect.right -= getPaddingRight()/2;
        rect.left += getPaddingLeft()/2;
        rect.top += getPaddingTop()/2;//为什么是+?因为左上方向有padding,坐标会增加.为什么要/2?因为正常画带宽度的边框时,边框的中心和图片边缘对其,我们要的要效果是,边框在图片的外部并且填充满ImageView!

        Log.i(TAG, "onDraw: width="+canvas.getWidth()+" height="+canvas.getHeight());

        //onDraw: rect left=0 top=0 right=200 bottom=200
        Log.i(TAG, "onDraw: rect left="+rect.left +" top="+rect.top+" right="+rect.right+" bottom="+rect.bottom);

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(getPaddingBottom());//mFrameWidth
        paint.setColor(mFrameColor);
        canvas.drawRect(rect,paint);
    }
}
