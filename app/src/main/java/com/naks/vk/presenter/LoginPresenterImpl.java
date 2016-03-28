package com.naks.vk.presenter;

import com.naks.vk.model.interactor.LoginInteractor;
import com.naks.vk.view.LoginView;
import com.naks.vk.view.activity.LoginActivity;

import javax.inject.Inject;

public class LoginPresenterImpl implements LoginPresenter, LoginInteractor.OnLoginFinishedListener {

    private static final String TAG = "LoginPresenterImpl";

    @Inject LoginView loginView;
    @Inject LoginInteractor loginInteractor;

    public LoginPresenterImpl(LoginActivity activity) {
        activity.getComponent().inject(this);
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
