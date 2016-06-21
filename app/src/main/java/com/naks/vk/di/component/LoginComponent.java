package com.naks.vk.di.component;

import com.naks.vk.di.anotation.PerActivity;
import com.naks.vk.di.module.LoginModule;
import com.naks.vk.mvp.presenter.impl.LoginPresenterImpl;
import com.naks.vk.ui.activity.LoginActivityDagger;

import dagger.Component;

@PerActivity
@Component(modules = LoginModule.class, dependencies = AppComponent.class)
public interface LoginComponent {
    void inject(LoginActivityDagger activity);
    void inject(LoginPresenterImpl presenter);
}
