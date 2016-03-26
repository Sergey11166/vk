package com.naks.vk.di.component;

import android.content.Context;

import com.naks.vk.App;
import com.naks.vk.di.anotation.PerApplication;
import com.naks.vk.di.module.AppModule;
import com.vk.sdk.VKAccessTokenTracker;

import dagger.Component;

@PerApplication
@Component(modules = AppModule.class)
public interface AppComponent {
    void inject(App app);
    Context context();
    VKAccessTokenTracker vkAccessTokenTracker();
    //VKApi vkApi();
    //Gson gson();
}