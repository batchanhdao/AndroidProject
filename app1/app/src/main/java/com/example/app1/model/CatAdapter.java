package com.example.app1.model;

import android.content.Context;

import java.util.List;

public class CatAdapter {
    private List<Cat> cats;
    private Context context;

    public CatAdapter(List<Cat> cats, Context context) {
        this.cats = cats;
        this.context = context;
    }

}
