package com.naks.vk.di.module;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.naks.vk.App;
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

    public AppModule() {
        this.context = App.get();
    }

    @Provides
    @Singleton
    DBHelper provideDBHelper() {
        return new DBHelper(this.context);
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