package com.jackwang.colortracklayout.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.jackwang.colortracklayout.R;
import com.jackwang.colortracklayout.view.ClipTextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * 姓名: YaYaG
 * 时间： 2020-06-07
 * 描述：
 */
public abstract class ColorTrackFragmentAdapter extends FragmentPagerAdapter implements ColorTracker{
    protected Context mContext;

    public ColorTrackFragmentAdapter(@NonNull FragmentManager fm, Context context) {
        super(fm);
        this.mContext = context;
    }

    public View getTabView(@NonNull String title) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.clip_view, null);
        ClipTextView clipTextView = view.findViewById(R.id.clipTextView);
        clipTextView.setText(title);
        return view;
    }
}
