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

import com.naks.vk.R;
import com.naks.vk.di.component.HasComponent;
import com.naks.vk.di.component.MainComponent;
import com.naks.vk.di.component.NewsTabComponent;
import com.naks.vk.di.module.NewsTabModule;
import com.naks.vk.mvp.presenter.NewsTabPresenter;
import com.naks.vk.mvp.view.NewsTabView;
import com.naks.vk.ui.adapter.NewsTabAdapter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.naks.vk.mvp.model.interactor.GetNewsInteractor.TypeNews;

public class NewsTabsFragment extends BaseFixFragment
        implements NewsTabView, HasComponent<NewsTabComponent> {

    public static final String TAG = "NewsTabsFragment";

    private NewsTabComponent component;

    @Inject NewsTabPresenter presenter;

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.view_pager) ViewPager viewPager;
    @BindView(R.id.tabs) TabLayout tabLayout;

    private Unbinder unbinder;

    @Override
    protected void setupComponent(MainComponent component) {
        this.component = component.plus(new NewsTabModule(this));
        this.component.inject(this);
    }

    @Override
    public NewsTabComponent getComponent() {
        return component;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_tabs_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        viewPager.setOffscreenPageLimit(3);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        DrawerLayout drawerLayout = (DrawerLayout) activity.findViewById(R.id.drawer_layout);
        setupDrawer(activity, drawerLayout);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupViewPager();
        tabLayout.setupWithViewPager(viewPager);
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
        NewsTabAdapter adapter = new NewsTabAdapter(childFragmentManager());
        adapter.addFragment(new NewsPageMosbyFragment(), TypeNews.NEWS, "news");
        adapter.addFragment(new NewsPageMosbyFragment(), TypeNews.RECOMMENDATIONS, "recommendation");
        adapter.addFragment(new NewsPageMosbyFragment(), TypeNews.FRIENDS, "friends");
        adapter.addFragment(new NewsPageMosbyFragment(), TypeNews.COMMUNITIES, "communities");
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
