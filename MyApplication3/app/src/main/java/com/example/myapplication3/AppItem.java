package com.example.myapplication3;

import android.graphics.drawable.Drawable;

// 2X2출력용
public class AppItem {
    Drawable d1, d2, d3, d4;
    String text;
    public AppItem(Drawable d1, Drawable d2, Drawable d3, Drawable d4, String text){
        this.d1=d1;
        this.d2=d2;
        this.d3=d3;
        this.d4=d4;
        this.text = text;
    }

    public Drawable getResId1() {
        return d1;
    }

    public Drawable getResId2() {
        return d2;
    }

    public Drawable getResId3() { return d3;}

    public Drawable getResId4() {
        return d4;
    }

    public String getText(){return text;}
    public void setText(String text){this.text = text;}
}
