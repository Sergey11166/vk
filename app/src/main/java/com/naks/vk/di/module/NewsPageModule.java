package com.naks.vk.di.module;

import com.naks.vk.di.anotation.PerPresenter;
import com.naks.vk.mvp.model.interactor.GetNewsInteractor;
import com.naks.vk.mvp.model.interactor.GetNewsInteractorImpl;

import java.util.Random;

import dagger.Module;
import dagger.Provides;

@Module
public class NewsPageModule {

    @Provides
    @PerPresenter
    GetNewsInteractor provideInteractor() {
        return new GetNewsInteractorImpl(new Random());
    }
}
