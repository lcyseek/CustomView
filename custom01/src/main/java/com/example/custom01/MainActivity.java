package com.example.custom01;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * 自定义View步骤
 * 1.自定义属性
 * 2.属性获取
 * 3.重写onMeasure
 * 4.重写onDraw
 *
 */
public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void test(View view) {
        startActivity(new Intent(this,MyViewActivity.class));
    }
}
