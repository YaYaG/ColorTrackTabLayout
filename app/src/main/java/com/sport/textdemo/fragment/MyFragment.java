package com.sport.textdemo.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * 姓名: Jack
 * 时间： 2020-06-06
 * 描述：
 */
public class MyFragment extends Fragment {
    private static final String NAME = "name";

    public static Fragment newFragment(String name){
        Fragment fragment = new MyFragment();

        Bundle bundle = new Bundle();
        bundle.putString(NAME,name);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView textView = new TextView(getContext());
        textView.setText(getArguments().getString(NAME));
        return textView;
    }
}
