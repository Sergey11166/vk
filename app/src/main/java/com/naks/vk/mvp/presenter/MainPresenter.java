package com.naks.vk.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.naks.vk.R;
import com.naks.vk.mvp.view.MainView;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    public void onNavigationItemSelected(int itemId) {
        if (itemId == R.id.nav_news) {
            getViewState().showNewsTabFragment();
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
        getViewState().closeDrawer();
    }

    public void onBackPressed(boolean isOpenedDrawer) {
        if (isOpenedDrawer) {
            getViewState().closeDrawer();
        } else {
            getViewState().pressBack();
        }
    }
}
