package com.naks.vk.presenter;

import android.content.Context;

import com.naks.vk.model.interactor.LoginInteractor;
import com.naks.vk.model.interactor.LoginInteractorImpl;
import com.naks.vk.ui.view.LoginView;

public class LoginPresenterImpl implements LoginPresenter {

    private LoginView mLoginView;
    private LoginInteractor mLoginInteractor;

    public LoginPresenterImpl(LoginView loginView) {
        mLoginView = loginView;
        mLoginInteractor = new LoginInteractorImpl();
    }

    @Override
    public void wakeUpSession(Context context) {
        mLoginInteractor.wakeUpSession(context, new OnFinishedListener());
    }

    @Override
    public void onUserPassedAuthorization() {
        mLoginView.navigateToMainScreen();
    }

    @Override
    public void onDestroy() {
        mLoginView = null;
    }

    private class OnFinishedListener implements LoginInteractor.OnLoginFinishedListener {

        @Override
        public void onLoggedOut() {
            mLoginView.showLoginScreen();
        }

        @Override
        public void onLoggedIn() {
            mLoginView.navigateToMainScreen();
        }

        @Override public void onPending() {}
        @Override public void onUnknown() {}
    }
}
