package com.naks.vk.mvp.model.interactor;

public interface OnLoginFinishedListener {

    void onLoggedOut();

    void onLoggedIn();

    void onPending();

    void onUnknown();
}