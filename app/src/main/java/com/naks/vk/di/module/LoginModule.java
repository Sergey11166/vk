package com.naks.vk.di.module;

import com.naks.vk.di.anotation.PerActivity;
import com.naks.vk.model.interactor.LoginInteractor;
import com.naks.vk.model.interactor.LoginInteractorImpl;
import com.naks.vk.presenter.LoginPresenter;
import com.naks.vk.presenter.LoginPresenterImpl;
import com.naks.vk.view.LoginView;
import com.naks.vk.view.activity.LoginActivity;

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
