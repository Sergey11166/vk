package com.naks.vk.di.component;

import com.naks.vk.di.anotation.PerFragment;
import com.naks.vk.di.module.NewsPageModule;
import com.naks.vk.model.interactor.GetNewsInteractorImpl;
import com.naks.vk.presenter.NewsPagePresenterImpl;
import com.naks.vk.view.fragment.NewsPageFragment;

import dagger.Subcomponent;

@PerFragment
@Subcomponent(modules = NewsPageModule.class)
public interface NewsPageComponent {
    void inject(NewsPageFragment fragment);
    void inject(NewsPagePresenterImpl presenter);
    void inject(GetNewsInteractorImpl interactor);
}
