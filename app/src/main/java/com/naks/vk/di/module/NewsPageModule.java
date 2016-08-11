package com.naks.vk.di.module;

import com.naks.vk.di.anotation.PerFragment;
import com.naks.vk.mvp.model.interactor.GetNewsInteractor;
import com.naks.vk.mvp.model.interactor.TypeNews;
import com.naks.vk.mvp.model.interactor.impl.GetNewsInteractorImpl;
import com.naks.vk.mvp.presenter.NewsPagePresenter;
import com.naks.vk.mvp.presenter.impl.NewsPagePresenterImpl;
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
    NewsPagePresenter providePresenter(GetNewsInteractor interactor) {
        String key = fragment.getArguments().getString(NewsPageFragment.KEY_NEWS_TYPE);
        assert key != null;
        return new NewsPagePresenterImpl(interactor, TypeNews.valueOf(TypeNews.class, key));
    }

    @Provides
    @PerFragment
    GetNewsInteractor provideGetNewsInteractor() {
        return new GetNewsInteractorImpl();
    }
}
