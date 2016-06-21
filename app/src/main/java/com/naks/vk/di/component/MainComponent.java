package com.naks.vk.di.component;

import com.naks.vk.di.anotation.PerActivity;
import com.naks.vk.di.module.MainModule;
import com.naks.vk.di.module.NewsPageModule;
import com.naks.vk.di.module.NewsTabModule;
import com.naks.vk.mvp.presenter.impl.MainPresenterImpl;
import com.naks.vk.ui.activity.MainActivityDagger;

import dagger.Component;

@PerActivity
@Component(modules = MainModule.class, dependencies = AppComponent.class)
public interface MainComponent {

    void inject(MainActivityDagger activity);
    void inject(MainPresenterImpl presenter);

    NewsTabComponent plus(NewsTabModule module);
    NewsPageComponent plus(NewsPageModule module);
}
