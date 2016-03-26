package com.naks.vk.di.module;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.naks.vk.App;
import com.naks.vk.di.anotation.PerApplication;
import com.naks.vk.view.activity.LoginActivity;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKAccessTokenTracker;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private final App app;

    public AppModule(App app) {
        this.app = app;
    }

    @Provides
    @PerApplication
    Context provideContext() {
        return app.getApplicationContext();
    }

    @Provides
    @PerApplication
    VKAccessTokenTracker vkAccessTokenTracker() {
        return new VKAccessTokenTracker() {
            @Override
            public void onVKAccessTokenChanged(@Nullable VKAccessToken oldToken,
                                               @Nullable VKAccessToken newToken) {
                if (newToken == null) {
                    Toast.makeText(app, "AccessToken invalidated", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(app, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    app.startActivity(intent);
                }
            }
        };
    }
}