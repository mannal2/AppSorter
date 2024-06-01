package com.example.myapplication3;

import java.util.ArrayList;

public class PixelColor {
    private int red;
    private int green;
    private int blue;

    final static private int[][] colorCode={
        {255,0,0}, //빨강
        {255,50,0}, //주황
        {255,255,0}, //노랑
        {0,255,0}, //초록
        {0,0,255}, //파랑
        {100,0,255}, //보라
        {255,255,255}, //하양
        {0,0,0}, //검정
    };

    public static int getColorCodeCount(){
        return colorCode.length;
    }
    public PixelColor(int r, int g, int b){
        red=r;
        green=g;
        blue=b;
    }

    public int getMostCloseColor(){
        ArrayList<Double> ai = new ArrayList<Double>();
        for(int i=0;i<colorCode.length;i++){
            double sum = Math.pow((red-colorCode[i][0]),2)+Math.pow((green-colorCode[i][1]),2)+Math.pow((blue-colorCode[i][2]),2);
            sum = Math.sqrt(sum);
            ai.add(sum);
        }
        return ListFuncZip.getMinValueIndex(ai);

    }
}
