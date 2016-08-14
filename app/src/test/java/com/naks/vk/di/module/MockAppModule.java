package com.naks.vk.di.module;

import com.naks.vk.mvp.model.interactor.LoginInteractor;
import com.naks.vk.mvp.model.interactor.OnLoginFinishedListener;
import com.naks.vk.mvp.presenter.LoginPresenter;
import com.naks.vk.mvp.presenter.impl.LoginPresenterImpl;
import com.naks.vk.mvp.view.LoginView;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;

@Module
public class MockAppModule {

    @Provides
    @Singleton
    LoginView provideLoginView() {
        return mock(LoginView.class);
    }

    @Provides
    @Singleton
    LoginInteractor provideLoginInteractor() {
        return mock(LoginInteractor.class);
    }

    @Provides
    @Singleton
    LoginPresenterImpl providePresenter(LoginView view, LoginInteractor interactor) {
        return new LoginPresenterImpl(view, interactor);
    }
}
