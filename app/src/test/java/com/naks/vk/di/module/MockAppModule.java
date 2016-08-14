package com.naks.vk.di.module;

import com.naks.vk.TestApp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class MockAppModule {

    TestApp testApp;

    public MockAppModule(TestApp testApp) {
        this.testApp = testApp;
    }

    @Provides
    @Singleton
    TestApp provideTestApp() {
        return testApp;
    }

}
