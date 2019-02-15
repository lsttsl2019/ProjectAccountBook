package com.isttis2019.projectaccountbook;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;



public class MyAdapter extends FragmentPagerAdapter {
    Fragment[] fragments =new Fragment[4];
    String[] titles=new String[]{"지출", "수입", "달력", "그래프"};

    public MyAdapter(FragmentManager fm) {
        super(fm);
        fragments[0]=new Page1Fragment();
        fragments[1]=new Page2Fragment();
        fragments[2]=new Page3Fragment();
        fragments[3]=new Page4Fragment();

    }

    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }

    @Override
    public int getCount() {
        return fragments.length;
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
