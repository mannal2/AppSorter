package com.example.myapplication3;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public class AllappItemView extends LinearLayout {
    ImageView imageView;

    public AllappItemView(Context context) {
        super(context);
        init(context);
    }

    public AllappItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public void init(Context context){
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.app_item_all, this, true);

        imageView = (ImageView) findViewById(R.id.imageView);
    }

    public void setImageView(Drawable drawable){
        imageView.setImageDrawable(drawable);
    }
}
