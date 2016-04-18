package com.naks.vk.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.naks.vk.mvp.model.interactor.LoginInteractor;
import com.naks.vk.mvp.view.LoginView;

import javax.inject.Inject;

@InjectViewState
public class LoginPresenterImpl extends MvpPresenter<LoginView>
        implements LoginPresenter, LoginInteractor.OnLoginFinishedListener {

    @Inject LoginInteractor loginInteractor;

    public LoginPresenterImpl() {
        super();
    }

    @Override
    public void wakeUpSession() {
        loginInteractor.wakeUpSession(this);
    }

    @Override
    public void onUserPassedAuthorization() {
        getViewState().navigateToMainScreen();
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onLoggedOut() {
        getViewState().showLoginScreen();
    }

    @Override
    public void onLoggedIn() {
        getViewState().navigateToMainScreen();
    }

    @Override public void onPending() {}
    @Override public void onUnknown() {}
}
