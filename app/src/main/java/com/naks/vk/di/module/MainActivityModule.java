package com.naks.vk.di.module;

import com.naks.vk.view.activity.BaseActivity;

import dagger.Module;

@Module(includes = BusModule.class)
public class MainActivityModule {

    private BaseActivity activity;

    public MainActivityModule(BaseActivity activity) {
        this.activity = activity;
    }
}
