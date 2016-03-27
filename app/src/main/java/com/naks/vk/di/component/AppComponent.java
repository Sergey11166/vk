package com.naks.vk.di.component;

import android.app.Application;

import com.naks.vk.App;
import com.naks.vk.di.module.AppModule;
import com.vk.sdk.VKAccessTokenTracker;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    void inject(App app);
    Application getApplication();
    VKAccessTokenTracker getVKAccessTokenTracker();
    //VKApi vkApi();
    //Gson gson();
}