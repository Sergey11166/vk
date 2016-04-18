package com.naks.vk.mvp.presenter;


import android.util.Log;

import com.arellomobile.mvp.MvpPresenter;
import com.naks.vk.R;
import com.naks.vk.db.DBHelper;
import com.naks.vk.mvp.model.interactor.MainInteractor;
import com.naks.vk.mvp.view.MainView;

import javax.inject.Inject;

public class MainPresenterImpl extends MvpPresenter<MainView> implements MainPresenter {

    private static final String TAG = "MainPresenterImpl";

    @Inject DBHelper dbHelper;
    @Inject MainInteractor interactor;

    public MainPresenterImpl() {
        super();
    }

    @Override
    public void onNavigationItemSelected(int itemId) {
        if (itemId == R.id.nav_news) {
            getViewState().showNewsTabFragment();
        } else if (itemId == R.id.nav_feedback) {
            Log.d(TAG, dbHelper.toString());
        } else if (itemId == R.id.nav_messages) {
            String resultTestAction = interactor.testAction();
            Log.d(TAG, resultTestAction);
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

    @Override
    public void onBackPressed(boolean isOpenedDrawer) {
        if (isOpenedDrawer) {
            getViewState().closeDrawer();
        } else {
            getViewState().pressBack();
        }
    }

    @Override
    public void onDestroy() {

    }
}
