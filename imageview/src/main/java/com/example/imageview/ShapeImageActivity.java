package com.example.imageview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.imageview.view.FrameImageView;

/**
 * 各种形状的图片控件
 */
public class ShapeImageActivity extends AppCompatActivity {

    private FrameImageView frameImageView;
    private ImageView iv_1;
    private ImageView iv_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_shape_image);

        iv_1 = (ImageView) findViewById(R.id.iv_1);
        iv_2 = (ImageView) findViewById(R.id.iv_2);

        initFrameImageView();
        setFrameImage();

        RoundRect roundRect = new RoundRect(400,400,200);
        Bitmap photo = roundRect.toRoundRect(this,R.drawable.dushi);
        iv_2.setImageBitmap(photo);
    }

    //初始化自定义控件
    void initFrameImageView(){
        frameImageView = (FrameImageView) findViewById(R.id.frameImageView);
        frameImageView.setFrameColor(Color.RED);
        frameImageView.setFrameWidth(8);
    }

    private void setFrameImage() {
        //如果直接使用资源文件修改,会报错Immutable bitmap passed to Canvas constructor
        //出现Immutable bitmap passed to Canvas constructor错误的原因是如果不用copy的方法，直接引用会对资源文件进行修改，而Android是不允许在代码里修改res文件里的图片
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.meinv);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.meinv).copy(Bitmap.Config.ARGB_8888,true);
        System.out.println("src width="+ bitmap.getWidth()+" height="+bitmap.getHeight());

        Canvas canvas = new Canvas(bitmap);

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        canvas.drawRect(canvas.getClipBounds(),paint);

        //添加边框是在原图基础上添加的,不会再原图的外侧添加,所以图片的尺寸不变!
        System.out.println("add frame width="+ bitmap.getWidth()+" height="+bitmap.getHeight());

        iv_1.setImageBitmap(bitmap);

    }

    class RoundRect {

        private int width;
        private int height;
        private float cornerRadius;

        public RoundRect(int width, int height, float cornerRadius) {
            this.width = width;
            this.height = height;
            this.cornerRadius = cornerRadius;
        }

        Bitmap toRoundRect(Context context, int imageID) {

            //创建位图对象
            Bitmap photo = BitmapFactory.decodeResource(context.getResources(), imageID);

            //根据源文件新建一个darwable对象
            Drawable imageDrawable = new BitmapDrawable(context.getResources(),photo);


            // 新建一个新的输出图片
            Bitmap output = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(output);

            //下面的这句话是为了调试 paint.setXfermode 这个的,仔细看看paint.setXfermode位置!如果背景图的alpha 都不为0  那么原图将都显示!!!
//        canvas.drawColor(Color.BLUE);

            // 新建一个矩形
            RectF outerRect = new RectF(0, 0, width, height);

            // 产生一个红色的圆角矩形
            Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            paint.setColor(Color.RED);

            //画一个和原始图片一样大小的圆角矩形
            canvas.drawRoundRect(outerRect, cornerRadius, cornerRadius, paint);

            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            canvas.saveLayer(outerRect, paint, Canvas.ALL_SAVE_FLAG);

            imageDrawable.setBounds(0, 0, width, height);
            imageDrawable.draw(canvas);
            // 将源图片绘制到这个圆角矩形上,注意此次的绘制没有使用paint,所以不用xfermode的规则来绘制的,是直接绘制到新的layer上
            canvas.restore();

            return output;
        }

    }
}


