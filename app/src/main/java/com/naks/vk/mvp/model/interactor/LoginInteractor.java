package com.naks.vk.mvp.model.interactor;

public interface LoginInteractor {

    interface OnLoginFinishedListener {

        void onLoggedOut();

        void onLoggedIn();

        void onPending();

        void onUnknown();
    }

    void wakeUpSession(OnLoginFinishedListener listener);
}
