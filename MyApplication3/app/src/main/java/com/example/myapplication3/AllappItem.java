package com.example.myapplication3;

import android.graphics.drawable.Drawable;

public class AllappItem {
    Drawable drawable;
    public AllappItem(Drawable drawable){
        this.drawable = drawable;
    }

    public Drawable getDrawable() {
        return drawable;
    }

    public void setResId(Drawable drawable) {
        this.drawable = drawable;
    }
}
