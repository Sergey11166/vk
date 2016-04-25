package com.naks.vk.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.naks.vk.App;
import com.naks.vk.di.component.DaggerLoginComponent;
import com.naks.vk.di.component.LoginComponent;
import com.naks.vk.di.module.LoginModule;
import com.naks.vk.mvp.model.interactor.LoginInteractor;
import com.naks.vk.mvp.view.LoginView;

import javax.inject.Inject;

@InjectViewState
public class LoginPresenter extends MvpPresenter<LoginView>
        implements LoginInteractor.OnLoginFinishedListener {

    private LoginComponent component;

    @Inject LoginInteractor loginInteractor;

    public LoginPresenter() {
        super();
        component = createComponent();
        component.inject(this);
    }

    private LoginComponent createComponent() {
        return DaggerLoginComponent.builder()
                .appComponent(App.get().getComponent())
                .loginModule(new LoginModule())
                .build();
    }

    public void wakeUpSession() {
        loginInteractor.wakeUpSession(this);
    }

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
