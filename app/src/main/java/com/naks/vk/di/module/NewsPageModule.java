package com.naks.vk.di.module;

import com.hannesdorfmann.mosby.mvp.viewstate.lce.data.RetainingLceViewState;
import com.naks.vk.di.anotation.PerFragment;
import com.naks.vk.mvp.model.interactor.GetNewsInteractor;
import com.naks.vk.mvp.model.interactor.impl.GetNewsInteractorImpl;
import com.naks.vk.mvp.presenter.NewsPagePresenter;
import com.naks.vk.mvp.presenter.impl.NewsPagePresenterImpl;
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
    NewsPagePresenter providePresenter() {
        return new NewsPagePresenterImpl(fragment);
    }

    @Provides
    @PerFragment
    RetainingLceViewState provideViewState() {
        return new RetainingLceViewState<>();
    }

    @Provides
    @PerFragment
    GetNewsInteractor provideGetNewsInteractor() {
        return new GetNewsInteractorImpl();
    }
}
