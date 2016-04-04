package com.naks.vk.di.module;

import com.naks.vk.di.anotation.PerFragment;
import com.naks.vk.model.interactor.NewsPageInteractor;
import com.naks.vk.model.interactor.NewsPageInteractorImpl;
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
    NewsPageInteractor provideInteractor() {
        return new NewsPageInteractorImpl(fragment);
    }
}
