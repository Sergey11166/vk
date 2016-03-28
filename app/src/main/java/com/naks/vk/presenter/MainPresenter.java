package com.naks.vk.presenter;

public interface MainPresenter {

    void onNavigationItemSelected(int itemId);

    void onBackPressed(boolean isOpenedDrawer);

    void onDestroy();
}
