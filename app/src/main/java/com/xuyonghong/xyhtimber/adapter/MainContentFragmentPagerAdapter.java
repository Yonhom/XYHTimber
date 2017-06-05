package com.xuyonghong.xyhtimber.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.xuyonghong.xyhtimber.fragment.SongFragment;

/**
 * Created by xuyonghong on 2017/6/2.
 */

public class MainContentFragmentPagerAdapter extends FragmentPagerAdapter {
    public MainContentFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        SongFragment songFragment = new SongFragment();
        return songFragment;
    }


    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "Song";
    }
}
