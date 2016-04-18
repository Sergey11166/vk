package com.naks.vk.di.module;

import android.content.Context;

import com.naks.vk.di.anotation.PerPresenter;
import com.naks.vk.mvp.model.interactor.LoginInteractor;
import com.naks.vk.mvp.model.interactor.LoginInteractorImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class LoginModule {

    @Provides
    @PerPresenter
    LoginInteractor provideLoginInteractor(Context context) {
        return new LoginInteractorImpl(context);
    }
}
