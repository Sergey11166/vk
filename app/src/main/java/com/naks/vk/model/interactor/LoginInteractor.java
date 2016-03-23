package com.naks.vk.model.interactor;

import android.content.Context;

public interface LoginInteractor {

    interface OnLoginFinishedListener {

        void onLoggedOut();

        void onLoggedIn();

        void onPending();

        void onUnknown();
    }

    void wakeUpSession(Context context, OnLoginFinishedListener listener);
}
