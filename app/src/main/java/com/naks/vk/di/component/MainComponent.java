package com.naks.vk.di.component;

import com.naks.vk.di.anotation.PerActivity;
import com.naks.vk.di.module.MainModule;
import com.naks.vk.di.module.NewsPageModule;
import com.naks.vk.di.module.NewsTabModule;
import com.naks.vk.model.interactor.MainInteractorImpl;
import com.naks.vk.presenter.MainPresenterImpl;
import com.naks.vk.view.activity.MainActivity;

import dagger.Component;

@PerActivity
@Component(modules = MainModule.class, dependencies = AppComponent.class)
public interface MainComponent {
    void inject(MainActivity activity);
    void inject(MainPresenterImpl presenter);
    void inject(MainInteractorImpl interactor);

    NewsTabComponent plus(NewsTabModule module);
    NewsPageComponent plus(NewsPageModule module);
}
