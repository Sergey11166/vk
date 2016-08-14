package com.naks.vk.di.module;

import com.naks.vk.di.anotation.PerFragment;
import com.naks.vk.mvp.presenter.impl.NewsTabPresenterImpl;
import com.naks.vk.mvp.view.NewsTabView;

import org.mockito.Mockito;

import dagger.Module;
import dagger.Provides;

@Module
public class MockNewsTabModule {

    @Provides
    @PerFragment
    NewsTabView provideView() {
        return Mockito.mock(NewsTabView.class);
    }

    @Provides
    @PerFragment
    NewsTabPresenterImpl providePresenter(NewsTabView view) {
        return new NewsTabPresenterImpl(view);
    }
}
