package com.example.duoihinhbatchu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.duoihinhbatchu.adapter.DapAnAdapter;

import java.util.ArrayList;

public class PlayActivity extends AppCompatActivity {
    private String resuft_ask = "có công mài sắt có ngày nên kim";
    private ArrayList<String> arrAnwser;
    private GridView gdvAnwser;
    private ArrayList<String> arrSelect;
    private GridView gdvSelect;
    ServiceGame serviceGame = new ServiceGame();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        init();
        createData();
        setOnClick();
        showAnwser();
        showSelect();
        Toast.makeText(getApplicationContext(), "main", Toast.LENGTH_SHORT).show();
    }
    private void init(){
        arrAnwser = new ArrayList<>();
        arrSelect = new ArrayList<>();
        gdvAnwser = findViewById(R.id.gdvAnwser);
        gdvSelect = findViewById(R.id.gdvSelect);
    }


    private void createData(){
//        megre resuft_ask
        ArrayList<String> megre_select_list = serviceGame.megreData(resuft_ask);
        int n = megre_select_list.size();
//        push data anwser and select
        for (int i = 0; i < n; i++) {
            arrAnwser.add("");
            arrSelect.add(megre_select_list.get(i).toUpperCase());
        }
    }

    private void setOnClick(){
        gdvAnwser.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String text = (String) adapterView.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(), text + " " + String.valueOf(position), Toast.LENGTH_SHORT).show();
                int index_empty = serviceGame.findIndexEmpty(arrSelect);
                if(text.length()!=0 && index_empty >= 0){
                    arrAnwser.set(position, "");
                    arrSelect.set(index_empty, text);
                    showAnwser();
                    showSelect();
                }
            }
        });

        gdvSelect.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String text = (String) adapterView.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(), text + " " + String.valueOf(position), Toast.LENGTH_SHORT).show();
                int index_empty = serviceGame.findIndexEmpty(arrAnwser);
                if(text.length()!=0 && index_empty >= 0){
                    arrAnwser.set(index_empty, text);
                    arrSelect.set(position, "");
                    showAnwser();
                    showSelect();
                }
            }
        });
        Toast.makeText(getApplicationContext(), "event", Toast.LENGTH_SHORT).show();
    }



    private void showAnwser(){
        gdvAnwser.setNumColumns(4);
        gdvAnwser.setAdapter(new DapAnAdapter(this, 0, arrAnwser));
    }

    private void showSelect(){
        gdvSelect.setNumColumns(4);
        gdvSelect.setAdapter(new DapAnAdapter(this, 0, arrSelect));
    }
}