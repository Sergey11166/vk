package com.naks.vk.di.module;

import com.naks.vk.api.domain.VKApiNews;
import com.naks.vk.di.anotation.FailedLoading;
import com.naks.vk.di.anotation.PerFragment;
import com.naks.vk.di.anotation.SuccessLoading;
import com.naks.vk.mvp.model.interactor.GetNewsInteractor;
import com.naks.vk.mvp.model.interactor.OnNewsLoadingFinishedListener;
import com.naks.vk.mvp.model.interactor.TypeNews;
import com.naks.vk.mvp.presenter.impl.NewsPagePresenterImpl;
import com.naks.vk.mvp.view.NewsPageView;
import com.vk.sdk.api.VKError;

import org.json.JSONObject;

import dagger.Module;
import dagger.Provides;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;

@Module
public class MockNewsPageModule {

    private final static String RESPONSE = "{response: {items: [], profiles: [], groups: [], next_from: 'test'}}";
    private final static String ERROR = "{error: {error_msg: 'Error message', request_params: []}}";

    @Provides
    @PerFragment
    NewsPageView provideView() {
        return mock(NewsPageView.class);
    }

    @Provides
    @PerFragment
    @SuccessLoading
    GetNewsInteractor provideSuccessInteractor() {

        final GetNewsInteractor interactor = mock(GetNewsInteractor.class);

        //pullToRefresh
        doAnswer(invocation -> {
            VKApiNews news = new VKApiNews(new JSONObject(RESPONSE));
            ((OnNewsLoadingFinishedListener)invocation.getArguments()[3]).onLoadingSuccess(news, true);
            return null;
        }).when(interactor)
                .get(any(TypeNews.class), eq(true), anyString(), any(OnNewsLoadingFinishedListener.class));

        //addData
        doAnswer(invocation -> {
            VKApiNews news = new VKApiNews(new JSONObject(RESPONSE));
            ((OnNewsLoadingFinishedListener)invocation.getArguments()[3]).onLoadingSuccess(news, false);
            return null;
        }).when(interactor)
                .get(any(TypeNews.class), eq(false), anyString(), any(OnNewsLoadingFinishedListener.class));

        //firstLoadNews
        doAnswer(invocation -> {
            VKApiNews news = new VKApiNews(new JSONObject(RESPONSE));
            news.next_from = null;
            ((OnNewsLoadingFinishedListener)invocation.getArguments()[3]).onLoadingSuccess(news, false);
            return null;
        }).when(interactor)
                .get(any(TypeNews.class), eq(false), eq(null), any(OnNewsLoadingFinishedListener.class));

        return interactor;
    }

    @Provides
    @PerFragment
    @FailedLoading
    GetNewsInteractor provideFailedInteractor() {

        final GetNewsInteractor interactor = mock(GetNewsInteractor.class);

        //showErrorLoadPage
        doAnswer(invocation -> {
            JSONObject root = new JSONObject(ERROR);
            JSONObject object = root.getJSONObject("error");
            object.put("error_code", "100");
            VKError vkError = new VKError(object);
            ((OnNewsLoadingFinishedListener)invocation.getArguments()[3]).onLoadingFailed(vkError, false);
            return null;
        }).when(interactor)
                .get(any(TypeNews.class), eq(false), anyString(), any(OnNewsLoadingFinishedListener.class));

        //showErrorPullToRefreshTrue
        doAnswer(invocation -> {
            JSONObject root = new JSONObject(ERROR);
            JSONObject object = root.getJSONObject("error");
            object.put("error_code", "100");
            VKError vkError = new VKError(object);
            ((OnNewsLoadingFinishedListener)invocation.getArguments()[3]).onLoadingFailed(vkError, true);
            return null;
        }).when(interactor)
                .get(any(TypeNews.class), eq(true), anyString(), any(OnNewsLoadingFinishedListener.class));

        //showErrorPullToRefreshFalse
        doAnswer(invocation -> {
            JSONObject root = new JSONObject(ERROR);
            JSONObject object = root.getJSONObject("error");
            object.put("error_code", "100");
            VKError vkError = new VKError(object);
            ((OnNewsLoadingFinishedListener)invocation.getArguments()[3]).onLoadingFailed(vkError, false);
            return null;
        }).when(interactor)
                .get(any(TypeNews.class), eq(false), eq(null), any(OnNewsLoadingFinishedListener.class));

        return interactor;
    }

    @Provides
    @PerFragment
    @SuccessLoading
    NewsPagePresenterImpl provideSuccessPresenter(@SuccessLoading GetNewsInteractor interactor) {
        return new NewsPagePresenterImpl(interactor, TypeNews.NEWS);
    }

    @Provides
    @PerFragment
    @FailedLoading
    NewsPagePresenterImpl provideFailedPresenter(@FailedLoading GetNewsInteractor interactor) {
        return new NewsPagePresenterImpl(interactor, TypeNews.NEWS);
    }
}
