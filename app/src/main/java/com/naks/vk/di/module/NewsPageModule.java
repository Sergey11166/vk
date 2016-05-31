package com.naks.vk.di.module;

import com.hannesdorfmann.mosby.mvp.viewstate.lce.data.RetainingLceViewState;
import com.naks.vk.di.anotation.PerFragment;
import com.naks.vk.mvp.model.interactor.GetNewsInteractor;
import com.naks.vk.mvp.model.interactor.GetNewsInteractorImpl;
import com.naks.vk.mvp.presenter.NewsPageMosbyPresenter;
import com.naks.vk.mvp.presenter.impl.NewsPageMosbyPresenterImpl;
import com.naks.vk.ui.adapter.NewsRecyclerAdapter;
import com.naks.vk.ui.fragment.NewsPageMosbyFragment;

import java.util.Random;

import dagger.Module;
import dagger.Provides;

@Module
public class NewsPageModule {

    private NewsPageMosbyFragment fragment;

    public NewsPageModule(NewsPageMosbyFragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @PerFragment
    NewsRecyclerAdapter provideAdapter() {
        return new NewsRecyclerAdapter();
    }

    @Provides
    @PerFragment
    NewsPageMosbyPresenter providePresenter() {
        return new NewsPageMosbyPresenterImpl(fragment);
    }

    @Provides
    @PerFragment
    RetainingLceViewState provideViewState() {
        return new RetainingLceViewState<>();
    }

    @Provides
    @PerFragment
    GetNewsInteractor provideGetNewsInteractor() {
        return new GetNewsInteractorImpl(new Random());
    }
}
