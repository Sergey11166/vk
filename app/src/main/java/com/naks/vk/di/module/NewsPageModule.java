package com.naks.vk.di.module;

import com.naks.vk.di.anotation.PerFragment;
import com.naks.vk.mvp.model.interactor.GetNewsInteractor;
import com.naks.vk.mvp.model.interactor.GetNewsInteractorImpl;
import com.naks.vk.mvp.presenter.NewsPagePresenter;
import com.naks.vk.mvp.presenter.impl.NewsPagePresenterImpl;
import com.naks.vk.mvp.view.NewsPageView;
import com.naks.vk.ui.adapter.NewsRecyclerAdapter;
import com.naks.vk.ui.fragment.NewsPageFragment;

import java.util.Random;

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
    GetNewsInteractor provideGetNewsInteractor() {
        return new GetNewsInteractorImpl(new Random());
    }
}
