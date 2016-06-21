package com.naks.vk.di.module;

import com.naks.vk.di.anotation.PerActivity;
import com.naks.vk.mvp.presenter.MainPresenter;
import com.naks.vk.mvp.presenter.impl.MainPresenterImpl;
import com.naks.vk.mvp.view.MainView;
import com.naks.vk.ui.activity.MainActivityDagger;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {

    private MainActivityDagger activity;

    public MainModule(MainActivityDagger activity) {
        this.activity = activity;
    }

    @Provides
    @PerActivity
    public MainView provideMainView() {
        return activity;
    }

    @Provides
    @PerActivity
    public MainPresenter provideMainPresenter() {
        return new MainPresenterImpl(activity);
    }
}
