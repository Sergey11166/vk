package com.naks.vk.mvp.presenter;

public interface MainPresenter {

    void onNavigationItemSelected(int itemId);

    void onBackPressed(boolean isOpenedDrawer);

    void onDestroy();
}
