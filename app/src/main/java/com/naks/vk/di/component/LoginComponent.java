package com.naks.vk.di.component;

import com.naks.vk.di.anotation.PerActivity;
import com.naks.vk.di.module.LoginModule;
import com.naks.vk.presenter.LoginPresenter;
import com.naks.vk.view.activity.LoginActivity;

import dagger.Component;

@PerActivity
@Component(modules = LoginModule.class, dependencies = AppComponent.class)
public interface LoginComponent {
    void inject(LoginActivity loginActivity);
    LoginPresenter getLoginPresenter();
}
