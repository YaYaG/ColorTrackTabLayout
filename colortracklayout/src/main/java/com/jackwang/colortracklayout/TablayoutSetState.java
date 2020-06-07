package com.jackwang.colortracklayout;

import com.jackwang.colortracklayout.adapter.ColorTrackFragmentAdapter;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

/**
 * 姓名: YaYaG
 * 时间： 2020-06-07
 * 描述：
 */
public interface TablayoutSetState {

    void setNormalColorAndSelectedColor(@ColorInt int normalColor, @ColorInt int selectedColor);

    void setupWithViewPager(@Nullable ViewPager viewPager, @NonNull ColorTrackFragmentAdapter colorTrackFragmentAdapter);

    void setTabLayoutTextSize(int textSize);
}
