package com.naks.vk.presenter;

import com.naks.vk.model.interactor.LoginInteractor;
import com.naks.vk.view.LoginView;

public class LoginPresenterImpl implements LoginPresenter, LoginInteractor.OnLoginFinishedListener {

    private LoginView loginView;
    private LoginInteractor loginInteractor;

    public LoginPresenterImpl(LoginView loginView, LoginInteractor loginInteractor) {
        this.loginView = loginView;
        this.loginInteractor = loginInteractor;
    }

    @Override
    public void wakeUpSession() {
        loginInteractor.wakeUpSession(this);
    }

    @Override
    public void onUserPassedAuthorization() {
        loginView.navigateToMainScreen();
    }

    @Override
    public void onDestroy() {
        loginView = null;
    }

    @Override
    public void onLoggedOut() {
        loginView.showLoginScreen();
    }

    @Override
    public void onLoggedIn() {
        loginView.navigateToMainScreen();
    }

    @Override public void onPending() {}
    @Override public void onUnknown() {}
}
