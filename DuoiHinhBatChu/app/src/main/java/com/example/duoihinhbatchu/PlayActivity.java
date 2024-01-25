package com.example.duoihinhbatchu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;

import com.example.duoihinhbatchu.adapter.DapAnAdapter;
import com.example.duoihinhbatchu.adapter.MegreData;

import java.util.ArrayList;

public class PlayActivity extends AppCompatActivity {
    String resuft_ask = "có công mài sắt có ngày nên kim";
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
        createData();
        showAnwser();
        showSelect();
    }
    private void init(){
        arrAnwser = new ArrayList<>();
        arrSelect = new ArrayList<>();
//        arrSelect.add("hê");
    }

    private void anhXa(){
        gdvAnwser = findViewById(R.id.gdvAnwser);
        gdvSelect = findViewById(R.id.gdvSelect);
    }

    private void createData(){
//        megre resuft_ask
        MegreData megre_data = new MegreData();
        ArrayList<String> megre_select_list = megre_data.megre(resuft_ask);

        int n = megre_select_list.size();

//        push data anwser and select
        for (int i = 0; i < n; i++) {
            arrAnwser.add("");
            arrSelect.add(megre_select_list.get(i).toUpperCase());
        }
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