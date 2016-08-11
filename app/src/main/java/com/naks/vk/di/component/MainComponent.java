package com.naks.vk.di.component;

import com.naks.vk.di.anotation.PerActivity;
import com.naks.vk.di.module.MainModule;
import com.naks.vk.di.module.NewsPageModule;
import com.naks.vk.di.module.NewsTabModule;
import com.naks.vk.ui.activity.MainActivity;

import dagger.Component;

@PerActivity
@Component(modules = MainModule.class, dependencies = AppComponent.class)
public interface MainComponent {

    void inject(MainActivity activity);

    NewsTabComponent plus(NewsTabModule module);
    NewsPageComponent plus(NewsPageModule module);
}
