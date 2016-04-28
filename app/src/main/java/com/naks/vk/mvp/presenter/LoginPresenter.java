package com.naks.vk.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.naks.vk.App;
import com.naks.vk.di.component.DaggerLoginComponent;
import com.naks.vk.di.module.LoginModule;
import com.naks.vk.mvp.model.interactor.LoginInteractor;
import com.naks.vk.mvp.view.LoginView;
import com.vk.sdk.VKScope;

import javax.inject.Inject;

@InjectViewState
public class LoginPresenter extends MvpPresenter<LoginView>
        implements LoginInteractor.OnLoginFinishedListener {

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

    @Inject
    LoginInteractor loginInteractor;

    public LoginPresenter() {
        super();
        initComponent();
    }

    private void initComponent() {
        DaggerLoginComponent.builder()
                .appComponent(App.get().getComponent())
                .loginModule(new LoginModule())
                .build()
                .inject(this);
    }

    public void wakeUpSession() {
        loginInteractor.wakeUpSession(this);
    }

    public void onUserPassedAuthorization() {
        getViewState().navigateToMainScreen();
    }

    @Override
    public void onLoggedOut() {
        getViewState().showLoginScreen(scope);
    }

    @Override
    public void onLoggedIn() {
        getViewState().navigateToMainScreen();
    }

    @Override public void onPending() {}
    @Override public void onUnknown() {}
}
