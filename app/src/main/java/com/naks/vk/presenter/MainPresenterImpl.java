package com.naks.vk.presenter;


import com.naks.vk.R;
import com.naks.vk.view.MainView;

public class MainPresenterImpl implements MainPresenter {

    private MainView view;

    public MainPresenterImpl(MainView view) {
        this.view = view;
    }

    @Override
    public void onNavigationItemSelected(int itemId) {
        if (itemId == R.id.nav_news) {
            view.showNewsTabFragment();
        } else if (itemId == R.id.nav_feedback) {

        } else if (itemId == R.id.nav_messages) {

        } else if (itemId == R.id.nav_friends) {

        } else if (itemId == R.id.nav_birthdays) {

        } else if (itemId == R.id.nav_communities) {

        } else if (itemId == R.id.nav_photos) {

        } else if (itemId == R.id.nav_bookmarks) {

        } else if (itemId == R.id.nav_search) {

        } else if (itemId == R.id.nav_setting) {

        }
        view.closeDrawer();
    }

    @Override
    public void onBackPressed(boolean isOpenedDrawer) {
        if (isOpenedDrawer) {
            view.closeDrawer();
        } else {
            view.pressBack();
        }
    }
}
