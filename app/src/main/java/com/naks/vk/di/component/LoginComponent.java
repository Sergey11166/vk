package com.naks.vk.di.component;

import com.naks.vk.di.anotation.PerActivity;
import com.naks.vk.di.module.LoginModule;
import com.naks.vk.mvp.model.interactor.LoginInteractorImpl;
import com.naks.vk.mvp.presenter.LoginPresenterImpl;
import com.naks.vk.ui.activity.LoginActivity;

import dagger.Component;

@PerActivity
@Component(modules = LoginModule.class, dependencies = AppComponent.class)
public interface LoginComponent {
    void inject(LoginActivity activity);
    void inject(LoginPresenterImpl presenter);
    void inject(LoginInteractorImpl interactor);
}
