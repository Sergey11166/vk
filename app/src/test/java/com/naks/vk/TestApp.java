package com.naks.vk;

import com.naks.vk.di.component.AppComponent;
import com.naks.vk.di.module.MockAppModule;

public class TestApp extends App {

    @Override
    public AppComponent createComponent() {
        return DaggerMockAppComponent.builder().mockAppModule(new MockAppModule(this)).build();
    }
}
