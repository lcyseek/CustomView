package com.example.custom;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.custom.view.view01;

public class CustomView01Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view01);
    }

    public void reMeasure(View view) {
        view01 v = (view01) findViewById(R.id.v1);
        v.requestLayout();
    }
}
