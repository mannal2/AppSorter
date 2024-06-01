package com.example.myapplication3;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

//첫 화면에서 2X2 앱 출력

public class AppItem2View extends LinearLayout {
    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    TextView textView;

    public AppItem2View(Context context){
        super(context);
        init(context);
    }

    public AppItem2View(Context context, AttributeSet attrs){
        super(context, attrs);
        init(context);
    }

    public void init(Context context){
        LayoutInflater inflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.app_item, this, true);

        imageView1 = (ImageView)findViewById(R.id.app_item1);
        imageView2 = (ImageView)findViewById(R.id.app_item2);
        imageView3 = (ImageView)findViewById(R.id.app_item3);
        imageView4 = (ImageView)findViewById(R.id.app_item4);
        textView = (TextView) findViewById(R.id.textView);
    }

    public void setImageViews(Drawable d1,Drawable d2,Drawable d3,Drawable d4){
        imageView1.setImageDrawable(d1);
        imageView2.setImageDrawable(d2);
        imageView3.setImageDrawable(d3);
        imageView4.setImageDrawable(d4);
    }
    public void setTextView(String text){textView.setText(text);}
}
