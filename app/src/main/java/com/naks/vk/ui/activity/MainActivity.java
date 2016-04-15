package com.naks.vk.ui.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.MenuItem;

import com.naks.vk.R;
import com.naks.vk.db.DBHelper;
import com.naks.vk.di.HasComponent;
import com.naks.vk.di.component.AppComponent;
import com.naks.vk.di.component.DaggerMainComponent;
import com.naks.vk.di.component.MainComponent;
import com.naks.vk.di.module.MainModule;
import com.naks.vk.mvp.presenter.MainPresenter;
import com.naks.vk.mvp.view.MainView;
import com.naks.vk.ui.fragment.NewsTabsFragment;

import javax.inject.Inject;

public class MainActivity extends BaseActivity implements
        NavigationView.OnNavigationItemSelectedListener,
        HasComponent<MainComponent>,
        MainView {

    private static final String TAG = "MainActivity";

    private MainComponent component;

    @Inject MainPresenter presenter;
    @Inject DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupNavigationView((NavigationView) findViewById(R.id.naw_view));
    }

    private void setupNavigationView(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_news);
        onNavigationItemSelected(navigationView.getMenu().getItem(0));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        assert drawer != null;
        presenter.onBackPressed(drawer.isDrawerOpen(GravityCompat.START));
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        presenter.onNavigationItemSelected(id);
        return true;
    }

    @Override
    public void showNewsTabFragment() {
        Log.d(TAG, dbHelper.toString());
        NewsTabsFragment newsTabsFragment;
        FragmentManager fm = getSupportFragmentManager();
        newsTabsFragment = (NewsTabsFragment) fm.findFragmentByTag(NewsTabsFragment.TAG);
        if (newsTabsFragment == null) newsTabsFragment = NewsTabsFragment.newInstance();
        fm.beginTransaction()
                .replace(R.id.main_container, newsTabsFragment, NewsTabsFragment.TAG)
                .commit();
    }

    @Override
    public void closeDrawer() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        assert drawer != null;
        drawer.closeDrawer(GravityCompat.START);
    }

    @Override
    public void pressBack() {
        super.onBackPressed();
    }

    @Override
    protected void setupComponent(AppComponent appComponent) {
        component = DaggerMainComponent.builder()
                .appComponent(appComponent)
                .mainModule(new MainModule(this))
                .build();
        component.inject(this);
    }

    @Override
    public MainComponent getComponent() {
        return component;
    }
}
