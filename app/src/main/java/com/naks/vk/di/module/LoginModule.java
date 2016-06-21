package com.naks.vk.di.module;

import com.naks.vk.di.anotation.PerActivity;
import com.naks.vk.mvp.model.interactor.LoginInteractor;
import com.naks.vk.mvp.model.interactor.impl.LoginInteractorImpl;
import com.naks.vk.mvp.presenter.LoginPresenter;
import com.naks.vk.mvp.presenter.impl.LoginPresenterImpl;
import com.naks.vk.mvp.view.LoginView;
import com.naks.vk.ui.activity.LoginActivityDagger;

import dagger.Module;
import dagger.Provides;

@Module
public class LoginModule {

    private LoginActivityDagger activity;

    public LoginModule(LoginActivityDagger activity) {
        this.activity = activity;
    }

    @Provides
    @PerActivity
    LoginView provideLoginView() {
        return activity;
    }

    @Provides
    @PerActivity
    LoginPresenter provideLoginPresenter() {
        return new LoginPresenterImpl(activity);
    }

    @Provides
    @PerActivity
    LoginInteractor provideLoginInteractor() {
        return new LoginInteractorImpl(activity);
    }
}