package com.example.myapplication3;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class AppAdapter extends BaseAdapter {
    ArrayList<AppItem> items = new ArrayList<AppItem>();
    private Context context;

    public AppAdapter(Context context){
        this.context = context;
    }

    public int getCount(){
        return items.size();
    }

    public void addItem(AppItem item){
        items.add(item);
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    public long getItemId(int position){
        return position;
    }

    public View getView(int position, View convertView, ViewGroup viewGroup){
        AppItem2View view = null;
        if(convertView==null){
            view=new AppItem2View(context);
        } else{
            view = (AppItem2View) convertView;
        }
        AppItem item = items.get(position);
        view.setImageViews(item.getResId1(), item.getResId2(), item.getResId3(), item.getResId4());
        view.setTextView(item.getText());

        return view;
    }


}
