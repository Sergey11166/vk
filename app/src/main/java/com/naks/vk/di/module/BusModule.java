package com.naks.vk.di.module;

import com.naks.vk.di.anotation.PerActivity;
import com.squareup.otto.Bus;

import dagger.Module;
import dagger.Provides;

@Module
public class BusModule {
    @Provides
    @PerActivity
    public Bus provideBus() {
        return new Bus("default");
    }
}
