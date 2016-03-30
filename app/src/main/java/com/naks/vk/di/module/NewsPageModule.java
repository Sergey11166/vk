package com.naks.vk.di.module;

import com.naks.vk.di.anotation.PerFragment;
import com.naks.vk.model.interactor.NewsPageInteractor;
import com.naks.vk.model.interactor.NewsPageInteractorImpl;
import com.naks.vk.presenter.NewsPagePresenter;
import com.naks.vk.presenter.NewsPagePresenterImpl;
import com.naks.vk.view.NewsPageView;
import com.naks.vk.view.adapter.NewsRecyclerAdapter;
import com.naks.vk.view.fragment.NewsPageFragment;

import dagger.Module;
import dagger.Provides;

@Module
public class NewsPageModule {

    private NewsPageFragment fragment;

    public NewsPageModule(NewsPageFragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @PerFragment
    NewsRecyclerAdapter provideAdapter() {
        return new NewsRecyclerAdapter();
    }

    @Provides
    @PerFragment
    NewsPageView provideView() {
        return fragment;
    }

    @Provides
    @PerFragment
    NewsPagePresenter providePresenter() {
        return new NewsPagePresenterImpl(fragment);
    }

    @Provides
    @PerFragment
    NewsPageInteractor provideInteractor() {
        return new NewsPageInteractorImpl(fragment);
    }
}
