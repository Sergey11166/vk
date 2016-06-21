package com.naks.vk.mvp.presenter.impl;

import com.naks.vk.mvp.model.interactor.LoginInteractor;
import com.naks.vk.mvp.presenter.LoginPresenter;
import com.naks.vk.mvp.view.LoginView;
import com.naks.vk.ui.activity.LoginActivityDagger;
import com.vk.sdk.VKScope;

import javax.inject.Inject;

public class LoginPresenterImpl implements LoginPresenter, LoginInteractor.OnLoginFinishedListener {

    private static final String[] scope = new String[]{
            VKScope.NOTIFICATIONS,
            VKScope.MESSAGES,
            VKScope.FRIENDS,
            VKScope.OFFLINE,
            VKScope.PHOTOS,
            VKScope.STATUS,
            VKScope.GROUPS,
            VKScope.EMAIL,
            VKScope.NOTES,
            VKScope.PAGES,
            VKScope.WALL,
            VKScope.DOCS,
    };

    @Inject LoginView view;
    @Inject LoginInteractor loginInteractor;

    public LoginPresenterImpl(LoginActivityDagger activity) {
        activity.getComponent().inject(this);
    }

    public void wakeUpSession() {
        loginInteractor.wakeUpSession(this);
    }

    public void onUserPassedAuthorization() {
        view.navigateToMainScreen();
    }

    @Override
    public void onLoggedOut() {
        view.showLoginScreen(scope);
    }

    @Override
    public void onLoggedIn() {
        view.navigateToMainScreen();
    }

    @Override public void onPending() {}
    @Override public void onUnknown() {}
}
