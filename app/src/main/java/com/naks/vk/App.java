package com.naks.vk;

import android.app.Application;

import com.naks.vk.di.component.AppComponent;
import com.naks.vk.di.component.DaggerAppComponent;
import com.naks.vk.di.module.AppModule;
import com.vk.sdk.VKAccessTokenTracker;
import com.vk.sdk.VKSdk;

import javax.inject.Inject;

public class App extends Application {

    private AppComponent component;

    @Inject VKAccessTokenTracker vkAccessTokenTracker;

    @Override
    public void onCreate() {
        super.onCreate();
        this.component = createComponent();
        component.inject(this);
        vkAccessTokenTracker.startTracking();
        VKSdk.initialize(this);
    }

    public AppComponent createComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getComponent() {
        return component;
    }
}
