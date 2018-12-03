package com.example.week1.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class MyFragmentAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment>mfragments;
    private ArrayList<String>mTitle;

    public MyFragmentAdapter(FragmentManager fm, ArrayList<Fragment> mfragments, ArrayList<String> mTitle) {
        super(fm);
        this.mfragments = mfragments;
        this.mTitle = mTitle;
    }

    @Override
    public Fragment getItem(int i) {
        return mfragments.get(i);
    }

    @Override
    public int getCount() {
        return mfragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitle.get(position);
    }
}
