package com.naks.vk;

import android.app.Application;

import com.naks.vk.di.component.DaggerMockAppComponent;
import com.naks.vk.di.component.MockAppComponent;
import com.naks.vk.di.module.MockAppModule;

public class TestApp extends Application {

    MockAppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = createComponent();
    }

    public MockAppComponent createComponent() {
        return DaggerMockAppComponent.builder()
                .mockAppModule(new MockAppModule())
                .build();
    }

    public MockAppComponent getComponent() {
        return component;
    }
}
