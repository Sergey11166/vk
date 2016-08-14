package com.naks.vk.di.module;

import com.naks.vk.di.anotation.PerActivity;
import com.naks.vk.mvp.model.interactor.LoginInteractor;
import com.naks.vk.mvp.presenter.impl.LoginPresenterImpl;
import com.naks.vk.mvp.view.LoginView;

import dagger.Module;
import dagger.Provides;

import static org.mockito.Mockito.mock;

@Module
public class MockLoginModule {

    @Provides
    @PerActivity
    LoginView provideView() {
        return mock(LoginView.class);
    }

    @Provides
    @PerActivity
    LoginInteractor provideInteractor() {
        return mock(LoginInteractor.class);
    }

    @Provides
    @PerActivity
    LoginPresenterImpl providePresenter(LoginView view, LoginInteractor interactor) {
        return new LoginPresenterImpl(view, interactor);
    }
}
