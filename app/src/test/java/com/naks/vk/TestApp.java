package com.naks.vk;

import com.naks.vk.di.component.DaggerMockAppComponent;
import com.naks.vk.di.component.MockAppComponent;
import com.naks.vk.di.module.MockAppModule;

public class TestApp {

    MockAppComponent component;

    public TestApp() {
        component = createComponent();
    }

    public MockAppComponent createComponent() {
        return DaggerMockAppComponent.builder()
                .mockAppModule(new MockAppModule(this))
                .build();
    }

    public MockAppComponent getComponent() {
        return component;
    }
}
