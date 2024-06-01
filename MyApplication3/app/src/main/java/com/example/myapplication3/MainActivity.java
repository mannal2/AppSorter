package com.example.myapplication3;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.Switch;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    GridView gridView;
    GridView centerGridView;
    AppAdapter appAdapter;
    ConstraintLayout backGround;
    AllItemAdapter allItemAdapter;
    Switch sw;

    ArrayList<AppInfo> all_item_list;
    ArrayList<AppInfo>[] type_order_apps;
    ArrayList<AppInfo>[] color_order_apps;
    String[] type_tags;
    String[] color_tags;
    int currentPosition;

    public void setScreen(ArrayList<AppInfo>[] aai, String[] tag){
        appAdapter = new AppAdapter(this);
        for(int i=0;i<aai.length;i++){ //GridView에 데이터 추가
            if(aai[i].size()==0)
                appAdapter.addItem(new AppItem(null, null, null, null, tag[i]));
            else if (aai[i].size()==1)
                appAdapter.addItem(new AppItem(aai[i].get(0).getAppIcon(), null, null, null, tag[i]));
            else if (aai[i].size()==2)
                appAdapter.addItem(new AppItem(aai[i].get(0).getAppIcon(),aai[i].get(1).getAppIcon(),null,null, tag[i]));
            else if(aai[i].size()==3)
                appAdapter.addItem(new AppItem(aai[i].get(0).getAppIcon(),aai[i].get(1).getAppIcon(),aai[i].get(2).getAppIcon(),null, tag[i]));
            else
                appAdapter.addItem(new AppItem(aai[i].get(0).getAppIcon(),aai[i].get(1).getAppIcon(),aai[i].get(2).getAppIcon(),aai[i].get(3).getAppIcon(), tag[i]));
        }
        gridView.setAdapter(appAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener(){ //각 아이템 클릭시 확대
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id){
                if(CenterGridView.isVisible()) {
                    CenterGridView.setOff();
                }
                else {
                    allItemAdapter = new AllItemAdapter(getApplicationContext());
                    for (int i = 0; i < aai[position].size(); i++) {
                        allItemAdapter.addItem(new AllappItem(aai[position].get(i).getAppIcon()));
                    }
                    centerGridView.setAdapter(allItemAdapter);
                    CenterGridView.setOn();
                    currentPosition=position;
                }
            }
        });

        centerGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String packageName = aai[currentPosition].get(position).getAppPackage();
                Intent runIntent = getPackageManager().getLaunchIntentForPackage(packageName);
                startActivity(runIntent);
            }
        });
    }
    
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        gridView = (GridView) findViewById(R.id.gridView1);
        backGround = (ConstraintLayout) findViewById(R.id.main);
        sw  = (Switch)findViewById(R.id.switch1);

        centerGridView = (GridView)findViewById(R.id.centerGrid);
        CenterGridView.init(centerGridView);

        //AppDatabase 설정
        AppDatabase.init(this);
        type_order_apps = AppDatabase.getTypeOrderApps();
        color_order_apps = AppDatabase.getColorOrderApps();
        type_tags = AppDatabase.getTypeTags();
        color_tags = AppDatabase.getColorTags();
        all_item_list = AppDatabase.getAllItemList();

        gridView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(CenterGridView.isVisible()) CenterGridView.setOff();
                return false;
            }
        });

        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(CenterGridView.isVisible()) CenterGridView.setOff();
                if(isChecked) setScreen(color_order_apps, color_tags);
                else setScreen(type_order_apps, type_tags);
            }
        });

        backGround.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(CenterGridView.isVisible()) {
                    CenterGridView.setOff();
                }
            }
        });

        setScreen(type_order_apps, type_tags);

    }
}