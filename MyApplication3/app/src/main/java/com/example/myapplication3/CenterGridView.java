package com.example.myapplication3;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.GridView;

public class CenterGridView {
    static private GridView centerGridView;
    static private Context context;
    static private Activity activity;

    public static void init(GridView cgv){
        centerGridView = cgv;
    }

    public static boolean isVisible(){
        return centerGridView.getVisibility() == View.VISIBLE;
    }

    public static void setOff(){
        Animation animation = new AlphaAnimation(1, 0);
        animation.setDuration(100);
        centerGridView.setVisibility(View.GONE);
        centerGridView.setAnimation(animation);
    }

    public static void setOn(){
        Animation animation = new AlphaAnimation(0, 1);
        animation.setDuration(100);
        centerGridView.setVisibility(View.VISIBLE);
        centerGridView.setAnimation(animation);
    }
}
