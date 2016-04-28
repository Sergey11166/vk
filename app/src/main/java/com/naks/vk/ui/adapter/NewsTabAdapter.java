package com.naks.vk.ui.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.naks.vk.mvp.model.interactor.GetNewsInteractor;
import com.naks.vk.ui.fragment.NewsPageFragment;

import java.util.ArrayList;
import java.util.List;

public class NewsTabAdapter extends FragmentPagerAdapter {

    private final List<Fragment> mFragments = new ArrayList<>();
    private final List<String> mFragmentTitles = new ArrayList<>();

    public NewsTabAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    public void addFragment(Fragment fragment, GetNewsInteractor.TypeNews type, String title) {
        Bundle args = new Bundle();
        args.putString(NewsPageFragment.KEY_NEWS_TYPE, type.name());
        fragment.setArguments(args);
        mFragments.add(fragment);
        mFragmentTitles.add(title);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitles.get(position);
    }
}
