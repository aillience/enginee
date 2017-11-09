package com.yfl.library.base.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Happy every day.
 * Created by yfl on 2017/9/20 0020
 * explain: viewPager的适配器，继承FragmentPagerAdapter
 */
@SuppressWarnings("unused")
public abstract class BaseFragmentAdapter extends FragmentPagerAdapter implements ViewPager.OnPageChangeListener{

    protected abstract List<Fragment>  getFragmentList();
    public BaseFragmentAdapter(FragmentManager fm) {
        super(fm);
        getFragmentList();
    }
    @Override
    public Fragment getItem(int position) {
        return getFragmentList().get(position);
    }

    @Override
    public int getCount() {
        return getFragmentList().size();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    protected void clear(){
        if(getFragmentList()!=null)
            getFragmentList().clear();
    }
}
