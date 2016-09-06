package com.example.measure;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();
    private LinearLayout ll;
    private MyLinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ll = (LinearLayout) findViewById(R.id.ll);
        layout = (MyLinearLayout) findViewById(R.id.layout);
    }

    public void infalte01(View view) {
        TextView tv = new TextView(this);
        tv.setText("hello");
        tv.setBackgroundColor(Color.GRAY);
        Log.i(TAG, "measure01: tv.getLayoutParams()->" + tv.getLayoutParams());

        /**
         * ll.addView()流程:
         * 会检测要添加的View有没有LayoutParams,如果没有则会调用generateDefaultLayoutParams() 生成一个.注意这个generateDefaultLayoutParams() 一般在LinearLayout RelativeLayout等里面都进行重写了,
         * 并不会调用父类的. 看LinearLayout的generateDefaultLayoutParams 源码,发现如果mOrientation == VERTICAL,那么会生成 new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
         * 所以才会看见textView水平方向match_parent,垂直方向wrap_content
         */
        ll.addView(tv);

        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) tv.getLayoutParams();

        Log.i(TAG, "measure01: width=" + layoutParams.width + " height=" + layoutParams.height);
    }

    public void infalte02(View view) {

        View v = getLayoutInflater().inflate(R.layout.inflate_item, null);
        //因为root 为null,所以解析出来的view没有LayoutParams,但是却有其他的参数比如说background padding等.但是 margin也是LayoutParams的
        Log.i(TAG, "measure01: getLayoutParams()" + v.getLayoutParams());
        Log.i(TAG, "measure01: getBackGround()->" + (ColorDrawable) v.getBackground());
    }

    public void infalte03(View view) {
        View v = getLayoutInflater().inflate(R.layout.inflate_item, ll, false);
        //因为root 不为null,那么inflate的方法会调用 ll.generateLayoutParams(attrs)的方法,把LinearLayout需要的LayoutParams解析出来.
        //因为最后一个参数是false,所以会把这个参数直接设置进view里面,返回view
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) v.getLayoutParams();
        Log.i(TAG, "measure03: width=" + layoutParams.width + " height=" + layoutParams.height + " leftMargin=" + layoutParams.leftMargin + " rightMargin=" + layoutParams.rightMargin);
        ll.addView(v);
    }


    public void infalte04(View view) {
        View v = getLayoutInflater().inflate(R.layout.inflate_item, ll, true);
        //因为root 不为null,那么inflate的方法会调用 ll.generateLayoutParams(attrs)的方法,把LinearLayout需要的LayoutParams解析出来.
        //因为最后一个参数是true,所以会把这个view 通过 解析出来的LayoutParams参数,添加到ll里面,返回ll
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) v.getLayoutParams();

        /**
         * 注意 为什么layoutParams.leftMargin  和  layoutParams.rightMargin 都为0?
         * 因为这个v并没有设置LayoutParams! 这不是和上面矛盾吗?
         * 并不是!看源码,可知 inflate里面生成个View temp,
         *      如果root!=null attachToRoot = false,那么会给这个temp设置LayoutParams,然后把temp返回.
         *      如果root!=null attachToRoot = true,那么会把这个temp以LayoutParams参数添加到ll里,此时我们的v没有被设置LayoutParams!
         */
        Log.i(TAG, "measure03: width=" + layoutParams.width + " height=" + layoutParams.height + " leftMargin=" + layoutParams.leftMargin + " rightMargin=" + layoutParams.rightMargin);
    }

    public void infalte05(View view) {
        TextView tv = new TextView(this);
        tv.setText("hello");
        tv.setBackgroundColor(Color.GRAY);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        params.leftMargin = 40;
        ll.addView(tv, params);

        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) tv.getLayoutParams();
        if (layoutParams == null) {
            System.out.println("tv params is null.");
            return;
        }
        Log.i(TAG, "measure01: width=" + layoutParams.width + " height=" + layoutParams.height + " leftMargin=" + layoutParams.leftMargin);
    }


    /*************************************************************************************************************************************************************************************/

    public void measure01(View view) {
        View v = getLayoutInflater().inflate(R.layout.measure_item, null);

        int widthSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int heightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);

        v.measure(widthSpec, heightSpec);

        Log.i(TAG, "measure01: MeasuredWidth=" + v.getMeasuredWidth() + " MeasuredHeight=" + v.getMeasuredHeight());//200 130
    }

    public void measure02(View view) {
        View v = getLayoutInflater().inflate(R.layout.measure_item, null);

        int widthSpec = View.MeasureSpec.makeMeasureSpec(100, View.MeasureSpec.EXACTLY);
        int heightSpec = View.MeasureSpec.makeMeasureSpec(100, View.MeasureSpec.EXACTLY);

        v.measure(widthSpec, heightSpec);

        Log.i(TAG, "measure01: MeasuredWidth=" + v.getMeasuredWidth() + " MeasuredHeight=" + v.getMeasuredHeight());//100 100
    }


    /**
     * 在measureChildren中最难的部分：找出传递给child的MeasureSpec。
     * 目的是结合父view的MeasureSpec与子view的LayoutParams信息去找到最好的结果
     * （也就是说子view的确切大小由两方面共同决定：1.父view的MeasureSpec 2.子view的LayoutParams属性）
     *
     * @param spec 父view的详细测量值(MeasureSpec)
     * @param padding view当前尺寸的的内边距和外边距(padding,margin)
     * @param childDimension child在当前尺寸下的布局参数宽高值(LayoutParam.width,height)
     */
    public void measure03(View view) {
        LinearLayout group = new LinearLayout(this);

        int heightSpec = View.MeasureSpec.makeMeasureSpec(600, View.MeasureSpec.EXACTLY);
        group.getChildMeasureSpec(heightSpec,0, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    /**
     * getChildMeasureSpec()
    public static int getChildMeasureSpec(int spec, int padding, int childDimension) {
        //父view的模式和大小
        int specMode = MeasureSpec.getMode(spec);
        int specSize = MeasureSpec.getSize(spec);

        //通过父view计算出的子view = 父大小-边距（父要求的大小，但子view不一定用这个值）
        int size = Math.max(0, specSize - padding);

        //子view想要的实际大小和模式（需要计算）
        int resultSize = 0;
        int resultMode = 0;

        //通过1.父view的MeasureSpec 2.子view的LayoutParams属性这两点来确定子view的大小
        switch (specMode) {
            // 当父view的模式为EXACITY时，父view强加给子view确切的值
            case MeasureSpec.EXACTLY:
                // 当子view的LayoutParams>0也就是有确切的值
                if (childDimension >= 0) {
                    //子view大小为子自身所赋的值，模式大小为EXACTLY
                    resultSize = childDimension;
                    resultMode = MeasureSpec.EXACTLY;
                    // 当子view的LayoutParams为MATCH_PARENT时(-1)
                } else if (childDimension == LayoutParams.MATCH_PARENT) {
                    //子view大小为父view大小，模式为EXACTLY
                    resultSize = size;
                    resultMode = MeasureSpec.EXACTLY;
                    // 当子view的LayoutParams为WRAP_CONTENT时(-2)
                } else if (childDimension == LayoutParams.WRAP_CONTENT) {
                    //子view决定自己的大小，但最大不能超过父view，模式为AT_MOST
                    resultSize = size;
                    resultMode = MeasureSpec.AT_MOST;
                }
                break;

            // 当父view的模式为AT_MOST时，父view强加给子view一个最大的值。
            case MeasureSpec.AT_MOST:
                // 道理同上
                if (childDimension >= 0) {
                    resultSize = childDimension;
                    resultMode = MeasureSpec.EXACTLY;
                } else if (childDimension == LayoutParams.MATCH_PARENT) {
                    resultSize = size;
                    resultMode = MeasureSpec.AT_MOST;
                } else if (childDimension == LayoutParams.WRAP_CONTENT) {
                    resultSize = size;
                    resultMode = MeasureSpec.AT_MOST;
                }
                break;

            // 当父view的模式为UNSPECIFIED时，子view为想要的值
            case MeasureSpec.UNSPECIFIED:
                if (childDimension >= 0) {
                    // 子view大小为子自身所赋的值
                    resultSize = childDimension;
                    resultMode = MeasureSpec.EXACTLY;
                } else if (childDimension == LayoutParams.MATCH_PARENT) {
                    // 因为父view为UNSPECIFIED，所以MATCH_PARENT的话子类大小为0
                    resultSize = 0;
                    resultMode = MeasureSpec.UNSPECIFIED;
                } else if (childDimension == LayoutParams.WRAP_CONTENT) {
                    // 因为父view为UNSPECIFIED，所以WRAP_CONTENT的话子类大小为0
                    resultSize = 0;
                    resultMode = MeasureSpec.UNSPECIFIED;
                }
                break;
        }
        return MeasureSpec.makeMeasureSpec(resultSize, resultMode);
    }
     *
     */
}
