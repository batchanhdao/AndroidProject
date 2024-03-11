package com.example.recycle_view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.recycle_view.model.Cat;
import com.example.recycle_view.model.CatAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CatAdapter.CatItemListener {
    private RecyclerView recyclerView;
    private CatAdapter catAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.initView();
    }

    private void initView() {
        this.recyclerView = findViewById(R.id.rview);
        this.catAdapter = new CatAdapter(this.getCats());
        this.catAdapter.setCatItemListener(this);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        this.recyclerView.setLayoutManager(gridLayoutManager);
        this.recyclerView.setAdapter(this.catAdapter);
    }

    private List<Cat> getCats() {
        List<Cat> cats = new ArrayList<>();

        cats.add(new Cat(R.drawable.cat_1, "Meo 1"));
        cats.add(new Cat(R.drawable.cat_2, "Meo 2"));
        cats.add(new Cat(R.drawable.cat_3, "Meo 3"));
        cats.add(new Cat(R.drawable.cat_4, "Meo 4"));

        return cats;
    }

    @Override
    public void onClickItem(View view, int position) {
        Cat cat = this.getCats().get(position);
        Toast.makeText(this, cat.getName(), Toast.LENGTH_SHORT).show();
    }
}