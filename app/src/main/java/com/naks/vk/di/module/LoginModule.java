package com.naks.vk.di.module;

import android.content.Context;

import com.naks.vk.di.anotation.PerActivity;
import com.naks.vk.mvp.model.interactor.LoginInteractor;
import com.naks.vk.mvp.model.interactor.impl.LoginInteractorImpl;
import com.naks.vk.mvp.presenter.LoginPresenter;
import com.naks.vk.mvp.presenter.impl.LoginPresenterImpl;
import com.naks.vk.mvp.view.LoginView;
import com.naks.vk.ui.activity.LoginActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class LoginModule {

    private LoginActivity activity;

    public LoginModule(LoginActivity activity) {
        this.activity = activity;
    }

    @Provides
    @PerActivity
    LoginView provideLoginView() {
        return activity;
    }

    @Provides
    @PerActivity
    LoginPresenter provideLoginPresenter(LoginView view, LoginInteractor interactor) {
        return new LoginPresenterImpl(view, interactor);
    }

    @Provides
    @PerActivity
    LoginInteractor provideLoginInteractor(Context context) {
        return new LoginInteractorImpl(context);
    }
}