package com.naks.vk.mvp.presenter;

public interface LoginPresenter extends MvpPresenter {

    void wakeUpSession();

    void onUserPassedAuthorization();
}
