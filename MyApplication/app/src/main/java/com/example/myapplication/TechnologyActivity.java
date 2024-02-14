package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.myapplication.model.Technology;
import com.example.myapplication.model.TechnologyAdapter;

import java.util.ArrayList;

public class TechnologyActivity extends AppCompatActivity {

    private ListView listView;
    TechnologyAdapter technologyAdapter;
    private Technology[] dataList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_technology);
        listView=findViewById(R.id.listTechnology);
        initData();
        technologyAdapter = new TechnologyAdapter(this, dataList);
        listView.setAdapter(technologyAdapter); // hiện trên giao diện theo kiểu ListView
    }

    private void initData() {
        Integer[] imgs = {R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background};
        String[] names = {"android", "ios"};
        String[] describes = {"describe android", "describe ios"};
        String[] subs = {"sub android", "sub ios"};
        dataList = new Technology[imgs.length];
        for(int i=0;i<dataList.length;i++){
            dataList[i] = new Technology(imgs[i],names[i],describes[i],subs[i]);;
        }
    }


}