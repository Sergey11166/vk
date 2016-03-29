package com.naks.vk.di.component;

import com.naks.vk.di.anotation.PerFragment;
import com.naks.vk.di.module.NewsTabModule;
import com.naks.vk.model.interactor.NewsTabInteractorImpl;
import com.naks.vk.presenter.NewsTabPresenterImpl;
import com.naks.vk.view.fragment.NewsTabsFragment;

import dagger.Subcomponent;

@PerFragment
@Subcomponent(modules = NewsTabModule.class)
public interface NewsTabComponent {
    void inject(NewsTabsFragment fragment);
    void inject(NewsTabPresenterImpl presenter);
    void inject(NewsTabInteractorImpl interactor);
}
