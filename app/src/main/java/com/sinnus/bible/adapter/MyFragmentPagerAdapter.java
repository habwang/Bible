package com.sinnus.bible.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import com.sinnus.bible.bean.Bible;
import com.sinnus.bible.fragment.MainFragment;


/**
 * Created by sinnus on 2015/10/21.
 */
public class MyFragmentPagerAdapter extends FragmentStatePagerAdapter {
    public MainFragment currentFragment;

    public MyFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        System.out.println("position" + position);
        Fragment fragment = MainFragment.newInstance(position);
        return fragment;

    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        System.out.println("instantiate" + position);
        return super.instantiateItem(container, position);
    }

    @Override
    public int getCount() {
        return Bible.ALL_CHAPTER_NUM;
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        currentFragment = (MainFragment) object;
//        currentFragment.loadMode();
        super.setPrimaryItem(container, position, object);
    }
}
