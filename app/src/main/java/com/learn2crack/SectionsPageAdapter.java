package com.learn2crack;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by timur on 10-Jul-17.
 */

public class SectionsPageAdapter extends FragmentPagerAdapter{

    private final List<Fragment> fragmentList = new ArrayList<Fragment>();
    private final List<String> fragmentTitleList = new ArrayList<String>();

    public void addFragment(Fragment fragment, String title){
        fragmentList.add(fragment);
        fragmentTitleList.add(title);
    }

    public SectionsPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentTitleList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
