package com.jackwang.colortracklayout;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;

import com.google.android.material.tabs.TabLayout;
import com.jackwang.colortracklayout.adapter.ColorTrackFragmentAdapter;
import com.jackwang.colortracklayout.view.ClipTextView;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

/**
 * 姓名: YaYaG
 * 时间： 2020-06-07
 * 描述：
 */
public class ColorTrackLayout extends TabLayout implements TablayoutSetState {
    private ViewPager mViewPager;
    private ColorTrackFragmentAdapter mColorTrackFragmentAdapter;
    private int mNormalColor = Color.BLACK, mSelectedColor = Color.BLUE;
    private int mTablayoutTextSize = 14;

    public ColorTrackLayout(@NonNull Context context) {
        super(context);
    }

    public ColorTrackLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ColorTrackLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init() {
        //设置自定义tab
        for (int i = 0; i < getTabCount(); i++) {
            TabLayout.Tab tab = getTab(i);
            if (tab != null) {
                View tabView = mColorTrackFragmentAdapter.getTabView(mColorTrackFragmentAdapter.getTitles().get(i));

                ClipTextView clipTextView = tabView.findViewById(R.id.clipTextView);
                clipTextView.setChangeColor(mSelectedColor);
                clipTextView.setOriginColor(mNormalColor);
                clipTextView.setTextSize(mTablayoutTextSize);
                tab.setCustomView(tabView);
            }
        }

        View view = getTab(0).getCustomView();
        ClipTextView clipTextView = view.findViewById(R.id.clipTextView);
        clipTextView.setCurrentProgress(1);
    }


    @Override
    public void setupWithViewPager(@Nullable ViewPager viewPager) {
        if (mColorTrackFragmentAdapter == null) {
            throw new IllegalArgumentException("请调用方法：setupWithViewPager(@Nullable ViewPager viewPager, @NonNull ColorTrackFragmentAdapter colorTrackAdapter)，不然不会有效果哦");
        }
        super.setupWithViewPager(viewPager);
    }

    @Override
    public void setNormalColorAndSelectedColor(@ColorInt int normalColor, @ColorInt int selectedColor) {
        mNormalColor = normalColor;
        mSelectedColor = selectedColor;
    }

    @Override
    public void setupWithViewPager(@Nullable ViewPager viewPager, @NonNull ColorTrackFragmentAdapter colorTrackFragmentAdapter) {
        this.mViewPager = viewPager;
        this.mColorTrackFragmentAdapter = colorTrackFragmentAdapter;
        setSelectedTabIndicatorHeight(0);
        super.setupWithViewPager(viewPager);
        init();
        addListener();
    }

    @Override
    public void setTabLayoutTextSize(int textSize) {
        mTablayoutTextSize = textSize;
    }

    private void addListener() {
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(this) {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                progress(position, positionOffset, ClipTextView.Direction.RIGHT_TO_LEFT);
                if (positionOffset < 1 && position < getTabCount() - 1) {
                    progress(position + 1, positionOffset, ClipTextView.Direction.LEFT_TO_RIGHT);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
                if (state == ViewPager.SCROLL_STATE_IDLE) {
                    for (int i = 0; i < getTabCount(); i++) {
                        if (i != mViewPager.getCurrentItem()) {
                            ClipTextView textView = getTab(i).getCustomView().findViewById(R.id.clipTextView);
                            textView.setDirection(ClipTextView.Direction.LEFT_TO_RIGHT);
                            textView.setCurrentProgress(0);
                        }
                    }
                }
            }
        });
    }


    private void progress(int position, float positionOffset, ClipTextView.Direction direction) {
        ClipTextView clipTextView =
                getTabAt(position).getCustomView().findViewById(R.id.clipTextView);
        clipTextView.setDirection(direction);
        clipTextView.setCurrentProgress(positionOffset);
    }


    private TabLayout.Tab getTab(int position) {
        return getTabAt(position);
    }
}
