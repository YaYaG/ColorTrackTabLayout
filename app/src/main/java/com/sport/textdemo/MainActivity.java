package com.sport.textdemo;

import android.graphics.Color;
import android.os.Bundle;

import com.jackwang.colortracklayout.ColorTrackLayout;
import com.sport.textdemo.adapter.MyAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private ColorTrackLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mViewPager = findViewById(R.id.viewpager);
        mTabLayout = findViewById(R.id.tab_layout);

        MyAdapter adapter = new MyAdapter(getSupportFragmentManager(), this);
        mViewPager.setAdapter(adapter);

        mTabLayout.setNormalColorAndSelectedColor(Color.BLACK, Color.GREEN);
        mTabLayout.setTabLayoutTextSize(18);

        mTabLayout.setupWithViewPager(mViewPager, adapter);
    }

}
