package com.naks.vk.di.component;

import com.naks.vk.di.anotation.PerActivity;
import com.naks.vk.di.module.MockLoginModule;
import com.naks.vk.mvp.presenter.impl.LoginPresenterTest;

import dagger.Component;

@PerActivity
@Component(modules = MockLoginModule.class, dependencies = MockAppComponent.class)
public interface MockLoginComponent {
    void inject(LoginPresenterTest test);
}
