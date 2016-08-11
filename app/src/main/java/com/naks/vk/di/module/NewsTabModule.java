package com.naks.vk.di.module;

import com.naks.vk.di.anotation.PerFragment;
import com.naks.vk.mvp.presenter.NewsTabPresenter;
import com.naks.vk.mvp.presenter.impl.NewsTabPresenterImpl;
import com.naks.vk.mvp.view.NewsTabView;
import com.naks.vk.ui.fragment.NewsTabsFragment;

import dagger.Module;
import dagger.Provides;

@Module
public class NewsTabModule {

    private NewsTabsFragment fragment;

    public NewsTabModule(NewsTabsFragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @PerFragment
    NewsTabView provideView() {
        return fragment;
    }

    @Provides
    @PerFragment
    NewsTabPresenter providePresenter(NewsTabView view) {
        return new NewsTabPresenterImpl(view);
    }
}
