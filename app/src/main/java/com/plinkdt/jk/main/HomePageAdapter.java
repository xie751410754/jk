package com.plinkdt.jk.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.plinkdt.jk.R;
import com.plinkdt.jk.main.adressbook.AddressBookFragment;
import com.plinkdt.jk.main.applicationcenter.ApplicationCenterFragment;
import com.plinkdt.jk.main.personal.PersonalFragment;

import java.util.ArrayList;
import java.util.List;

public class HomePageAdapter extends FragmentPagerAdapter {

    private final List<Fragment> fragments = new ArrayList<>();
    private final String[] titles = new String[]{
            "通讯录",
            "我的",
            "应用中心"
    };
    private final int[] norIcons = new int[]{
            R.drawable.adressbook,
            R.drawable.personal_select,
            R.drawable.applicationcenter,
    };
    private final int[] seleIcons = new int[]{
            R.drawable.adressbook_selecter,
            R.drawable.personal,
            R.drawable.applicationcenter_selecter,
    };

    HomePageAdapter(FragmentManager fm) {
        super(fm);
        fragments.add(new AddressBookFragment());
        fragments.add(new PersonalFragment());
        fragments.add(new ApplicationCenterFragment());
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    String[] getTitles() {
        return titles;
    }

    int[] getNorIcons() {
        return norIcons;
    }

    int[] getSeleIcons() {
        return seleIcons;
    }

    public AddressBookFragment getAddressBookFragment() {
        return (AddressBookFragment) fragments.get(0);
    }
}
