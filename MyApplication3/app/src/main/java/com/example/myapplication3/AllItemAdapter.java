package com.example.myapplication3;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class AllItemAdapter extends BaseAdapter {
    ArrayList<AllappItem> items = new ArrayList<AllappItem>();
    private Context context;

    public AllItemAdapter(Context context){
        this.context = context;
    }

    public int getCount(){
        return items.size();
    }

    public void addItem(AllappItem item){
        items.add(item);
    }

    public Object getItem(int position){
        return items.get(position);
    }

    public long getItemId(int position){
        return position;
    }

    public View getView(int position, View convertView, ViewGroup viewGroup){
        AllappItemView view = null;
        if(convertView == null){
            view= new AllappItemView(context);
        }else{
            view=(AllappItemView) convertView;
        }
        AllappItem item = items.get(position);
        view.setImageView(item.getDrawable());

        return view;
    }
}
