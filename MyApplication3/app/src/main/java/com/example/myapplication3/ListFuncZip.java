package com.example.myapplication3;

import java.util.ArrayList;

public class ListFuncZip {
    public static int getMinValueIndex(ArrayList<Double> al){
        double min=al.get(0);
        for(int i=1;i<al.size();i++){
            if(min>al.get(i)) min=al.get(i);
        }
        int index=al.indexOf(min);
        return index;
    }

    public static int getMostCommonIndex(ArrayList<Integer> arr){ //3 5 5 2 5 면 5를 리턴
        //빨강 주황 노랑 초록 파랑 보라 하양 검정
        int[] countArr = new int[PixelColor.getColorCodeCount()];
        for(int i=0;i<arr.size();i++){
            countArr[arr.get(i)]++;
        }

        int max=countArr[0];
        int commonIndex=0;
        for(int i=1;i<countArr.length-2;i++){
            if(max<countArr[i]) {
                commonIndex = i;
                max=countArr[i];
            }
        }

        if(arr.size()==(countArr[countArr.length-2]+countArr[countArr.length-1])) //무채색만 있을때
            return AppDatabase.getCountOfColor()-1;
        return commonIndex;
    }
}
