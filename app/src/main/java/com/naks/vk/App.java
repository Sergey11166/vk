package com.naks.vk;

import android.app.Application;

import com.arellomobile.mvp.MvpFacade;
import com.naks.vk.di.component.AppComponent;
import com.naks.vk.di.component.DaggerAppComponent;
import com.naks.vk.di.module.AppModule;
import com.vk.sdk.VKSdk;

public class App extends Application {

    private AppComponent component;
    private static App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        MvpFacade.init();
        instance = this;
        component = createComponent();
        component.getVKAccessTokenTracker().startTracking();
        VKSdk.initialize(this);
    }

    public AppComponent createComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule())
                .build();
    }

    public AppComponent getComponent() {
        return component;
    }

    public static App get() {
        return instance;
    }
}
