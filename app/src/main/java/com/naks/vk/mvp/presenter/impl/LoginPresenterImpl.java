package com.naks.vk.mvp.presenter.impl;

import com.naks.vk.mvp.model.interactor.LoginInteractor;
import com.naks.vk.mvp.model.interactor.OnLoginFinishedListener;
import com.naks.vk.mvp.presenter.LoginPresenter;
import com.naks.vk.mvp.view.LoginView;
import com.vk.sdk.VKScope;

public class LoginPresenterImpl implements LoginPresenter, OnLoginFinishedListener {

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

    private LoginView view;
    private LoginInteractor interactor;

    public LoginPresenterImpl(LoginView view, LoginInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void wakeUpSession() {
        interactor.wakeUpSession(this);
    }

    @Override
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
}
