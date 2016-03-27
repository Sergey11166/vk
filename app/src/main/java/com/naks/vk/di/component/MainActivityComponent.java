package com.naks.vk.di.component;

import com.naks.vk.di.anotation.PerActivity;
import com.naks.vk.di.module.MainModule;
import com.naks.vk.presenter.MainPresenter;
import com.naks.vk.view.activity.MainActivity;

import dagger.Component;

@PerActivity
@Component(modules = MainModule.class, dependencies = AppComponent.class)
public interface MainActivityComponent {
    void inject(MainActivity mainActivity);
    MainPresenter getMainPresenter();
}
