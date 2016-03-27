package com.naks.vk.di.module;

import android.app.Application;

import com.naks.vk.model.interactor.LoginInteractor;
import com.naks.vk.model.interactor.LoginInteractorImpl;
import com.naks.vk.presenter.LoginPresenter;
import com.naks.vk.presenter.LoginPresenterImpl;
import com.naks.vk.view.LoginView;

import dagger.Module;
import dagger.Provides;

@Module
public class LoginModule {

    private LoginView loginView;

    public LoginModule(LoginView loginView) {
        this.loginView = loginView;
    }

    @Provides
    public LoginView provideLoginView() {
        return loginView;
    }

    @Provides
    public LoginInteractor provideLoginInteractor(Application application) {
        return new LoginInteractorImpl(application);
    }

    @Provides
    public LoginPresenter provideLoginPresenter(LoginView loginView, LoginInteractor loginInteractor) {
        return new LoginPresenterImpl(loginView, loginInteractor);
    }
}
