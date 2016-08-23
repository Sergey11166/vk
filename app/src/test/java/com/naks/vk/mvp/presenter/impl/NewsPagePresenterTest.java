package com.naks.vk.mvp.presenter.impl;

import com.naks.vk.BuildConfig;
import com.naks.vk.TestApp;
import com.naks.vk.api.domain.VKApiNews;
import com.naks.vk.di.anotation.FailedLoading;
import com.naks.vk.di.anotation.SuccessLoading;
import com.naks.vk.di.component.DaggerMockMainComponent;
import com.naks.vk.di.module.MockMainModule;
import com.naks.vk.di.module.MockNewsPageModule;
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

    @Inject @SuccessLoading NewsPagePresenterImpl successPresenter;
    @Inject @FailedLoading NewsPagePresenterImpl failedPresenter;
    @Inject NewsPageView view;

    @Before
    public void setUp() {
        DaggerMockMainComponent.builder()
                .mockAppComponent(((TestApp)RuntimeEnvironment.application).getComponent())
                .mockMainModule(new MockMainModule())
                .build()
                .plus(new MockNewsPageModule())
                .inject(this);
        successPresenter.view = view;
        failedPresenter.view = view;
    }

    @Test
    public void firstLoadNews() {
        successPresenter.startFrom = null;
        successPresenter.loadNews(false);

        verify(view, times(1)).showLoading(anyBoolean());
        verify(successPresenter.interactor, times(1))
                .get(any(TypeNews.class), eq(false), eq(null), any(OnNewsLoadingFinishedListener.class));
        verify(view, times(1)).setData(any(VKApiNews.class));
        verify(view, times(1)).showContent();
    }

    @Test
    public void pullToRefresh() {
        successPresenter.startFrom = "test";
        successPresenter.loadNews(true);

        verify(view, times(1)).showLoading(true);
        verify(successPresenter.interactor, times(1))
                .get(any(TypeNews.class), eq(true), anyString(), any(OnNewsLoadingFinishedListener.class));
        verify(view, times(1)).setData(any(VKApiNews.class));
        verify(view, times(1)).showContent();
    }

    @Test
    public void addData() {
        successPresenter.startFrom = "test";
        successPresenter.loadNews(false);

        verify(successPresenter.interactor, times(1))
                .get(any(TypeNews.class), eq(false), eq("test"), any(OnNewsLoadingFinishedListener.class));
        verify(view, times(1)).addData(any(VKApiNews.class));
    }

    @Test
    public void showErrorLoadPage() {
        failedPresenter.startFrom = "test";
        failedPresenter.loadNews(false);

        verify(failedPresenter.interactor, times(1))
                .get(any(TypeNews.class), eq(false), anyString(), any(OnNewsLoadingFinishedListener.class));
        verify(view, times(1)).showErrorLoadPage();
    }

    @Test
    public void showErrorWhenPullToRefreshTrue() {
        failedPresenter.startFrom = "test";
        failedPresenter.loadNews(true);

        verify(failedPresenter.interactor, times(1))
                .get(any(TypeNews.class), eq(true), anyString(), any(OnNewsLoadingFinishedListener.class));
        verify(view, times(1)).showError(any(Throwable.class), eq(true));
    }

    @Test
    public void showErrorWhenPullToRefreshFalse() {
        failedPresenter.startFrom = null;
        failedPresenter.loadNews(false);

        verify(failedPresenter.interactor, times(1))
                .get(any(TypeNews.class), eq(false), eq(null), any(OnNewsLoadingFinishedListener.class));
        verify(view, times(1)).showError(any(Throwable.class), eq(false));
    }
}
