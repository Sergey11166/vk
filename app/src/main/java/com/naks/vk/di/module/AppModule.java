package com.naks.vk.di.module;

import android.content.Context;
import android.content.Intent;

import com.naks.vk.ui.activity.LoginActivityDagger;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKAccessTokenTracker;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private final Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    VKAccessTokenTracker vkAccessTokenTracker() {
        return new VKAccessTokenTracker() {
            @Override
            public void onVKAccessTokenChanged(VKAccessToken oldToken, VKAccessToken newToken) {
                if (newToken != null) return;
                Intent intent = new Intent(context, LoginActivityDagger.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent);
            }
        };
    }
}