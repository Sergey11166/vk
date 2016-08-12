package com.naks.vk.di.module;

import com.naks.vk.mvp.model.interactor.LoginInteractor;
import com.naks.vk.mvp.model.interactor.OnLoginFinishedListener;
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
        LoginInteractor interactor = mock(LoginInteractor.class);
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                ((OnLoginFinishedListener)invocation.getArguments()[0]).onLoggedIn();
                return null;
            }
        }).when(interactor).wakeUpSession(any(OnLoginFinishedListener.class));
        return interactor;
    }
}
