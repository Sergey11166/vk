package com.naks.vk.mvp.presenter;

public interface MainPresenter extends MvpPresenter {

    void onNavigationItemSelected(int itemId);

    void onBackPressed(boolean isOpenedDrawer);
}
