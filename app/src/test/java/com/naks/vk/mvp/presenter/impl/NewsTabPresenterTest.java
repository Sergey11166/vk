package com.naks.vk.mvp.presenter.impl;

import com.naks.vk.BuildConfig;
import com.naks.vk.TestApp;
import com.naks.vk.di.component.DaggerMockMainComponent;
import com.naks.vk.di.module.MockMainModule;
import com.naks.vk.di.module.MockNewsTabModule;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import javax.inject.Inject;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, application = TestApp.class, sdk = 21)
public class NewsTabPresenterTest {

    @Inject NewsTabPresenterImpl presenter;

    @Before
    public void setUp() {
        DaggerMockMainComponent.builder()
                .mockAppComponent(((TestApp) RuntimeEnvironment.application).getComponent())
                .mockMainModule(new MockMainModule())
                .build()
                .plus(new MockNewsTabModule())
                .inject(this);
    }

    @Test
    public void onFABClickTest() {
        presenter.onFABClick();
        verify(presenter.view, times(1)).navigateToNewPostActivity();
    }
}
