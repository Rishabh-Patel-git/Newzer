package com.example.newzer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class NewsActivity extends AppCompatActivity {
Toolbar mToolbar;
ViewPager2 mViewPager2;
TabLayout mTablayout;
ViewPagerAdapter mAdapter;
    String title[] = new String[]{"Home","Sports","Health","Science","Technology","Entertainment"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_main);
        mToolbar = findViewById(R.id.toolbar_view);
        mViewPager2 = findViewById(R.id.viewpager);
        mTablayout = findViewById(R.id.tab_view);
        mAdapter = new ViewPagerAdapter(getSupportFragmentManager(),getLifecycle());
        mViewPager2.setAdapter(mAdapter);
        new TabLayoutMediator(mTablayout,mViewPager2,(((tab, position) -> tab.setText(title[position])))).attach();
    }
}