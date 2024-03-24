package com.track_management;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.track_management.adapter.ViewPagerAdapter;

public class MainActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private BottomNavigationView bottomNavigationView;
    private ViewPager viewPager;
    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.initView();
        this.catchEvent();
    }

    private void initView() {
        this.tabLayout = findViewById(R.id.tabLayoutId);
        this.bottomNavigationView = findViewById(R.id.bottomNavigationViewId);
        this.viewPager = findViewById(R.id.viewPageId);
        this.floatingActionButton = findViewById(R.id.floatingActionButtonId);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.viewPager.setAdapter(adapter);

        this.tabLayout.setupWithViewPager(viewPager);
        this.tabLayout.getTabAt(0).setIcon(R.drawable.ic_today);
        this.tabLayout.getTabAt(1).setIcon(R.drawable.ic_history);
        this.tabLayout.getTabAt(2).setIcon(R.drawable.ic_statistic);
    }

    private void catchEvent() {
        this.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

        this.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        bottomNavigationView.getMenu().findItem(R.id.mHome).setChecked(true);
                        break;

                    case 1:
                        bottomNavigationView.getMenu().findItem(R.id.mHistory).setChecked(true);
                        break;

                    case 2:
                        bottomNavigationView.getMenu().findItem(R.id.mSearch).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        this.bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.mHome:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.mHistory:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.mSearch:
                        viewPager.setCurrentItem(2);
                        break;
                }

                return true;
            }
        });
    }
}