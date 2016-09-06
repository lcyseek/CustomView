package com.example.custom;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.example.custom.view.view02;

public class MainActivity extends AppCompatActivity {
    private LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ll = (LinearLayout) findViewById(R.id.group);
    }

    public void customView01(View view) {
        startActivity(new Intent(this,CustomView01Activity.class));
    }

    public void customView02(View view) {
        startActivity(new Intent(this,CustomView02Activity.class));
    }

    public void customViewGroup01(View view) {
    }

    public void doMeasure(View view) {
        view02 v = new view02(this);

        int widthSpec = View.MeasureSpec.makeMeasureSpec(800, View.MeasureSpec.AT_MOST);
        int heightSpec = View.MeasureSpec.makeMeasureSpec(800, View.MeasureSpec.AT_MOST);

        v.measure(widthSpec,heightSpec);
        v.setBackgroundColor(Color.YELLOW);

        System.out.println("-->"+v.getMeasuredWidth()+"|"+v.getMeasuredHeight());
//        ll.addView(v);
    }
}
