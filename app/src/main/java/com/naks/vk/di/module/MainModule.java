package com.naks.vk.di.module;

import com.naks.vk.di.anotation.PerActivity;
import com.naks.vk.presenter.MainPresenter;
import com.naks.vk.presenter.MainPresenterImpl;
import com.naks.vk.view.MainView;
import com.naks.vk.view.activity.MainActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {

    private MainActivity mainActivity;

    public MainModule(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Provides
    @PerActivity
    public MainActivity provideMainActivity() {
        return mainActivity;
    }

    @Provides
    @PerActivity
    public MainView provideMainView() {
        return mainActivity;
    }

    @Provides
    @PerActivity
    public MainPresenter provideMainPresenter (MainView mainView) {
        return new MainPresenterImpl(mainView);
    }
}
