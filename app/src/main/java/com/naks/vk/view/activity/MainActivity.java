package com.naks.vk.view.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;

import com.naks.vk.R;
import com.naks.vk.di.component.AppComponent;
import com.naks.vk.di.component.DaggerMainComponent;
import com.naks.vk.di.module.MainModule;
import com.naks.vk.presenter.MainPresenter;
import com.naks.vk.view.MainView;
import com.naks.vk.view.fragment.NewsTabsFragment;

import javax.inject.Inject;

public class MainActivity extends BaseActivity implements
        NavigationView.OnNavigationItemSelectedListener,
        MainView {

    @Inject MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupNavigationView((NavigationView) findViewById(R.id.naw_view));
    }

    @Override
    protected void setupComponent(AppComponent appComponent) {
        DaggerMainComponent.builder()
                .appComponent(appComponent)
                .mainModule(new MainModule(this))
                .build()
                .inject(this);
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

    private void setupNavigationView(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_news);
        onNavigationItemSelected(navigationView.getMenu().getItem(0));
    }

    @Override
    public void showNewsTabFragment() {
        NewsTabsFragment newsTabsFragment = NewsTabsFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_container, newsTabsFragment)
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
}
