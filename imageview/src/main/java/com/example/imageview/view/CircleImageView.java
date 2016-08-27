package com.example.imageview.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

/**
 * Created by luchunyang on 16/8/19.
 */
public class CircleImageView extends ImageView {

    public static final String TAG = CircleImageView.class.getSimpleName();
    
    public CircleImageView(Context context) {
        this(context,null);
    }

    public CircleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        Drawable drawable = getDrawable();
        if(drawable == null)
            return;

        if(getWidth() == 0 || getHeight() == 0)
            return;

        Log.i(TAG, "onDraw: 1");

        Bitmap src = ((BitmapDrawable)drawable).getBitmap();
        Log.i(TAG, "onDraw: 2");

        Bitmap roundBitmap = getCroppedBitmap(src,getWidth());
        canvas.drawBitmap(roundBitmap,0,0,new Paint(Paint.ANTI_ALIAS_FLAG));
    }

    private Bitmap getCroppedBitmap(Bitmap bitmap, int width) {

        //不能在原图上修改,否则抛异常
        Bitmap copy;
        if(bitmap.getWidth() != width || bitmap.getHeight() != width)
            copy = Bitmap.createScaledBitmap(bitmap,width,width,false);
        else
            copy = bitmap.copy(Bitmap.Config.ARGB_8888,true);

        Bitmap output = Bitmap.createBitmap(width,width,Bitmap.Config.ARGB_8888);
        final Rect rect = new Rect(0, 0, width, width);

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        paint.setColor(Color.parseColor("#BAB399"));

        Canvas c = new Canvas(output);
        c.drawARGB(0, 0, 0, 0);
        c.drawCircle(copy.getWidth() / 2+0.7f, copy.getHeight() / 2+0.7f, copy.getWidth() / 2+0.1f, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        c.drawBitmap(copy, rect, rect, paint);

        return output;
    }
}
