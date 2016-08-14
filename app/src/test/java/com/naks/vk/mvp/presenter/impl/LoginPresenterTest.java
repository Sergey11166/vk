package com.naks.vk.mvp.presenter.impl;

import com.naks.vk.TestApp;
import com.naks.vk.di.component.DaggerMockLoginComponent;
import com.naks.vk.di.module.MockLoginModule;
import com.naks.vk.mvp.model.interactor.OnLoginFinishedListener;

import org.junit.Before;
import org.junit.Test;

import javax.inject.Inject;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class LoginPresenterTest {

    @Inject LoginPresenterImpl presenter;

    @Before
    public void setUp() {
        DaggerMockLoginComponent.builder()
                .mockAppComponent((new TestApp()).getComponent())
                .mockLoginModule(new MockLoginModule())
                .build()
                .inject(this);
    }

    @Test
    public void invokedOnLoggedInWhenCallWakeUpSessionAndLogged() throws Exception {
        doAnswer(invocation -> {
            ((OnLoginFinishedListener)invocation.getArguments()[0]).onLoggedIn();
            return null;
        }).when(presenter.interactor).wakeUpSession(any(OnLoginFinishedListener.class));

        presenter.wakeUpSession();

        verify(presenter.view, times(1)).navigateToMainScreen();
    }

    @Test
    public void invokeShowLoginScreenWhenCallWakeUpSessionAndNotLogged() {
        doAnswer(invocation -> {
            ((OnLoginFinishedListener)invocation.getArguments()[0]).onLoggedOut();
            return null;
        }).when(presenter.interactor).wakeUpSession(any(OnLoginFinishedListener.class));

        presenter.wakeUpSession();

        verify(presenter.view, times(1)).showLoginScreen(LoginPresenterImpl.scope);
    }

    @Test
    public void invokeNavigateToMainScreenWhenCallOnUserPassedAuthorization() {
        presenter.onUserPassedAuthorization();
        verify(presenter.view, times(1)).navigateToMainScreen();
    }
}