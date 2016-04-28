package com.naks.vk.di.module;

import android.content.Context;
import android.content.Intent;

import com.naks.vk.App;
import com.naks.vk.ui.activity.LoginActivity;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKAccessTokenTracker;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private final Context context;

    public AppModule() {
        this.context = App.get();
    }

    @Provides
    @Singleton
    VKAccessTokenTracker vkAccessTokenTracker() {
        return new VKAccessTokenTracker() {
            @Override
            public void onVKAccessTokenChanged(VKAccessToken oldToken, VKAccessToken newToken) {
                if (newToken != null) return;
                Intent intent = new Intent(context, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent);
            }
        };
    }
}