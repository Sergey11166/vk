package com.naks.vk.di.module;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.naks.vk.db.DBHelper;
import com.naks.vk.ui.activity.LoginActivity;
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
    Context provideContext() {
        return context;
    }

    @Provides
    @Singleton
    DBHelper provideDBHelper(Context context) {
        return new DBHelper(context);
    }


    @Provides
    @Singleton
    VKAccessTokenTracker vkAccessTokenTracker() {
        return new VKAccessTokenTracker() {
            @Override
            public void onVKAccessTokenChanged(@Nullable VKAccessToken oldToken,
                                               @Nullable VKAccessToken newToken) {
                if (newToken == null) {
                    Toast.makeText(context, "AccessToken invalidated", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(context, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    context.startActivity(intent);
                }
            }
        };
    }
}