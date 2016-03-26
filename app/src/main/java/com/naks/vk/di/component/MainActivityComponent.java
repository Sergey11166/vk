package com.naks.vk.di.component;

import com.naks.vk.di.anotation.PerActivity;
import com.naks.vk.di.module.BusModule;
import com.naks.vk.di.module.MainActivityModule;
import com.naks.vk.view.activity.MainActivity;

import dagger.Component;

@PerActivity
@Component(modules = MainActivityModule.class, dependencies = AppComponent.class)
public interface MainActivityComponent extends
        NewsTabFragmentComponent.HasNewsTabFragmentDepends,
        NewsFragment0Component.HasNewsFragment0Depends {
    void inject(MainActivity mainActivity);
}
