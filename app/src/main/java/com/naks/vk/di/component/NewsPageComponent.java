package com.naks.vk.di.component;

import com.naks.vk.di.anotation.PerPresenter;
import com.naks.vk.di.module.NewsPageModule;
import com.naks.vk.mvp.presenter.NewsPagePresenter;

import dagger.Component;

@PerPresenter
@Component(modules = NewsPageModule.class, dependencies = AppComponent.class)
public interface NewsPageComponent {
    void inject(NewsPagePresenter presenter);
}
