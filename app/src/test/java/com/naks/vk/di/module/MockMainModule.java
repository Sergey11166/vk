package com.naks.vk.di.module;

import com.naks.vk.di.anotation.PerActivity;
import com.naks.vk.mvp.presenter.impl.MainPresenterImpl;
import com.naks.vk.mvp.view.MainView;

import org.mockito.Mockito;

import dagger.Module;
import dagger.Provides;

@Module
public class MockMainModule {

    @Provides
    @PerActivity
    MainView provideView() {
        return Mockito.mock(MainView.class);
    }

    @Provides
    @PerActivity
    MainPresenterImpl providePresenter(MainView view) {
        return new MainPresenterImpl(view);
    }
}
