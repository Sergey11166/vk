package com.naks.vk.ui.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.naks.vk.R;
import com.naks.vk.ui.fragment.NewsTabsFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NavigationView navigationView = (NavigationView) findViewById(R.id.naw_view);
        setupNavigationView(navigationView);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        assert drawer != null;
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_news) {
            NewsTabsFragment newsTabsFragment = NewsTabsFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_container, newsTabsFragment)
                    .commit();

        } else if (id == R.id.nav_feedback) {

        } else if (id == R.id.nav_messages) {

        } else if (id == R.id.nav_friends) {

        } else if (id == R.id.nav_birthdays) {

        } else if (id == R.id.nav_communities) {

        } else if (id == R.id.nav_photos) {

        } else if (id == R.id.nav_bookmarks) {

        } else if (id == R.id.nav_search) {

        } else if (id == R.id.nav_setting) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        assert drawer != null;
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setupNavigationView(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_news);
        onNavigationItemSelected(navigationView.getMenu().getItem(0));
    }
}
