package com.naks.vk.di.component;

import com.naks.vk.TestApp;
import com.naks.vk.di.module.MockAppModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = MockAppModule.class)
public interface MockAppComponent {
    TestApp testApp();
}
