package com.naks.vk.mvp.presenter.impl;

import com.naks.vk.BuildConfig;
import com.naks.vk.R;
import com.naks.vk.TestApp;
import com.naks.vk.di.component.DaggerMockMainComponent;
import com.naks.vk.di.module.MockMainModule;

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
public class MainPresenterTest {

    @Inject MainPresenterImpl presenter;

    @Before
    public void setUp() {
        DaggerMockMainComponent.builder()
                .mockAppComponent(((TestApp) RuntimeEnvironment.application).getComponent())
                .mockMainModule(new MockMainModule())
                .build()
                .inject(this);
    }

    @Test
    public void onNavigationItemSelectedTest() {
        presenter.onNavigationItemSelected(R.id.nav_news);
        verify(presenter.view, times(1)).showNewsTabFragment();
        verify(presenter.view, times(1)).closeDrawer();
    }

    @Test
    public void onPressBackTest() {
        presenter.onBackPressed(true);
        verify(presenter.view, times(1)).closeDrawer();

        presenter.onBackPressed(false);
        verify(presenter.view, times(1)).pressBack();
    }
}
