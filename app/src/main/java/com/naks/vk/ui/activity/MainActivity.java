package com.naks.vk.ui.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;

import com.naks.vk.App;
import com.naks.vk.R;
import com.naks.vk.di.component.DaggerMainComponent;
import com.naks.vk.di.component.MainComponent;
import com.naks.vk.di.module.MainModule;
import com.naks.vk.mvp.presenter.MainPresenter;
import com.naks.vk.mvp.view.MainView;
import com.naks.vk.ui.fragment.NewsTabsFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends DaggerBaseActivity<MainComponent> implements
        NavigationView.OnNavigationItemSelectedListener, MainView {

    @Inject MainPresenter presenter;

    @BindView(R.id.naw_view) NavigationView navigationView;
    @BindView(R.id.drawer_layout) DrawerLayout drawerLayout;

    @Override
    protected void setupComponent() {
        component = DaggerMainComponent.builder()
                .appComponent(App.get(this).getComponent())
                .mainModule(new MainModule(this))
                .build();
        component.inject(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setupNavigationView();
    }

    private void setupNavigationView() {
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_news);
        onNavigationItemSelected(navigationView.getMenu().getItem(0));
    }

    @Override
    public void onBackPressed() {
        presenter.onBackPressed(drawerLayout.isDrawerOpen(GravityCompat.START));
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        presenter.onNavigationItemSelected(id);
        return true;
    }

    @Override
    public void showNewsTabFragment() {
        NewsTabsFragment newsTabsFragment;
        FragmentManager fm = getSupportFragmentManager();
        newsTabsFragment = (NewsTabsFragment) fm.findFragmentByTag(NewsTabsFragment.TAG);
        if (newsTabsFragment == null) newsTabsFragment = new NewsTabsFragment();
        fm.beginTransaction()
                .replace(R.id.main_container, newsTabsFragment, NewsTabsFragment.TAG)
                .commit();
    }

    @Override
    public void closeDrawer() {
        drawerLayout.closeDrawer(GravityCompat.START);
    }

    @Override
    public void pressBack() {
        super.onBackPressed();
    }
}
