package com.naks.vk.presenter;

public interface LoginPresenter {

    void wakeUpSession();

    void onUserPassedAuthorization();

    void onDestroy();
}
