package com.example.custom01;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

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

}
