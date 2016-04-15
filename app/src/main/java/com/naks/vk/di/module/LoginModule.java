package com.naks.vk.di.module;

import com.naks.vk.di.anotation.PerActivity;
import com.naks.vk.mvp.model.interactor.LoginInteractor;
import com.naks.vk.mvp.model.interactor.LoginInteractorImpl;
import com.naks.vk.mvp.presenter.LoginPresenter;
import com.naks.vk.mvp.presenter.LoginPresenterImpl;
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
    LoginActivity provideLoginActivity() {
        return activity;
    }

    @Provides
    @PerActivity
    LoginPresenter provideLoginPresenter(LoginActivity activity) {
        return new LoginPresenterImpl(activity);
    }

    @Provides
    @PerActivity
    LoginInteractor provideLoginInteractor(LoginActivity activity) {
        return new LoginInteractorImpl(activity);
    }
}
