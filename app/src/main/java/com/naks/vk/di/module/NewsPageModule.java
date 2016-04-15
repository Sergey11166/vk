package com.naks.vk.di.module;

import com.naks.vk.di.anotation.PerFragment;
import com.naks.vk.mvp.model.interactor.GetNewsInteractor;
import com.naks.vk.mvp.model.interactor.GetNewsInteractorImpl;
import com.naks.vk.ui.adapter.NewsRecyclerAdapter;
import com.naks.vk.ui.fragment.NewsPageFragment;

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
    GetNewsInteractor provideInteractor() {
        return new GetNewsInteractorImpl(fragment);
    }
}
