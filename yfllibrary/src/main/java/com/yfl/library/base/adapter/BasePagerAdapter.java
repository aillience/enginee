package com.yfl.library.base.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * viewPager的简单适配器
 * create by yinfanglang
 */
public class BasePagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragmentList;
    private List<String> list_Title;

    public BasePagerAdapter(FragmentManager fm, List<Fragment> fragmentList, List<String> list_Title) {
        super(fm);
        this.fragmentList = fragmentList;
        this.list_Title = list_Title;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return (list_Title == null) ? 0:list_Title.size();
    }

    /**
     * //此方法用来显示tab上的名字
     *
     * @param position 坐标
     * @return 返回
     */
    @Override
    public CharSequence getPageTitle(int position) {
        return list_Title.get(position);
    }
}
