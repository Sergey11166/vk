package com.naks.vk.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.naks.vk.di.component.AppComponent;
import com.naks.vk.di.component.DaggerLoginComponent;
import com.naks.vk.di.component.HasComponent;
import com.naks.vk.di.component.LoginComponent;
import com.naks.vk.di.module.LoginModule;
import com.naks.vk.mvp.presenter.LoginPresenter;
import com.naks.vk.mvp.view.LoginView;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKError;

import javax.inject.Inject;

public class LoginActivityDagger extends DaggerBaseActivity<LoginComponent>
        implements LoginView, HasComponent<LoginComponent> {

    @Inject LoginPresenter presenter;

    @Override
    protected void setupComponent(AppComponent appComponent) {
        component = DaggerLoginComponent.builder()
                .appComponent(appComponent)
                .loginModule(new LoginModule(this))
                .build();
        component.inject(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.wakeUpSession();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        VKCallback<VKAccessToken> callback = new VKCallback<VKAccessToken>() {
            @Override
            public void onResult(VKAccessToken res) {
                // User passed Authorization
                presenter.onUserPassedAuthorization();
            }

            @Override
            public void onError(VKError error) {
                // User didn't pass Authorization
                finish();
            }
        };

        if (!VKSdk.onActivityResult(requestCode, resultCode, data, callback)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void showLoginScreen(String[] scope) {
        VKSdk.login(this, scope);
    }

    @Override
    public void navigateToMainScreen() {
        startActivity(new Intent(this, MainActivityDagger.class));
        finish();
    }
}
