package com.example.luchunyang.customview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

public class LayoutInflaterActivity extends AppCompatActivity {

    private LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_inflater);

        ll = (LinearLayout) findViewById(R.id.ll);
    }

//    总结一下几种inflate方法的区别：
//    inflate方法在第三个参数root不为空时，返回的View就是root，而当root为空时，返回的才是加载的layout的根节点
//    Øinflate(resId , null ) 只创建temp ,返回temp。
//    Øinflate(resId , parent, false )创建temp，然后执行temp. setLayoutParams(params);返回temp。
//    Øinflate(resId , parent, true ) 创建temp，然后执行root.addView(temp,params);最后返回root。
    public void test1(View view) {
        ll.removeAllViews();
        View v = LayoutInflater.from(this).inflate(R.layout.layout_title_view,null);
        ll.addView(v);
    }

    public void test2(View view) {
        ll.removeAllViews();
        View v = LayoutInflater.from(this).inflate(R.layout.layout_title_view,ll,false);
        System.out.println("----"+v);//RelativeLayout
        ll.addView(v);
    }

    public void test3(View view) {
        ll.removeAllViews();
        View v = LayoutInflater.from(this).inflate(R.layout.layout_title_view,ll,true);
        System.out.println("----"+v);//LinearLayout
//        ll.addView(v);
    }
}
