package com.naks.vk.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
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
import com.naks.vk.mvp.presenter.NewsTabPresenter;
import com.naks.vk.mvp.view.NewsTabView;
import com.naks.vk.ui.adapter.NewsTabAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.naks.vk.mvp.model.interactor.GetNewsInteractor.TypeNews;

public class NewsTabsFragment extends BaseFragment implements NewsTabView {

    public static final String TAG = "NewsTabsFragment";

    @InjectPresenter NewsTabPresenter presenter;

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.view_pager) ViewPager viewPager;
    @BindView(R.id.tabs) TabLayout tabLayout;

    private Unbinder unbinder;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_tabs_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        DrawerLayout drawerLayout = (DrawerLayout) activity.findViewById(R.id.drawer_layout);
        setupDrawer(activity, drawerLayout);
        setupViewPager();
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void setupDrawer(Activity activity, DrawerLayout drawerLayout) {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(activity, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void setupViewPager() {
        NewsTabAdapter adapter = new NewsTabAdapter(getChildFragmentManager());
        adapter.addFragment(NewsPageFragment.newInstance(), TypeNews.NEWS, "news");
        adapter.addFragment(NewsPageFragment.newInstance(), TypeNews.RECOMMENDATIONS, "recommendation");
        adapter.addFragment(NewsPageFragment.newInstance(), TypeNews.FRIENDS, "friends");
        adapter.addFragment(NewsPageFragment.newInstance(), TypeNews.COMMUNITIES, "communities");
        viewPager.setAdapter(adapter);
    }

    @OnClick(R.id.fab)
    @SuppressWarnings("unused")
    void onFABClick(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
        presenter.onFABClick();
    }

    @Override
    public void navigateToNewPostActivity() {

    }
}
