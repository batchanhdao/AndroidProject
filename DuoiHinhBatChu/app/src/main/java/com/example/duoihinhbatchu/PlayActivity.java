package com.example.duoihinhbatchu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;

import com.example.duoihinhbatchu.adapter.DapAnAdapter;

import java.util.ArrayList;

public class PlayActivity extends AppCompatActivity {
    ArrayList<String> arrAnwser;
    GridView gdvAnwser;

    ArrayList<String> arrSelect;
    GridView gdvSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        init();
        anhXa();
        showAnwser();
        showSelect();
    }
    private void init(){
        arrAnwser = new ArrayList<>();
        arrAnwser.add("B");
        arrAnwser.add("Ba");
        arrAnwser.add("Be");
        arrAnwser.add("Bo");
        arrAnwser.add("Bi");
        arrAnwser.add("Bu");

        arrSelect = new ArrayList<>();
        arrSelect.add("Ha");
        arrSelect.add("Hi");
        arrSelect.add("Hu");
        arrSelect.add("He");
        arrSelect.add("Ho");
    }

    private  void anhXa(){
        gdvAnwser = findViewById(R.id.gdvAnwser);
        gdvSelect = findViewById(R.id.gdvSelect);
    }

    private void showAnwser(){
        gdvAnwser.setNumColumns(arrAnwser.size());
        gdvAnwser.setAdapter(new DapAnAdapter(this, 0, arrAnwser));
    }

    private void showSelect(){
        gdvSelect.setNumColumns(arrSelect.size());
        gdvSelect.setAdapter(new DapAnAdapter(this, 0, arrSelect));
    }
}