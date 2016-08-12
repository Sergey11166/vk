package com.naks.vk;

import android.app.Application;
import android.content.Context;

import com.naks.vk.di.component.AppComponent;
import com.naks.vk.di.component.DaggerAppComponent;
import com.naks.vk.di.module.AppModule;
import com.vk.sdk.VKSdk;

public class App extends Application {

    AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = createComponent();
        component.getVKAccessTokenTracker().startTracking();
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

    public static App get(Context context) {
        return (App) context.getApplicationContext();
    }
}
