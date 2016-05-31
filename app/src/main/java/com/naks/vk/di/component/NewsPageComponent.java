package com.naks.vk.di.component;

import com.hannesdorfmann.mosby.mvp.viewstate.lce.data.RetainingLceViewState;
import com.naks.vk.di.anotation.PerFragment;
import com.naks.vk.di.module.NewsPageModule;
import com.naks.vk.mvp.presenter.NewsPageMosbyPresenter;
import com.naks.vk.mvp.presenter.impl.NewsPageMosbyPresenterImpl;
import com.naks.vk.ui.fragment.NewsPageMosbyFragment;

import dagger.Subcomponent;

@PerFragment
@Subcomponent(modules = NewsPageModule.class)
public interface NewsPageComponent {

    void inject(NewsPageMosbyFragment fragment);
    void inject(NewsPageMosbyPresenterImpl presenter);

    NewsPageMosbyPresenter getPresenter();
    RetainingLceViewState getViewState();
}