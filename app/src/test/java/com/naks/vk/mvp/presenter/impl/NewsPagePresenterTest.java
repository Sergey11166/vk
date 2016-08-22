package com.naks.vk.mvp.presenter.impl;

import com.naks.vk.BuildConfig;
import com.naks.vk.TestApp;
import com.naks.vk.api.domain.VKApiNews;
import com.naks.vk.di.component.DaggerMockMainComponent;
import com.naks.vk.di.module.MockMainModule;
import com.naks.vk.di.module.MockNewsPageModule;
import com.naks.vk.mvp.model.interactor.OnLoginFinishedListener;
import com.naks.vk.mvp.model.interactor.OnNewsLoadingFinishedListener;
import com.naks.vk.mvp.model.interactor.TypeNews;
import com.naks.vk.mvp.view.NewsPageView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import javax.inject.Inject;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, application = TestApp.class, sdk = 21)
public class NewsPagePresenterTest {

    @Inject NewsPagePresenterImpl presenter;
    @Inject NewsPageView view;

    @Before
    public void setUp() {
        DaggerMockMainComponent.builder()
                .mockAppComponent(((TestApp)RuntimeEnvironment.application).getComponent())
                .mockMainModule(new MockMainModule())
                .build()
                .plus(new MockNewsPageModule())
                .inject(this);
        presenter.view = view;
    }

    @Test
    public void firstLoadNews() {
        presenter.loadNews(false);

        verify(view, times(1)).showLoading(anyBoolean());
        verify(presenter.interactor, times(1))
                .get(any(TypeNews.class), eq(false), eq(null), any(OnNewsLoadingFinishedListener.class));
        verify(view, times(1)).setData(any(VKApiNews.class));
        verify(view, times(1)).showContent();
    }

    @Test
    public void pullToRefresh() {
        presenter.startFrom = "test";
        presenter.loadNews(true);

        verify(view, times(1)).showLoading(true);
        verify(presenter.interactor, times(1))
                .get(any(TypeNews.class), eq(true), anyString(), any(OnNewsLoadingFinishedListener.class));
        verify(view, times(1)).setData(any(VKApiNews.class));
        verify(view, times(1)).showContent();
    }

    @Test
    public void addData() {
        presenter.startFrom = "test";
        presenter.loadNews(false);

        verify(presenter.interactor, times(1))
                .get(any(TypeNews.class), eq(false), anyString(), any(OnNewsLoadingFinishedListener.class));
        verify(view, times(1)).addData(any(VKApiNews.class));
    }
}
