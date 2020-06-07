package com.sport.textdemo.adapter;

import android.content.Context;

import com.jackwang.colortracklayout.adapter.ColorTrackFragmentAdapter;
import com.sport.textdemo.fragment.MyFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

/**
 * 姓名: YaYaG
 * 时间： 2020-06-06
 * 描述：
 */
public class MyAdapter extends ColorTrackFragmentAdapter {
    private List<String> titles = new ArrayList<>();

    public MyAdapter(@NonNull FragmentManager fm, Context context) {
        super(fm, context);
        for (int i = 0; i < 10; i++) {
            titles.add("项目" +i);
        }
    }

    @NonNull
    @Override
    public List<String> getTitles() {
        return titles;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return MyFragment.newFragment("页面" + position);
    }

    @Override
    public int getCount() {
        return titles.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
