package com.naks.vk.di.module;

import com.naks.vk.di.anotation.PerFragment;
import com.naks.vk.model.interactor.NewsTabInteractor;
import com.naks.vk.model.interactor.NewsTabInteractorImpl;
import com.naks.vk.presenter.NewsTabPresenter;
import com.naks.vk.presenter.NewsTabPresenterImpl;
import com.naks.vk.view.NewsTabView;
import com.naks.vk.view.fragment.NewsTabsFragment;

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
    NewsTabsFragment provideNewsTabFragment() {
        return fragment;
    }

    @Provides
    @PerFragment
    NewsTabView provideNewsTabView() {
        return fragment;
    }

    @Provides
    @PerFragment
    NewsTabPresenter provideNewsTabPresenter(NewsTabsFragment fragment) {
        return new NewsTabPresenterImpl(fragment);
    }

    @Provides
    @PerFragment
    NewsTabInteractor provideNewsTabInteractor(NewsTabsFragment fragment) {
        return new NewsTabInteractorImpl(fragment);
    }
}
