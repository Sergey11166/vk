package com.naks.vk.presenter;

import android.content.Context;

public interface LoginPresenter {

    void wakeUpSession(Context context);

    void onUserPassedAuthorization();

    void onDestroy();
}
