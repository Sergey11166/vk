package com.naks.vk.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.naks.vk.R;
import com.naks.vk.mvp.model.interactor.GetNewsInteractor;
import com.naks.vk.mvp.presenter.NewsTabPresenter;
import com.naks.vk.mvp.view.NewsTabView;

import java.util.ArrayList;
import java.util.List;

import static com.naks.vk.mvp.model.interactor.GetNewsInteractor.TypeNews;

public class NewsTabsFragment extends BaseFragment implements NewsTabView {

    public static final String TAG = "NewsTabsFragment";

    @InjectPresenter
    NewsTabPresenter presenter;

    public NewsTabsFragment(){}

    public static NewsTabsFragment newInstance() {
        return new NewsTabsFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.news_tabs_fragment, container, false);

        Toolbar toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) activity.findViewById(R.id.drawer_layout);
        setupDrawer(activity, drawer, toolbar);

        ViewPager viewPager = (ViewPager) rootView.findViewById(R.id.view_pager);
        setupViewPager(viewPager);

        TabLayout tabLayout = (TabLayout) rootView.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                presenter.onFABClick();
            }
        });

        return rootView;
    }

    private void setupDrawer(Activity activity, DrawerLayout drawer, Toolbar toolbar) {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(activity, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getChildFragmentManager());
        adapter.addFragment(NewsPageFragment.newInstance(), TypeNews.NEWS, "news");
        adapter.addFragment(NewsPageFragment.newInstance(), TypeNews.RECOMMENDATIONS, "recommendation");
        adapter.addFragment(NewsPageFragment.newInstance(), TypeNews.FRIENDS, "friends");
        adapter.addFragment(NewsPageFragment.newInstance(), TypeNews.COMMUNITIES, "communities");
        viewPager.setAdapter(adapter);
    }

    @Override
    public void navigateToNewPostActivity() {

    }

    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public Adapter(FragmentManager fragmentManager) {
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
}
