package com.plinkdt.jk.main.personal;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * MyPagerAdapter
 * Created by Tse on 2020/11/10.
 */
public class MatterPagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> fragments = new ArrayList<>();

    public MatterPagerAdapter(FragmentManager fm) {
        super(fm);
        fragments.add(MatterFragment.newInstance(0));
        fragments.add(MatterFragment.newInstance(1));
        fragments.add(MatterFragment.newInstance(2));
    }

    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
