package com.naks.vk.mvp.presenter;


import android.util.Log;

import com.naks.vk.R;
import com.naks.vk.db.DBHelper;
import com.naks.vk.mvp.model.interactor.MainInteractor;
import com.naks.vk.mvp.view.MainView;
import com.naks.vk.ui.activity.MainActivity;

import javax.inject.Inject;

public class MainPresenterImpl implements MainPresenter {

    private static final String TAG = "MainPresenterImpl";

    @Inject MainView view;
    @Inject DBHelper dbHelper;
    @Inject MainInteractor interactor;

    public MainPresenterImpl(MainActivity activity) {
        activity.getComponent().inject(this);
    }

    @Override
    public void onNavigationItemSelected(int itemId) {
        if (itemId == R.id.nav_news) {
            view.showNewsTabFragment();
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

    @Override
    public void onDestroy() {
        view = null;
    }
}
