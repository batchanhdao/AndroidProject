package com.example.duoihinhbatchu.adapter;

import java.util.ArrayList;
import java.util.Random;

public class MegreData {

    public ArrayList<String> megre(String str){
        ArrayList<String> result = new ArrayList<>();
        ArrayList<String> temp = new ArrayList<>();
        String[] s = str.split(" ");
        for (int i = 0; i < s.length; i++) {
            temp.add(s[i]);
        }
        System.out.println(temp);
        Random random = new Random();
        while(temp.size()>0){
            int index = random.nextInt(temp.size());
            result.add(temp.remove(index).trim());
        }
        System.out.println(result);
        return result;
    }

    public void main(String[] args) {
        ArrayList<String> s = megre("có công mài sắt có ngày nên kim");
    }


}
