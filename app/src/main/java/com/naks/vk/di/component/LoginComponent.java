package com.naks.vk.di.component;

import com.naks.vk.di.anotation.PerPresenter;
import com.naks.vk.di.module.LoginModule;
import com.naks.vk.mvp.presenter.LoginPresenter;

import dagger.Component;

@PerPresenter
@Component(modules = LoginModule.class, dependencies = AppComponent.class)
public interface LoginComponent {
    void inject(LoginPresenter presenter);
}
