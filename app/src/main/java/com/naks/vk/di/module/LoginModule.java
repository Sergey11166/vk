package com.naks.vk.di.module;

import android.content.Context;

import com.naks.vk.di.anotation.PerActivity;
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
    @PerActivity
    public LoginView provideLoginView() {
        return loginView;
    }

    @Provides
    @PerActivity
    public LoginInteractor provideLoginInteractor(Context context) {
        return new LoginInteractorImpl(context);
    }

    @Provides
    @PerActivity
    public LoginPresenter provideLoginPresenter(LoginView loginView, LoginInteractor loginInteractor) {
        return new LoginPresenterImpl(loginView, loginInteractor);
    }
}
