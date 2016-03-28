package com.naks.vk.model.interactor;

public interface LoginInteractor {

    interface OnLoginFinishedListener {

        void onLoggedOut();

        void onLoggedIn();

        void onPending();

        void onUnknown();
    }

    void wakeUpSession(OnLoginFinishedListener listener);
}