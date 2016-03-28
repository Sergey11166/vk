package com.naks.vk.di.component;

import android.content.Context;

import com.naks.vk.db.DBHelper;
import com.naks.vk.di.module.AppModule;
import com.vk.sdk.VKAccessTokenTracker;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    DBHelper getDBHelper();
    Context getContext();
    VKAccessTokenTracker getVKAccessTokenTracker();
}