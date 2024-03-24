package com.demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private Button prevBtn, nextBtn;
    private FragmentManager fragmentManager;
    private FragmentAdapter fragmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.initView();
        this.catchEvents();
    }

    private void catchEvents() {
        this.tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        tabLayout.setTabTextColors(Color.BLACK, getResources().getColor(R.color.pink));
                        prevBtn.setBackgroundColor(getResources().getColor(R.color.pink));
                        nextBtn.setBackgroundColor(getResources().getColor(R.color.pink));
                        break;
                    case 1:
                        tabLayout.setTabTextColors(Color.BLACK, getResources().getColor(R.color.green));
                        prevBtn.setBackgroundColor(getResources().getColor(R.color.green));
                        nextBtn.setBackgroundColor(getResources().getColor(R.color.green));
                        break;
                    case 2:
                        tabLayout.setTabTextColors(Color.BLACK, getResources().getColor(R.color.blue));
                        prevBtn.setBackgroundColor(getResources().getColor(R.color.blue));
                        nextBtn.setBackgroundColor(getResources().getColor(R.color.blue));
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        this.prevBtn.setOnClickListener(this);
        this.nextBtn.setOnClickListener(this);
    }

    private void initView() {
        this.viewPager = findViewById(R.id.viewPager);
        this.tabLayout = findViewById(R.id.tabLayout);
        this.prevBtn = findViewById(R.id.prevBtn);
        this.nextBtn = findViewById(R.id.nextBtn);

        this.fragmentManager = getSupportFragmentManager();
        this.fragmentAdapter = new FragmentAdapter(this.fragmentManager, 3);

        this.viewPager.setPageTransformer(true, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {

            }
        });
        this.viewPager.setAdapter(this.fragmentAdapter);
        this.tabLayout.setupWithViewPager(this.viewPager);

        this.setTabLayoutTitleColor();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.nextBtn:
                if (this.viewPager.getCurrentItem() == 2) {
                    return;
                } else {
                    this.viewPager.setCurrentItem(this.viewPager.getCurrentItem() + 1);
                    this.setTabLayoutTitleColor();
                }

                break;

            case R.id.prevBtn:
                if (this.viewPager.getCurrentItem() == 0) {
                    return;
                } else {
                    this.viewPager.setCurrentItem(this.viewPager.getCurrentItem() - 1);
                    this.setTabLayoutTitleColor();
                }

                break;
        }
    }

    private void setTabLayoutTitleColor() {
        switch (this.viewPager.getCurrentItem()) {
            case 0:
                this.tabLayout.setTabTextColors(Color.BLACK, getResources().getColor(R.color.pink));
                break;
            case 1:
                this.tabLayout.setTabTextColors(Color.BLACK, getResources().getColor(R.color.green));
                break;
            case 2:
                this.tabLayout.setTabTextColors(Color.BLACK, getResources().getColor(R.color.blue));
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (this.viewPager.getCurrentItem() == 0) {
            super.onBackPressed();
        } else {
            this.viewPager.setCurrentItem(this.viewPager.getCurrentItem() - 1);
        }
    }
}